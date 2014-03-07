#include "server.h"
#include "showmsg.h"
#include "malloc.h"

int8*  g_PBuff = NULL;
int8*  PTempBuff = NULL;
int32 fd;
session_list_t session_list;

void server_init()
{
	ShowStatus("Initializing server...\n");

	//init packet parser
	//init sql handle

	//load all static data from db

	//bind socket
	ShowStatus("Binding to port: %u", SERVER_PORT);
	fd = makeBind_udp(INADDR_ANY, SERVER_PORT);
	ShowMessage("\t - "CL_GREEN"OK"CL_RESET"\n");

	CREATE(g_PBuff, int8, RECV_BUFFER_SIZE + 20);
	CREATE(PTempBuff, int8, RECV_BUFFER_SIZE + 20);

	ShowStatus(CL_GREEN"Game server initialized!"CL_RESET"\n");
	ShowMessage("---------------------------------------\n");
}

session_data_t* get_session_ipport(uint64 ipp)
{
	session_list_t::iterator i = session_list.begin();
	while (i != session_list.end())
	{
		if ((*i).first == ipp)
			return (*i).second;
		i++;
	}
	return NULL;
}

session_data_t* create_session(uint32 ip, uint16 port)
{
	session_data_t* session_data = new session_data_t;
	memset(session_data, 0, sizeof session_data_t);

	// might need this later for checking order of packets
	//CREATE(session_data->server_packet_data, int8, RECV_BUFFER_SIZE + 20);

	session_data->last_update = time(NULL);
	session_data->addr = ip;
	session_data->port = port;

	uint64 port64 = port;
	uint64 ipp = ip;
	ipp |= port64 << 32;
	session_list[ipp] = session_data;

	ShowInfo(CL_WHITE"session created for" CL_RESET":" CL_CYAN"%s" CL_RESET":" CL_CYAN"%u"
		CL_RESET"\n", ip2str(session_data->addr, NULL), session_data->port);
	return session_data;
}

int32 process_sockets(fd_set* rfd)
{	
	memcpy(rfd, &readfds, sizeof(*rfd));

	int32 ret = sSelect(fd_max, rfd, NULL, NULL, NULL);

	if (ret == SOCKET_ERROR)
	{
		if (sErrno != S_EINTR)
		{
			ShowFatalError("do_sockets: select() failed, error code %d!\n", sErrno);
			int sdfsdf = sErrno;
			exit(EXIT_FAILURE);
		}
		return 0;
	}

	last_tick = time(NULL);

	if (sFD_ISSET(fd, rfd))
	{
		struct sockaddr_in from;
		socklen_t fromlen = sizeof from;

		int32 ret = recvudp(fd, g_PBuff, RECV_BUFFER_SIZE, 0, (struct sockaddr*)&from, &fromlen);

		if (ret != -1)
		{
		#ifdef WIN32
			uint32 ip = ntohl(from.sin_addr.S_un.S_addr);
		#else
			uint32 ip = ntohl(from.sin_addr.s_addr);
		#endif

			uint64 port = ntohs(from.sin_port);
			uint64 ipport = ip | port << 32;
			session_data_t* session = get_session_ipport(ipport);

			if (!session)
			{
				session = create_session(ip, (uint16)port);
				if (!session)
				{
					return -1;
				}
			}

			//parse packets

		}
	}
	return 0;

}
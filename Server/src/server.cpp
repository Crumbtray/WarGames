#include "server.h"
#include "showmsg.h"
#include "malloc.h"
#include "packet_handler.h"

#include "packets\packet.h"

int8*  g_PBuff = NULL;
int8*  PTempBuff = NULL;
int32 fd;
session_list_t session_list;

void server_init()
{
	ShowStatus("Initializing server...\n");

	packethandler::init();
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


int32 recv_parse(int8* buffer, size_t buffsize, sockaddr_in* from, session_data_t* session)
{
	int8* packet_begin = &buffer[0];
	int8* packet_end = &buffer[buffsize];

	CPlayer* PPlayer = session->PPlayer;

	uint8 chunk_size = 0;
	uint8 chunk_type = 0;

	for (int8* chunk_ptr = packet_begin; chunk_ptr + RBUFB(chunk_ptr, 1) <= packet_end && RBUFB(chunk_ptr, 1); chunk_ptr = chunk_ptr + chunk_size)
	{
		chunk_size = RBUFB(chunk_ptr, 1);
		chunk_type = RBUFB(chunk_ptr, 0);

		if (packethandler::PacketSizes[chunk_type] == chunk_size || packethandler::PacketSizes[chunk_type] == 0)
		{
		ShowInfo("parse: %02hX (size %02hX) from user %s\n", chunk_type, chunk_size, PPlayer->GetName());
		//parse the incoming packet
		packethandler::PacketParser[chunk_type](session, PPlayer, chunk_ptr);
		}
		else
		{
			ShowWarning("Incorrect packet size %02hX for %02hX from user %s\n", chunk_size, chunk_type, PPlayer->GetName());
		}
	}
	return 0;
}

int32 send_parse(int8 *buff, size_t* buffsize, sockaddr_in* from, session_data_t* session)
{
	*buffsize = 0;
	CPlayer* PPlayer = session->PPlayer;
	while (!PPlayer->packetQueueEmpty())
	{
		CPacket* PPacket = PPlayer->popPacket();
		memcpy(buff + *buffsize, PPacket, PPacket->getSize());

		*buffsize += PPacket->getSize();

		delete PPacket;
	}
	return 0;
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

			size_t size = ret;

			//TODO: decide how to handle sending
			//recv_parse(g_PBuff, size, &from, session);
			//sendudp(fd, g_PBuff, size, 0, (const struct sockaddr*)&from, fromlen);
		}
	}
	return 0;

}
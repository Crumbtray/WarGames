#include "server.h"
#include "showmsg.h"
#include "malloc.h"

int8*  g_PBuff = NULL;
int8*  PTempBuff = NULL;
int32 fd;

void server_init()
{
	ShowStatus("Initializing server...\n");

	//init packet parse
	//init sql handle

	//load all static data from db

	//bind socket
	ShowStatus("Binding to port: %u", 31111);
	fd = makeBind_udp(INADDR_ANY, 31111);
	ShowMessage("\t - "CL_GREEN"OK"CL_RESET"\n");

	CREATE(g_PBuff, int8, RECV_BUFFER_SIZE + 20);
	CREATE(PTempBuff, int8, RECV_BUFFER_SIZE + 20);

	ShowStatus(CL_GREEN"Game server initialized!"CL_RESET"\n");
	ShowMessage("---------------------------------------\n");
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
			/*session_data_t* session = getSessionByIPPort(ipport);

			if (!session)
			{
				session = createSession(ip, port);
				if (!session)
				{
					return -1;
				}
			}*/

			//parse packets

		}
	}
	return 0;

}
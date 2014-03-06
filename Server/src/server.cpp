#include "server.h"

int8*  g_PBuff = NULL;
int8*  PTempBuff = NULL;

int32 process_sockets(fd_set* rfd, int32 next)
{
	struct timeval timeout;
	
	memcpy(rfd, &readfds, sizeof(*rfd));

	timeout.tv_sec = next / 1000;
	timeout.tv_usec = next % 1000 * 1000;

	int32 ret = sSelect(fd_max, rfd, NULL, NULL, &timeout);

	if (ret == SOCKET_ERROR)
	{
		if (sErrno != S_EINTR)
		{
			//print fatal error
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
#include "socket.h"
#include "server.h"

int main(int argc, char **argv)
{
	//init sockets
	fd_set readfd;
	{
		while (true)
		{
			process_sockets(&readfd);
		}
	}
}
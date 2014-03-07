#include "socket.h"
#include "server.h"
#include "showmsg.h"

int main(int argc, char **argv)
{
	ShowInfo(CL_MAGENTA"WarGames Server"CL_RESET"\n");
	//init sockets
	socket_init();
	server_init();
	fd_set readfd;
	{
		while (true)
		{
			process_sockets(&readfd);
		}
	}
}
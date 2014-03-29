#include "lib/socket.h"
#include "lib/showmsg.h"
#include "lib/taskmgr.h"
#include "lib/timer.h"

#include "server.h"

int main(int argc, char **argv)
{
	ShowInfo(CL_MAGENTA"WarGames Server"CL_RESET"\n");
	//init sockets
	socket_init();
	server_init();
	timer_init();
	fd_set readfd;
	{
		int next;

		while (true)
		{
			next = CTaskMgr::getInstance()->DoTimer(gettick_nocache());
			process_sockets(&readfd, next);
		}
	}
}
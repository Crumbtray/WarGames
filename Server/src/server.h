

#ifndef _SERVER_H
#define _SERVER_H

#include "cbasetypes.h"
#include "socket.h"

#define RECV_BUFFER_SIZE 1000

struct session_data_t
{
	uint32		addr;
	uint16		port;
	//account/user object
};

extern int32 fd;

void server_init();


#endif
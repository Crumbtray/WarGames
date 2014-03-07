

#ifndef _SERVER_H
#define _SERVER_H

#include "cbasetypes.h"
#include "socket.h"

#include <map>

#define RECV_BUFFER_SIZE 1000
#define SERVER_PORT 31111

struct session_data_t
{
	uint32		addr;		 //client IP address
	uint16		port;		 //client port
	time_t		last_update; //for detecting disconnection
	//account/user object
};

typedef std::map<uint64, session_data_t*> session_list_t;
extern session_list_t session_list;
extern int32 fd;
extern session_data_t* get_session_ipport(uint64 ipp);
extern session_data_t* create_session(uint32 ip, uint16 port);

void server_init();



#endif
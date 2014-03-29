
#include "lobby_chat.h"

#include "../lib/socket.h"
#include "../player.h"

CLobbyChatPacket::CLobbyChatPacket(CPlayer* player, const char* msg)
{
	this->type = 0x04;
	uint32 msgsize = strlen(msg) > 30 ? 30 : strlen(msg);
	this->size = 0x03 + msgsize;
	
	WBUFB(packet, 0x02) = msgsize;
	memcpy(packet + 0x03, player->GetName(), 10);
	memcpy(packet + 0x0D, msg, msgsize);
}
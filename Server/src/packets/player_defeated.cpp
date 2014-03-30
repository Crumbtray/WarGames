
#include "player_defeated.h"
#include "../player.h"

#include "../lib/socket.h"

CPlayerDefeatedPacket::CPlayerDefeatedPacket(CPlayer* player)
{
	this->type = 0x16;
	this->size = 0x07;

	//WBUFB(packet, 0x02) = player number
	WBUFL(packet, 0x03) = player->id;
}

#include "player_definition.h"
#include "../player.h"

#include "../lib/socket.h"

CPlayerDefinitionPacket::CPlayerDefinitionPacket(CPlayer* player)
{
	this->type = 0x12;
	this->size = 0x13;

	WBUFL(packet, 0x02) = player->id;
	memcpy(packet + 0x06, player->GetName(), 10);
	WBUFB(packet, 0x10) = player->GetColor();
	WBUFB(packet, 0x11) = player->GetTeam();
	//WBUFB(packet, 0x12) = player number (from lobby/game instance i guess)
}
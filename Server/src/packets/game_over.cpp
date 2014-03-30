
#include "game_over.h"
#include "../lobby.h"
#include "../player.h"

#include "../lib/socket.h"

CGameOverPacket::CGameOverPacket(CPlayer* player)
{
	this->type = 0x17;
	this->size = 0x07;

	CLobby* lobby = lobbies::getLobby(player);

	if (lobby)
	{
		WBUFB(packet, 0x02) = lobby->playerNumber(player);
	}

	WBUFL(packet, 0x03) = player->id;
}
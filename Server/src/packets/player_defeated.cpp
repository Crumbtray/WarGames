
#include "player_defeated.h"
#include "../lobby.h"
#include "../player.h"

#include "../lib/socket.h"

CPlayerDefeatedPacket::CPlayerDefeatedPacket(CPlayer* player)
{
	this->type = 0x16;
	this->size = 0x07;

	CLobby* lobby = lobbies::getLobby(player);

	if (lobby)
	{
		WBUFB(packet, 0x02) = lobby->playerNumber(player);
	}
	WBUFL(packet, 0x03) = player->id;
}
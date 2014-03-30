
#include "lobby_list.h"

#include "../lib/socket.h"
#include "../player.h"
#include "../lobby.h"

CLobbyListPacket::CLobbyListPacket()
{
	this->type = 0x06;
	uint32 lobbies = lobbies::getLobbies().size() > 16 ? 16 : lobbies::getLobbies().size();
	this->size = 0x0F * lobbies + 2;

	auto lobbyList = lobbies::getLobbies();

	for (uint8 i = 0; i < lobbyList.size(); i++)
	{
		WBUFW(packet, 0x02 + i * 0x0F) = lobbyList.at(i)->id;
		WBUFB(packet, 0x04 + i * 0x0F) = lobbyList.at(i)->playerList.size();
		WBUFB(packet, 0x05 + i * 0x0F) = lobbyList.at(i)->getMaxSize();
		WBUFB(packet, 0x06 + i * 0x0F) = lobbyList.at(i)->getMapID();
		memcpy(packet + 0x07 + i * 0x0F, lobbyList.at(i)->getHost()->GetName(), 10);
	}
}
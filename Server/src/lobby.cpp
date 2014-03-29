
#include "lobby.h"
#include "player.h"


CLobby::CLobby(uint32 id)
{
	this->id = id;
	m_maxSize = 0;
	m_mapID = 0;
}

CLobby::~CLobby()
{

}

uint8 CLobby::getMaxSize()
{
	return m_maxSize;
}

void CLobby::setMapID(uint8 map)
{
	m_mapID = map;
}

uint8 CLobby::getMapID()
{
	return m_mapID;
}

void CLobby::addPlayer(CPlayer* player)
{
	playerList.push_back(player);
}

void CLobby::removePlayer(CPlayer* player)
{
	for (uint8 i = 0; i < playerList.size(); i++)
	{
		if (playerList.at(i)->id == player->id)
		{
			playerList.erase(playerList.begin() + i);
		}
	}
}

bool CLobby::hasPlayer(CPlayer* player)
{
	for (uint8 i = 0; i < playerList.size(); i++)
	{
		if (playerList.at(i)->id == player->id)
		{
			return true;
		}
	}
	return false;
}

namespace lobbies
{
	std::vector<CLobby*> lobbyList;

	CLobby* createLobby()
	{
		CLobby* lobby = new CLobby(newLobbyId());
		lobbyList.push_back(lobby);
	}

	CLobby* getLobby(CPlayer* player)
	{
		for (uint32 i = 0; i < lobbyList.size(); i++)
		{
			if (lobbyList.at(i)->hasPlayer(player))
			{
				return lobbyList.at(i);
			}
		}
		return NULL;
	}

	uint32 newLobbyId()
	{
		uint32 lobbyId = 1;
		for (uint32 i = 0; i < lobbyList.size(); i++)
		{
			if (lobbyList.at(i)->id == lobbyId)
			{
				lobbyId++;
				i = 0;
			}
		}
		return lobbyId;
	}
}
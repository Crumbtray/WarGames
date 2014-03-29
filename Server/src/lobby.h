#ifndef _LOBBY_H
#define _LOBBY_H

#include "lib/cbasetypes.h"
#include <vector>

class CPlayer;

class CLobby
{
private:
	uint8 m_maxSize;
	uint8 m_mapID;
public:

	uint32 id;
	std::vector<CPlayer*> playerList;

	uint8 getMaxSize();
	void setMapID(uint8);
	uint8 getMapID();
	void addPlayer(CPlayer*);
	void removePlayer(CPlayer*);
	bool hasPlayer(CPlayer*);

	CLobby(uint32);
	~CLobby();
};

namespace lobbies
{
	CLobby* createLobby();
	CLobby* getLobby(CPlayer*);
}

#endif
#ifndef _GAME_H
#define _GAME_H

#include "lib/cbasetypes.h"
#include <vector>

class CPlayer;

class CGame
{
private:
	uint8 m_activePlayer;
	uint8 m_mapID;
	CPlayer* m_winner;
public:
	std::vector<CPlayer*> playerList;

	uint8 getMapID();
	void setMapID(uint8);

	void addPlayer(CPlayer*);
	bool hasPlayer(CPlayer*);
	bool isActivePlayer(CPlayer*);
	bool isWinner(CPlayer*);

	void endTurn();
	void start();
	void end(CPlayer* winner);

	CGame(uint8 mapID);
	~CGame();
};

namespace games
{
	CGame* createGame(uint8 mapID);
	CGame* getGame(CPlayer*);
}

#endif
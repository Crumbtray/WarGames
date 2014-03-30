#ifndef _GAME_H
#define _GAME_H

#include "lib/cbasetypes.h"
#include <vector>

class CPlayer;

class CGame
{
private:
	uint8 m_activePlayer;
public:
	std::vector<CPlayer*> playerList;

	void addPlayer(CPlayer*);
	bool hasPlayer(CPlayer*);
	bool isActivePlayer(CPlayer*);
	void endTurn();

	CGame();
	~CGame();
};

namespace games
{
	CGame* createGame();
	CGame* getGame(CPlayer*);
}

#endif
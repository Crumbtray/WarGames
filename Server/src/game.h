#ifndef _GAME_H
#define _GAME_H

#include "lib/cbasetypes.h"
#include <vector>

class CPlayer;

class CGame
{
private:

public:
	std::vector<CPlayer*> playerList;

	void addPlayer(CPlayer*);

	CGame();
	~CGame();
};

#endif
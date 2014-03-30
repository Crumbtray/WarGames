
#include "game.h"
#include "player.h"


CGame::CGame()
{

}

CGame::~CGame()
{

}

void CGame::addPlayer(CPlayer* player)
{
	playerList.push_back(player);
}
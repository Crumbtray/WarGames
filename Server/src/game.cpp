
#include "game.h"
#include "player.h"

#include "packets/turn_change.h"

CGame::CGame()
{

}

CGame::~CGame()
{

}

void CGame::endTurn()
{
	m_activePlayer = m_activePlayer >= playerList.size() - 1 ? 0 : m_activePlayer + 1;

	for (auto player : playerList)
	{
		player->pushPacket(new CTurnChangePacket(m_activePlayer));
	}
}

bool CGame::hasPlayer(CPlayer* player)
{
	for (auto p : playerList)
	{
		if (p->id == player->id)
		{
			return true;
		}
	}
	return false;
}

void CGame::addPlayer(CPlayer* player)
{
	playerList.push_back(player);
}

bool CGame::isActivePlayer(CPlayer* player)
{
	return player == playerList.at(m_activePlayer);
}

namespace games
{
	std::vector<CGame*> gameList;

	CGame* createGame()
	{
		CGame* game = new CGame();
		gameList.push_back(game);
		return game;
	}

	CGame* getGame(CPlayer* player)
	{
		for (auto game : gameList)
		{
			if (game->hasPlayer(player))
			{
				return game;
			}
		}
		return NULL;
	}
}
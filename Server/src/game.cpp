
#include "game.h"
#include "Map.h"
#include "player.h"

#include "packets\player_definition.h"
#include "packets\post_game.h"
#include "packets\turn_change.h"

CGame::CGame(uint8 map)
{
	m_map = new Map(map);
	m_activePlayer = 0;
	m_winner = NULL;
}

CGame::~CGame()
{
	delete m_map;
}

Map* CGame::getMap()
{
	return m_map;
}

Unit* CGame::getUnit(uint16 id)
{
	return m_map->getUnit(id);
}

void CGame::endTurn()
{
	m_activePlayer = m_activePlayer >= playerList.size() - 1 ? 0 : m_activePlayer + 1;

	for (auto player : playerList)
	{
		player->pushPacket(new CTurnChangePacket(m_activePlayer));
	}
}

void CGame::start()
{
	m_activePlayer = 0;
	for (auto player : playerList)
	{
		for (auto player2 : playerList)
		{
			player->pushPacket(new CPlayerDefinitionPacket(player2));
		}
		player->pushPacket(new CTurnChangePacket(m_activePlayer));
	}
}

void CGame::addPlayer(CPlayer* player)
{
	playerList.push_back(player);
	player->SetMoney(0);
	player->SetScore(0);
}

void CGame::end(CPlayer* winner)
{
	m_winner = winner;

	for (auto p : playerList)
	{
		p->pushPacket(new CPostGamePacket(winner, this));
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

bool CGame::isActivePlayer(CPlayer* player)
{
	return player == playerList.at(m_activePlayer);
}

bool CGame::isWinner(CPlayer* player)
{
	return player == m_winner;
}

namespace games
{
	std::vector<CGame*> gameList;

	CGame* createGame(uint8 mapID)
	{
		CGame* game = new CGame(mapID);
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
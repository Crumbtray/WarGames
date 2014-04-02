
#include "game.h"
#include "Map.h"
#include "player.h"

#include "packets\action.h"
#include "packets\entity_update.h"
#include "packets\player_definition.h"
#include "packets\post_game.h"
#include "packets\turn_change.h"

CGame::CGame(uint8 map, std::vector<CPlayer*> playerList)
{
	m_playerList = playerList;
	m_map = new Map(map, playerList);
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
	m_activePlayer = m_activePlayer >= m_playerList.size() - 1 ? 0 : m_activePlayer + 1;

	for (auto player : m_playerList)
	{
		player->pushPacket(new CTurnChangePacket(m_activePlayer));
	}
}

void CGame::start()
{
	m_activePlayer = 0;
	for (auto player : m_playerList)
	{
		for (auto player2 : m_playerList)
		{
			player->pushPacket(new CPlayerDefinitionPacket(player2));
		}
		player->pushPacket(new CTurnChangePacket(m_activePlayer));
	}
}

void CGame::addPlayer(CPlayer* player)
{
	m_playerList.push_back(player);
	player->SetMoney(0);
	player->SetScore(0);
}

void CGame::end(CPlayer* winner)
{
	m_winner = winner;

	for (auto p : m_playerList)
	{
		p->pushPacket(new CPostGamePacket(winner, this));

		if (p == m_winner)
		{
			p->AddWin();
		}
		else
		{
			p->AddLoss();
		}
	}
}

bool CGame::hasPlayer(CPlayer* player)
{
	for (auto p : m_playerList)
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
	return player == m_playerList.at(m_activePlayer);
}

uint8 CGame::getActivePlayer()
{
	return m_activePlayer;
}

bool CGame::isWinner(CPlayer* player)
{
	return player == m_winner;
}

void CGame::updateEntity(Unit* unit)
{
	for (auto p : m_playerList)
	{
		p->pushPacket(new CEntityUpdatePacket(unit, m_map->getUnitPos(unit->getID())));
	}
}

void CGame::checkVictoryCondition()
{
	int losers = 0;
	for (auto p : m_playerList)
	{
		if (!m_map->unitsRemain(p))
		{
			losers++;
		}
	}
	if (losers == m_playerList.size() - 1)
	{
		for (auto p : m_playerList)
		{
			if (m_map->unitsRemain(p))
			{
				end(p);
			}
		}
	}
}

void CGame::sendAction(Unit* initiator, Unit* target, ACTION action, int8 dmginit, int8 dmgtarget, std::pair<uint8, uint8> pos)
{
	for (auto p : m_playerList)
	{
		p->pushPacket(new CActionPacket(initiator, target, action, dmginit, dmgtarget, pos));
	}
}

namespace games
{
	std::vector<CGame*> gameList;

	CGame* createGame(uint8 mapID, std::vector<CPlayer*> playerList)
	{
		CGame* game = new CGame(mapID, playerList);
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
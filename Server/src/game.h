#ifndef _GAME_H
#define _GAME_H

#include "lib/cbasetypes.h"
#include "packets/action.h"
#include <vector>

class CPlayer;
class Map;
class Unit;

class CGame
{
private:
	uint8 m_activePlayer;
	Map* m_map;
	CPlayer* m_winner;
public:
	std::vector<CPlayer*> playerList;

	Map* getMap();
	Unit* getUnit(uint16);

	void addPlayer(CPlayer*);
	bool hasPlayer(CPlayer*);
	bool isActivePlayer(CPlayer*);
	uint8 getActivePlayer();
	bool isWinner(CPlayer*);

	void endTurn();
	void start();
	void end(CPlayer* winner);

	void updateEntity(Unit*);
	void sendAction(Unit* initiator, Unit* target, ACTION action, int8 dmginit, int8 dmgtarget, std::pair<uint8, uint8> pos);
	void checkVictoryCondition();

	CGame(uint8 mapID);
	~CGame();
};

namespace games
{
	CGame* createGame(uint8 mapID);
	CGame* getGame(CPlayer*);
}

#endif
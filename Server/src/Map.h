#pragma once

#include "Tile.h"
#include <utility>

class Unit;

class Map
{
private:
	const int WIDTH;
	const int HEIGHT;
	uint16 mapID();
	Tile **tiles;

	uint16 newUnitId(); //same as lobby's newLobbyId()

public:
	Map(uint8 mapID);
	~Map();

	uint8 getMapID();
	Unit* getUnit(uint16 id);
	std::pair<uint8, uint8> getUnitPos(uint16 id);
	Unit* produceUnit(uint8 xpos, uint8 ypos, UnitType type);
	
};


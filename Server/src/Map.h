#pragma once

#include "Tile.h"

class Unit;

class Map
{
private:
	const int WIDTH;
	const int HEIGHT;
	uint16 mapID();
	Tile **tiles;

public:
	Map(uint8 mapID);
	~Map();

	uint8 getMapID();
	Unit* getUnit(uint16);
};


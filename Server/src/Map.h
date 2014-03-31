#pragma once

#include "Tile.h"
class Map
{
private:
	const int WIDTH;
	const int HEIGHT;
	Tile **tiles;

public:
	Map();
	Map(int width, int height);
	~Map();
};


#pragma once
#include "TypeEnums.h"
#include "Unit.h"

class Tile
{
private:
	const TerrainType TYPE;
	const int INCOME;
	int owner;
	Unit unit;
	int moveCost;


public:
	Tile();
	~Tile();
	TerrainType getType();
	int getIncome();

	Unit getUnit();
	Unit setUnit();
	int getOwner();
	void setOwner();
	int getMoveCost();
	int setMoveCost();
};


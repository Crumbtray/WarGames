#pragma once
#include "TypeEnums.h"
#include <map>
#include "Unit.h"

typedef std::map<MobilityType, int> MoveCost;
class Terrain
{
	
private:
	const TerrainType m_TYPE;
	const int m_INCOME;
	uint8 m_owner;
	int m_defense;
	Unit *m_unit;
	MoveCost m_moveCost;


public:
	Terrain(TerrainType type, int income);
	~Terrain();
	Terrain **init();
	TerrainType getType();
	int getIncome();

	Unit getUnit();
	Unit setUnit(Unit unit);
	uint8 getOwner();
	void setOwner(uint8);
	int getMoveCost(MobilityType type);
	int setMoveCost(MobilityType type);
	int getDefense();
	void setDefense(int defense);
};


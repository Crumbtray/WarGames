#pragma once
#include "TypeEnums.h"
class Unit
{

private:
	const UnitType UNIT_TYPE;
	const int OWNER;
	const MobilityType MOBILITY_TYPE;
	const int MOBILITY; 
	const int COST;
	const int RANGE;
	const int VISION;
	bool active;
	bool visible;
	int health;
	int ammo;
	int armor;
	int fuel;
	int capture_progress;
	

public:
	uint16 id;

	Unit(UnitType type);
	Unit(int owner, MobilityType mType, int mobility, int cost, int range, int vision);
	~Unit();

	bool move(int x, int y);
	bool attack(int x, int y);
	int capture(int x, int y);
	void updateIncome(int amount);
};


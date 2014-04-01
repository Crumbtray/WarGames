#pragma once
#include "lib/cbasetypes.h"
#include "TypeEnums.h"

#define MAX_HEALTH 10
#define MAX_AMMO 20
#define MAX_FUEL 100

class Unit
{
private:
	const uint16 m_id;
	const UnitType m_UNIT_TYPE;
	const uint8 m_OWNER;
	const MobilityType m_MOBILITY_TYPE;
	const int m_MOBILITY;
	const int m_COST;
	const int m_RANGE;
	const int m_VISION;
	bool m_active;
	bool m_visible;
	int m_health;
	int m_ammo;
	int m_armor;
	int m_fuel;
	bool m_capturing;

public:/*
	bool move(int x, int y);
	bool attack(int x, int y, Unit* target);
	int capture(int x, int y);
	void updateIncome(int amount);*/

	Unit(UnitType type, uint8 owner, MobilityType mType, int mobility, int cost, int range, int vision, int id);
	~Unit();

	uint16 getID();
	UnitType getUnitType();
	uint8 getOwner();
	MobilityType getMobilityType();
	int getMobility();
	int getCost();
	int getMaxRange();
	int getMinRange();
	int getVision();
	bool isActive();
	bool activate();
	bool deactivate();
	bool isVisible();
	void setVisible(bool visible);
	int getHealth();
	void damageUnit(int damage);
	bool capture();
	bool isCapturing();
	void decreaseAmmo();
	void increaseAmmo(int ammo);
	int getAmmo();
	int getArmor();
	void decreaseFuel();
	void increaseFuel(int ammo);
	int getFuel();
};


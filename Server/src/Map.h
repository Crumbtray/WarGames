#pragma once

#define DEFAULT_WIDTH 16
#define DEFAULT_HEIGHT 16

#include <utility>
#include <vector>
#include "TypeEnums.h"
#include "Terrain.h"
#include "Unit.h"

class CPlayer;
class Map
{
typedef std::map<uint16, Unit*> UnitList;
typedef std::vector< std::vector<Terrain*>> TerrainMatrix;

private:
	const uint8 m_WIDTH;
	const uint8 m_HEIGHT;
	const uint8 m_mapID;
	TerrainMatrix m_terrain;
	UnitList m_unitList;

	uint16 newUnitId(); //same as lobby's newLobbyId()
	void initializeTerrain(uint8 mapID,	std::vector<CPlayer*> playerList);

public:
	Map(uint8 width, uint8 height, uint8 mapID, std::vector<CPlayer*> playerList);
	Map(uint8 mapID, std::vector<CPlayer*> playerList);
	~Map();

	bool unitsRemain(CPlayer*);

	uint8 getMapID();
	uint8 getWidth();
	uint8 getHeight();

	Terrain* getTerrainAt(uint8 x, uint8 y);
	Terrain* getTerrainUnderUnit(uint16 id);
	void addTerrain(uint8 x, uint8 y, Terrain *terrain);
	TerrainType getTerrainTypeAt(uint8 x, uint8 y);

	std::pair<uint8, uint8> getUnitPos(uint16 id);
	Unit *getUnit(uint16 id);
	Unit *getUnitAt(uint8 x, uint8 y);

	Unit *produceUnit(uint8 x, uint8 y, CPlayer* owner, UnitType type);
	bool moveUnit(Unit* unit, uint8 new_x, uint8 new_y);
	bool attackUnit(Unit* unit, uint8 new_x, uint8 new_y, Unit* target, int* targetdamage, int* returndamage);
	bool deleteUnit(Unit* unit);
	bool captureStructure(uint8 x, uint8 y); 
	void turnChange(CPlayer* player);
};


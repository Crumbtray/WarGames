#pragma once

#define DEFAULT_WIDTH 16
#define DEFAULT_HEIGHT 16

#include <utility>
#include <vector>
#include "TypeEnums.h"
#include "Terrain.h"
#include "Unit.h"


class Map
{
private:
	uint8 m_WIDTH;
	uint8 m_HEIGHT;
	uint8 m_mapID;
	std::vector< std::vector<Terrain*> > m_terrain;
	std::map<uint16, Unit*> m_unitList;

	uint16 newUnitId(); //same as lobby's newLobbyId()
	void initializeTerrain();

public:
	Map(uint8 width, uint8 height, uint8 mapID);
	Map(uint8 mapID);
	~Map();

	uint8 getMapID();
	uint8 getWidth();
	uint8 getHeight();

	Terrain* getTerrainAt(uint8 x, uint8 y);
	uint8 addTerrain(uint8 x, uint8 y, Terrain terrain);
	TerrainType getTerrainTypeAt(uint8 x, uint8 y);

	std::pair<uint8, uint8> getUnitPos(uint16 id);
	Unit *getUnit(uint16 id);
	Unit *getUnitAt(uint8 x, uint8 y);

	Unit *produceUnit(uint8 x, uint8 y, UnitType type);
	bool moveUnit(Unit* unit, uint8 new_x, uint8 new_y);
	bool deleteUnit(uint8 x, uint8 y);
	bool captureStructure(uint8 x, uint8 y);

};


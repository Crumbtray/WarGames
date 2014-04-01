

#include "Map.h"
#include "Terrain.h"
#include "TerrainBuilder.h"
#include "UnitBuilder.h"
#include "player.h"

Map::Map(uint8 width, uint8 height, uint8 mapID)
{
	m_mapID = mapID;
	m_HEIGHT = height;
	m_WIDTH = width;
	
	initializeTerrain();
}
Map::Map(uint8 mapID)
{
	//get data from mapID
	m_mapID = mapID;
	m_HEIGHT = 16;
	m_WIDTH = 16;

	initializeTerrain();
}
Map::~Map()
{

}

uint16 Map::newUnitId()
{
	uint32 unitId = 1;
	for (uint32 i = 0; i < m_unitList.size(); i++)
	{
		if (m_unitList.at(i)->getID() == unitId)
		{
			unitId++;
			i = 0;
		}
	}
	return unitId;
}

void Map::initializeTerrain()
{
	//temp hardcoded map
	TerrainBuilder plainBuilder(TerrainType::Plain);
	for (auto i : m_terrain)
	{
		for (auto terrain : i)
		{
			terrain = plainBuilder.getResult();
		}
	}
	TerrainBuilder factoryBuilder(TerrainType::Factory);
	delete m_terrain[0][0];
	m_terrain[0][0] = factoryBuilder.getResult();
	delete m_terrain[15][15];
	m_terrain[15][15] = factoryBuilder.getResult();
}

uint8 Map::getMapID()
{
	return m_mapID;
}
uint8 Map::getWidth()
{
	return m_WIDTH;
}
uint8 Map::getHeight()
{
	return m_HEIGHT;
}

Terrain* Map::getTerrainAt(uint8 x, uint8 y)
{
	return m_terrain[x][y];
}

Terrain* Map::getTerrainUnderUnit(uint16 id)
{
	for (int i = 0; i < m_WIDTH; i++)
	{
		for (int j = 0; j < m_HEIGHT; j++)
		{
			if (m_terrain[i][j]->getUnit() && m_terrain[i][j]->getUnit()->getID() == id)
			{
				return m_terrain[i][j];
			}
		}
	}
	return NULL;
}

std::pair<uint8, uint8> Map::getUnitPos(uint16 id)
{
	for (int i = 0; i < m_WIDTH; i++)
	{
		for (int j = 0; j < m_HEIGHT; j++)
		{
			if (m_terrain[i][j]->getUnit() && m_terrain[i][j]->getUnit()->getID() == id)
			{
				return std::pair<uint8, uint8>(i, j);
			}
		}
	}
	return std::pair<uint8, uint8>(-1, -1);
}

Unit* Map::getUnit(uint16 id)
{
	return m_unitList.find(id)->second;
}

Unit* Map::getUnitAt(uint8 x, uint8 y)
{
	return m_terrain[x][y]->getUnit();
}

Unit* Map::produceUnit(uint8 x, uint8 y, CPlayer* owner, UnitType type)
{
	Terrain* pos = getTerrainAt(x, y);
	if (pos->getUnit() == NULL)
	{
		UnitBuilder builder(type, owner, newUnitId());
		return builder.getResult();
	}
	return NULL;
}

bool Map::moveUnit(Unit* unit, uint8 new_x, uint8 new_y)
{
	Terrain* newPos = getTerrainAt(new_x, new_y);
	if (newPos->getUnit() == unit)
	{
		return true;
	}
	else if (newPos->getUnit() == NULL)
	{
		Terrain* oldPos = getTerrainUnderUnit(unit->getID());
		oldPos->setUnit(NULL);
		newPos->setUnit(unit);
		//todo: reduce fuel
		return true;
	}
	return false;
}

bool Map::attackUnit(Unit* unit, uint8 new_x, uint8 new_y, Unit* target)
{
	if (moveUnit(unit, new_x, new_y))
	{
		//TODO: ammo check, range check
		unit->attack(target);
	}
}

bool Map::deleteUnit(uint8 x, uint8 y)
{
	Terrain* pos = getTerrainAt(x, y);
	Unit* unit = pos->getUnit();
	if (unit)
	{
		m_unitList.erase(unit->getID());
		pos->setUnit(NULL);
		return true;
	}
	return false;
}

bool Map::captureStructure(uint8 x, uint8 y)
{
	return false;
}
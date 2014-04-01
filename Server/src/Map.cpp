

#include "Map.h"
#include "Terrain.h"
#include "TerrainBuilder.h"

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

Unit* produceUnit(uint8 x, uint8 y, UnitType type)
{

}

bool moveUnit(Unit* unit, uint8 new_x, uint8 new_y)
{

}

bool deleteUnit(uint8 x, uint8 y)
{

}

bool captureStructure(uint8 x, uint8 y)
{

}
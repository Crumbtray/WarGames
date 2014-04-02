
#include "Map.h"
#include "Terrain.h"
#include "TerrainBuilder.h"
#include "UnitBuilder.h"
#include "player.h"

Map::Map(uint8 width, uint8 height, uint8 mapID, std::vector<CPlayer*> playerList) :
m_mapID(mapID),
m_HEIGHT(height),
m_WIDTH(width)
{
	initializeTerrain(mapID, playerList);
}

Map::Map(uint8 mapID, std::vector<CPlayer*> playerList) :
m_mapID(mapID),
m_HEIGHT(DEFAULT_HEIGHT),
m_WIDTH(DEFAULT_WIDTH)
{
	//get data from mapID
	initializeTerrain(mapID, playerList);
}

Map::~Map(){
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

void Map::initializeTerrain(uint8 mapID, std::vector<CPlayer*> playerList){
	//TODO: use mapID to generate different maps

	//temp hardcoded map - all plains except for opposing corners
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
	m_terrain[0][0]->setOwner(playerList[0]);
	delete m_terrain[15][15];
	m_terrain[15][15] = factoryBuilder.getResult();
	m_terrain[15][15]->setOwner(playerList[1]);
}

uint8 Map::getMapID(){
	return m_mapID;
}

uint8 Map::getWidth(){
	return m_WIDTH;
}

uint8 Map::getHeight(){
	return m_HEIGHT;
}

Terrain* Map::getTerrainAt(uint8 x, uint8 y){
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

void Map::addTerrain(uint8 x, uint8 y, Terrain *terrain){
	m_terrain[x][y] = terrain;
}

TerrainType Map::getTerrainTypeAt(uint8 x, uint8 y){
	return m_terrain[x][y]->getType();
}

std::pair<uint8, uint8> Map::getUnitPos(uint16 id){
	for (int i = 0; i < m_WIDTH; i++){
		for (int j = 0; j < m_HEIGHT; j++){
			if (m_terrain[i][j]->getUnit() && m_terrain[i][j]->getUnit()->getID() == id){
				return std::pair<uint8, uint8>(i, j);
			}
		}
	}
	return std::pair<uint8, uint8>(-1, -1);
}

Unit* Map::getUnit(uint16 id){
	return m_unitList.find(id)->second;
}

Unit* Map::getUnitAt(uint8 x, uint8 y){
	return m_terrain[x][y]->getUnit();
}

Unit* Map::produceUnit(uint8 x, uint8 y, CPlayer* owner, UnitType type)
{
	Terrain* pos = getTerrainAt(x, y);
	if (pos->getUnit() == NULL)
	{
		UnitBuilder builder(type, owner, newUnitId());
		Unit* newUnit = builder.getResult();

		//Add unit to map
		m_terrain[x][y]->setUnit(newUnit);

		//Add unit to unit list
		m_unitList[newUnit->getID()] = newUnit;
		return newUnit;
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
	//ensure unit can move onto newPos
	else if (newPos->getMoveCost(unit->getMobilityType()) < 1){
		return false;
	}
	//TODO: pathfinding 
	//ensure newPos doesn't already have a unit.
	else if (newPos->getUnit() == NULL)
	{
		Terrain* oldPos = getTerrainUnderUnit(unit->getID());
		oldPos->setUnit(NULL);
		newPos->setUnit(unit);
		//todo: implement fuel (not yet implemented on client)
		return true;
	}
	return false;
}

bool Map::attackUnit(Unit* unit, uint8 new_x, uint8 new_y, Unit* target, int* targetdamage, int* returndamage)
{
	if (!unit->isActive() || getUnitAt(new_x, new_y) != target){
		return false;
	}
	if (moveUnit(unit, new_x, new_y))     //?
  	{
		//TODO: ammo check, range check
		unit->attack(target, targetdamage, returndamage);
		if (target->getHealth() == 0)
		{
			deleteUnit(target);
			delete target;
		}
		if (unit->getHealth() == 0)
		{
			deleteUnit(unit);
			delete unit;
		}
		return true;
		unit->deactivate();
	}
	return false;
}

bool Map::deleteUnit(Unit* unit)
{
	Terrain* pos = getTerrainUnderUnit(unit->getID());

	m_unitList.erase(unit->getID());
	pos->setUnit(NULL);
	return true;
}

bool Map::unitsRemain(CPlayer* player)
{
	for (auto u : m_unitList)
	{
		if (u.second->getOwner() == player)
		{
			return true;
		}
	}
	return false;
}

bool Map::captureStructure(uint8 x, uint8 y){
	Unit *unit = getUnitAt(x, y);
	Terrain *captureTerrain = getTerrainAt(x, y);
	if (!unit->isActive() || !captureTerrain->canBeCaptured()){
		return false;
	}

	bool captured = unit->capture();
	unit->deactivate();

	//Set owner if captured
	if (captured){
		m_terrain[x][y]->setOwner(unit->getOwner());
	}
	return captured;
}

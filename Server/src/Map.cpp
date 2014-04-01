
#include "Map.h"
#include "Terrain.h"
#include "TerrainBuilder.h"
#include "UnitBuilder.h"

Map::Map(uint8 width, uint8 height, uint8 mapID):
m_mapID(mapID),
m_HEIGHT(height),
m_WIDTH(width)
{
	initializeTerrain(mapID);
}

Map::Map(uint8 mapID):
m_mapID(mapID),
m_HEIGHT(DEFAULT_HEIGHT),
m_WIDTH(DEFAULT_WIDTH)
{
	//get data from mapID
	initializeTerrain(mapID);
}

Map::~Map(){
}

void Map::initializeTerrain(uint8 mapID){
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
	m_terrain[0][0]->setOwner(0);
	delete m_terrain[15][15];
	m_terrain[15][15] = factoryBuilder.getResult();
	m_terrain[0][0]->setOwner(1);
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

Unit* Map::produceUnit(uint8 x, uint8 y, UnitType type, uint8 owner){
	if (m_terrain[x][y]->getUnit() != NULL){
		return NULL;
	}
	
	//Create unit
	uint16 id = newUnitId();
	Unit * newUnit;
	UnitBuilder builder(type, owner, id);
	newUnit = builder.getResult();
	
	//Add unit to map
	m_terrain[x][y]->setUnit(newUnit);

	//Add unit to unit list
	m_unitList[id] = newUnit;
	return newUnit;
}

bool Map::moveUnit(Unit* unit, uint8 new_x, uint8 new_y){
	if (!unit->isActive() || m_terrain[new_x][new_y]->getUnit() != NULL){
		return false;
	}
	std::pair<int, int> currentPos = getUnitPos(unit->getID());
	//TODO: move validation
	m_terrain[new_x][new_y]->setUnit(unit);
	m_terrain[currentPos.first][currentPos.second]->setUnit(NULL);
	
	unit->deactivate();
	return true;
}

bool Map::attackUnit(Unit* initiator, uint8 new_x, uint8 new_y, Unit *target){
	if (!initiator->isActive()){
		return false;
	}

	std::pair<int, int> currentPos = getUnitPos(initiator->getAmmo());
	//TODO: attack calculations
	//		update health(s)
	//		delete units if health == 0
	
	initiator->deactivate();
	return true;
}

bool Map::deleteUnit(uint8 x, uint8 y){
	Unit *unit = getUnitAt(x, y);
	if (unit == NULL)
		return false;
	m_terrain[x][y]->setUnit(NULL);
	m_unitList.erase(unit->getID());
	return true;
}

bool Map::captureStructure(uint8 x, uint8 y, uint8 owner){
	Unit *unit = getUnitAt(x, y);
	//TODO: validate x,y can be captured
	if (!unit->isActive()){
		return false;
	}
	bool captured = unit->capture();
	unit->deactivate();

	//Set owner if captured
	if (captured){
		m_terrain[x][y]->setOwner(owner);
	}
	return captured;
}

uint16 Map::newUnitId(){
	uint16 newId = 1;
	for (UnitList::iterator it = m_unitList.begin(); it != m_unitList.end(); it++){
		if (it->second->getID() == newId){
			newId++;
			it = m_unitList.begin();
		}
	}
	return newId;
}
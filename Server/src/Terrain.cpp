#include "Terrain.h"


Terrain::Terrain(TerrainType type, int income, int defense, MoveCost cost):
m_TYPE(type),
m_INCOME(income),
m_defense(defense),
m_moveCost(cost)
{
	m_owner = 255;
	m_unit = NULL;
}


Terrain::~Terrain()
{
}

Unit *Terrain::getUnit(){
	return this->m_unit;
}

TerrainType Terrain::getType(){
	return this->m_TYPE;
}

Unit *Terrain::setUnit(Unit *unit){
	this->m_unit = unit;
	return this->m_unit;
}

uint8 Terrain::getOwner(){
	return this->m_owner;
}

void Terrain::setOwner(uint8 owner){
	this->m_owner = owner;
}
int Terrain::getMoveCost(MobilityType type){
	return this->m_moveCost.find(type)->second;
}

int Terrain::getDefense(){
	return this->m_defense;
}
#include "Terrain.h"


Terrain::Terrain(TerrainType type, int income, int defense, MoveCost cost):
m_TYPE(type),
m_INCOME(income),
m_defense(defense),
m_moveCost(cost)
{
	m_owner = NULL;
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

int Terrain::getIncome(){
	return this->m_INCOME;
}

Unit *Terrain::setUnit(Unit *unit){
	this->m_unit = unit;
	return this->m_unit;
}

CPlayer *Terrain::getOwner(){
	return this->m_owner;
}

void Terrain::setOwner(CPlayer *owner){
	this->m_owner = owner;
}
int Terrain::getMoveCost(MobilityType type){
	return this->m_moveCost.find(type)->second;
}

int Terrain::getDefense(){
	return this->m_defense;
}

bool Terrain::canBeCaptured(){
	if (this->m_TYPE == TerrainType::Airport ||
		this->m_TYPE == TerrainType::City ||
		this->m_TYPE == TerrainType::HQ ||
		this->m_TYPE == TerrainType::Factory){
		return true;
	}
	return false;
}
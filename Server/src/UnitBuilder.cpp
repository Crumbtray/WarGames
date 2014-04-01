#include "UnitBuilder.h"

/*  UnitBuilder is a helper function to set the const
*  variables in the Unit class. The corresponding
*  .cpp file essentially contains Unit definitions as
*  outlined in Static Data.
*  To create a new unit, pass the UnitType to the constructor
*  of UnitBuilder, and call getResult() to get a new
*  fully initialized unit.
*      ie: UnitBuilder *builder = new UnitBuilder(type);
*          Unit *newUnit = UnitBuilder.getResult();
*/
Unit *UnitBuilder::getResult(){
	if (m_unitType != UnitType::Invalid)
		return new Unit(m_unitType, m_owner, m_mobilityType, m_mobility, m_cost, m_range, m_vision, m_id);
	return NULL;
}

UnitBuilder::UnitBuilder(UnitType type, uint16 id){
	m_id = id;
	switch (type){
	case UnitType::Infantry:
		buildInfantry();
		break;
	case UnitType::Tank:
		buildTank();
		break;
	case UnitType::Artillery:
		buildArtillery();
		break;
	case UnitType::Recon:
		buildRecon();
		break;
	case UnitType::Bike:
		buildBike();
		break;
	case UnitType::Rockets:
		buildRockets();
		break;
	case UnitType::Mech:
		buildMech();
		break;
	case UnitType::Fighter:
		buildFighter();
		break;
	case UnitType::Duster:
		buildDuster();
		break;
	case UnitType::Bomber:
		buildBomber();
		break;
	case UnitType::Transport_Copter:
		buildTransportCopter();
		break;
	case UnitType::Battleship:
		buildBattleship();
		break;
	case UnitType::Cruiser:
		buildCruiser();
		break;
	case UnitType::Submarine:
		buildSubmarine();
		break;
	case UnitType::Lander:
		buildLander();
		break;
	case UnitType::Invalid:
	default:
		this->m_unitType = UnitType::Invalid;
		this->m_owner = NULL;
		this->m_mobilityType = MobilityType::None;
		this->m_mobility = NULL;
		this->m_cost = NULL;
		this->m_range = NULL;
		this->m_vision = NULL;
		this->m_id = id;
		break;
	}
}

UnitBuilder::~UnitBuilder(){

}

uint8 UnitBuilder::setOwner(){
	// TODO: get current player # from game and return it
	return 0;
}

void UnitBuilder::buildInfantry(){
	this->m_unitType = UnitType::Infantry;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Foot;
	this->m_mobility = 3;
	this->m_cost = 1500;
	this->m_range = 1;
	this->m_vision = 2;
}

void UnitBuilder::buildTank(){
	this->m_unitType = UnitType::Tank;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Tread;
	this->m_mobility = 6;
	this->m_cost = 7000;
	this->m_range = 1;
	this->m_vision = 3;
}

void UnitBuilder::buildArtillery(){
	this->m_unitType = UnitType::Artillery;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Tread;
	this->m_mobility = 5;
	this->m_cost = 6000;
	this->m_range = 23;
	this->m_vision = 3;
}

void UnitBuilder::buildRecon(){
	this->m_unitType = UnitType::Recon;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Tire;
	this->m_mobility = 8;
	this->m_cost = 4000;
	this->m_range = 1;
	this->m_vision = 5;
}

void UnitBuilder::buildBike(){
	this->m_unitType = UnitType::Bike;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Tire;
	this->m_mobility = 5;
	this->m_cost = 2500;
	this->m_range = 1;
	this->m_vision = 2;
}

void UnitBuilder::buildRockets(){
	this->m_unitType = UnitType::Rockets;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Tire;
	this->m_mobility = 5;
	this->m_cost = 15000;
	this->m_range = 35;
	this->m_vision = 3;
}

void UnitBuilder::buildMech(){
	this->m_unitType = UnitType::Mech;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Foot;
	this->m_mobility = 2;
	this->m_cost = 2500;
	this->m_range = 1;
	this->m_vision = 2;
}

void UnitBuilder::buildFighter(){
	this->m_unitType = UnitType::Fighter;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Air;
	this->m_mobility = 9;
	this->m_cost = 20000;
	this->m_range = 1;
	this->m_vision = 5;
}

void UnitBuilder::buildDuster(){
	this->m_unitType = UnitType::Duster;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Air;
	this->m_mobility = 8;
	this->m_cost = 13000;
	this->m_range = 1;
	this->m_vision = 4;
}

void UnitBuilder::buildBomber(){
	this->m_unitType = UnitType::Bomber;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Air;
	this->m_mobility = 7;
	this->m_cost = 20000;
	this->m_range = 1;
	this->m_vision = 3;
}

void UnitBuilder::buildTransportCopter(){
	this->m_unitType = UnitType::Transport_Copter;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Air;
	this->m_mobility = 6;
	this->m_cost = 5000;
	this->m_range = 0;
	this->m_vision = 1;
}

void UnitBuilder::buildBattleship(){
	this->m_unitType = UnitType::Battleship;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Boat;
	this->m_mobility = 5;
	this->m_cost = 25000;
	this->m_range = 35;
	this->m_vision = 5;
}

void UnitBuilder::buildCruiser(){
	this->m_unitType = UnitType::Cruiser;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Boat;
	this->m_mobility = 6;
	this->m_cost = 16000;
	this->m_range = 1;
	this->m_vision = 5;
}

void UnitBuilder::buildSubmarine(){
	this->m_unitType = UnitType::Submarine;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Boat;
	this->m_mobility = 6;
	this->m_cost = 20000;
	this->m_range = 1;
	this->m_vision = 5;
}

void UnitBuilder::buildLander(){
	this->m_unitType = UnitType::Lander;
	this->m_owner = setOwner();
	this->m_mobilityType = MobilityType::Boat;
	this->m_mobility = 6;
	this->m_cost = 10000;
	this->m_range = 0;
	this->m_vision = 1;
}

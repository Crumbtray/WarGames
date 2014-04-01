#pragma once

#include "lib/cbasetypes.h"

#include "TypeEnums.h"
#include "Unit.h"

/*  UnitBuilder is a helper function to set the const 
 *  variables in the Unit class. The corresponding
 *  .cpp file essentially contains Unit definitions as
 *  outlined in Static Data.
 *  To create a new unit, pass the UnitType to the constructor
 *  of UnitBuilder, and call getResult() to get a new 
 *  fully initialized unit.
 */
class UnitBuilder
{
private:
	UnitType m_unitType;
	uint8 m_owner;
	MobilityType m_mobilityType;
	int m_mobility;
	int m_cost;
	int m_range;
	int m_vision;
	uint16 m_id;

public:
	Unit *getResult();

	UnitBuilder(UnitType type, uint16 id);
	~UnitBuilder();

private:
	uint8 setOwner();
	void buildInfantry();
	void buildTank();
	void buildArtillery();
	void buildRecon();
	void buildBike();
	void buildRockets();
	void buildMech();
	void buildFighter();
	void buildDuster();
	void buildBomber();
	void buildTransportCopter();
	void buildBattleship();
	void buildCruiser();
	void buildSubmarine();
	void buildLander();
};


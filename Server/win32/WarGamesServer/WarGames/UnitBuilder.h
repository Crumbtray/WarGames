#pragma once

#include "TypeEnums.h"
#include "Unit.h"

/*  UnitBuilder is called from the default constructor 
 *  in Unit to set the const variables. The corresponding
 *  .cpp file essentially contains Unit definitions as
 *  outlined in Static Data.
 */
class UnitBuilder
{
private:
	UnitType unitType;
	int owner;
	MobilityType mobilityType;
	int mobility;
	int cost;
	int range;
	int vision;

public:
	Unit getResult();

	UnitBuilder(UnitType type);
	~UnitBuilder();

private:
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


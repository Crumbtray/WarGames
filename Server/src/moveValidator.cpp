
#include "moveValidator.h"

bool MoveValidator::isMoveValid(int old_x, int old_y, int new_x, int new_y, Unit *unit, Map *gameMap)
{
	return false;
}

CoordinateList MoveValidator::validLocations(int x, int y, Unit *unit, Map *gameMap){
	CoordinateList validLocations = CoordinateList();
	// Maximum possible area to move in one direction is based on mobility.
	int remainingMobility = unit->getMobility();
	Coordinate beginningCoord = Coordinate(x, y);
	validLocations.push_back(beginningCoord);

	while (remainingMobility > 0)
	{
		CoordinateList neighbours = CoordinateList();
		for (Coordinate validCoord : validLocations)
		{
			CoordinateList potentialNeighbours = getNeighbours(validCoord.first, validCoord.second, unit, gameMap);
			for (Coordinate potentialNeighbour : potentialNeighbours)
			{
				if (std::find(validLocations.begin(), validLocations.end(), potentialNeighbour) != validLocations.end())
				{
					neighbours.push_back(potentialNeighbour);
				}
			}
		}
		for (Coordinate neighbourCoord : neighbours)
		{
			if (std::find(validLocations.begin(), validLocations.end(), neighbourCoord) != validLocations.end())
			{
				Terrain *associatedTerrain = gameMap->getTerrainAt(neighbourCoord.first, neighbourCoord.second);
				if (associatedTerrain->canMove(unit))
				{
					// Unit can potentially move onto this square, let's see if it has enough mobility to get onto the square
					int moveCost = associatedTerrain->getMoveCost(unit->getMobilityType());
					if (remainingMobility >= moveCost)
					{
						// Unit can move onto this square.
						// Check if it's occupied.
						if (gameMap->getUnitAt(neighbourCoord.first, neighbourCoord.second) == NULL)
						{
							// Unoccupied. Add it to the list.
							validLocations.push_back(neighbourCoord);
						}

					}
				}
			}

		}

		remainingMobility = remainingMobility - 1;
	}

	return validLocations;
}


CoordinateList MoveValidator::getNeighbours(int old_x, int old_y, Unit *playerUnit, Map *gameMap)
{
	CoordinateList neighbours = CoordinateList();
	CoordinateList potentialNeighbours = CoordinateList();
	Coordinate northNeighbour = Coordinate(old_x, old_y - 1);
	Coordinate southNeighbour = Coordinate(old_x, old_y + 1);
	Coordinate eastNeighbour = Coordinate(old_x + 1, old_y);
	Coordinate westNeighbour = Coordinate(old_x - 1, old_y);
	potentialNeighbours.push_back(northNeighbour);
	potentialNeighbours.push_back(southNeighbour);
	potentialNeighbours.push_back(eastNeighbour);
	potentialNeighbours.push_back(westNeighbour);

	for (Coordinate coordinate : potentialNeighbours)
	{
		if (coordinate.first <= 15 && coordinate.first >= 0 && coordinate.second <= 15 && coordinate.second >= 0)
		{
			// Check if neighbour has enemy on it.
			if (gameMap->getUnitAt(coordinate.first, coordinate.second) != NULL)
			{
				// Someone's here!
				Unit *unit = gameMap->getUnitAt(coordinate.first, coordinate.second);
				if (unit->getOwner() == playerUnit->getOwner())
				{
					// Friendly
					neighbours.push_back(coordinate);
				}
			}
			else
			{
				neighbours.push_back(coordinate);
			}
		}
	}

	return neighbours;
}

CoordinateList MoveValidator::getAttackableCoordinates(int old_x, int old_y, Unit *playerUnit, Map *gameMap)
{
	CoordinateList attackableCoords = CoordinateList();
	//if range > 1, unit can either move or attack, not both
	if (!playerUnit->getMaxRange() > 1){ //equivalent to "canMoveAndAttack" on client side
		CoordinateList moveableSpots = validLocations(old_x, old_y, playerUnit, gameMap);
		for (Coordinate moveable : moveableSpots){
			for (Coordinate attackableCoordinate : getAttackableNeighbours(moveable, playerUnit, gameMap)){
				if (std::find(attackableCoords.begin(), attackableCoords.end(), attackableCoordinate) != attackableCoords.end()){
					attackableCoords.push_back(attackableCoordinate);
				}
			}
		}
	}
	else
	{

	}
	return attackableCoords;
}

CoordinateList MoveValidator::getAttackableNeighbours(Coordinate startingPosition, Unit *playerUnit, Map *gameMap)
{
	CoordinateList validAttacks = CoordinateList();
	int northernY = startingPosition.second - 1;
	int southernY = startingPosition.second + 1;
	int westernX = startingPosition.first - 1;
	int easternX = startingPosition.first + 1;

	// Check North
	if (northernY >= 0)
	{
		if (gameMap->getUnitAt(startingPosition.first, northernY) != NULL)
		{
			Unit *potentialEnemyUnit = gameMap->getUnitAt(startingPosition.first, northernY);
			if (potentialEnemyUnit->getOwner() != playerUnit->getOwner())
			{
				validAttacks.push_back(Coordinate(startingPosition.first, northernY));
			}
		}
	}
	// Check South
	if (southernY <= 15)
	{
		if (gameMap->getUnitAt(startingPosition.first, southernY) != NULL)
		{
			Unit *potentialEnemyUnit = gameMap->getUnitAt(startingPosition.first, southernY);
			if (potentialEnemyUnit->getOwner() != playerUnit->getOwner())
			{
				validAttacks.push_back(Coordinate(startingPosition.first, southernY));
			}
		}
	}
	//Check West
	if (westernX >= 0)
	{
		if (gameMap->getUnitAt(westernX, startingPosition.second) != NULL)
		{
			Unit *potentialEnemyUnit = gameMap->getUnitAt(westernX, startingPosition.second);
			if (potentialEnemyUnit->getOwner() != playerUnit->getOwner())
			{
				validAttacks.push_back(Coordinate(westernX, startingPosition.second));
			}
		}
	}
	// Check East
	if (easternX <= 15)
	{
		if (gameMap->getUnitAt(easternX, startingPosition.second) != NULL)
		{
			Unit *potentialEnemyUnit = gameMap->getUnitAt(easternX, startingPosition.second);
			if (potentialEnemyUnit->getOwner() != playerUnit->getOwner())
			{
				validAttacks.push_back(Coordinate(easternX, startingPosition.second));
			}
		}
	}

	return validAttacks;
}


bool MoveValidator::isNeighbour(Coordinate a, Coordinate b){
	if (std::abs(a.first - b.first) == 1 && (a.second == b.second))
	{
		return true;
	}
	else if (std::abs(a.second - b.second) == 1 && (a.first == b.first))
	{
		return true;
	}
	else
	{
		return false;
	}
}

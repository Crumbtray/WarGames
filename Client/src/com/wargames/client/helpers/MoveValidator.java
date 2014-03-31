package com.wargames.client.helpers;

import java.util.ArrayList;
import java.util.HashSet;

import com.wargames.client.model.*;

public class MoveValidator {

	public static boolean isMoveValid(int startX, int startY, int destX, int destY, Unit unit, Map gameMap)
	{
		return false;
	}
		
	public static ArrayList<Coordinate> validLocations(int startX, int startY, Unit unit, Map gameMap)
	{
		ArrayList<Coordinate> validLocations = new ArrayList<Coordinate>();
		// Maximum possible area to move in one direction is based on mobility.
		int remainingMobility = unit.getMobility();
		Coordinate beginningCoord = new Coordinate(startX, startY);
		validLocations.add(beginningCoord);

		while(remainingMobility > 0)
		{
			ArrayList<Coordinate> neighbours = new ArrayList<Coordinate>();
			for(Coordinate validCoord : validLocations)
			{
				ArrayList<Coordinate> potentialNeighbours = getNeighbours(validCoord.x, validCoord.y);
				for(Coordinate potentialNeighbour : potentialNeighbours)
				{
					if(!validLocations.contains(potentialNeighbour))
					{
						neighbours.add(potentialNeighbour);
					}
				}
			}
			for(Coordinate neighbourCoord : neighbours)
			{
				if(!validLocations.contains(neighbourCoord))
				{
					Terrain associatedTerrain = gameMap.getTerrainAt(neighbourCoord.x, neighbourCoord.y);
					if(associatedTerrain.canMove(unit))
					{
						// Unit can potentially move onto this square, let's see if it has enough mobility to get onto the square
						int moveCost = associatedTerrain.getMoveCost(unit);
						if(remainingMobility >= moveCost)
						{
							// Unit can move onto this square.  Add it to the list.
							validLocations.add(neighbourCoord);
						}
					}
				}
				
			}
			
			remainingMobility = remainingMobility - 1;
		}
		
		return validLocations;
	}
	
	/**
	 * Gets the neighbours of the given points.
	 * @param startX
	 * @param startY
	 * @return
	 */
	public static ArrayList<Coordinate> getNeighbours(int startX, int startY)
	{
		ArrayList<Coordinate> neighbours = new ArrayList<Coordinate>();
		ArrayList<Coordinate> potentialNeighbours = new ArrayList<Coordinate>();
		Coordinate northNeighbour = new Coordinate(startX, startY - 1);
		Coordinate southNeighbour = new Coordinate(startX, startY + 1);
		Coordinate eastNeighbour = new Coordinate(startX + 1, startY);
		Coordinate westNeighbour = new Coordinate(startX - 1, startY);
		potentialNeighbours.add(northNeighbour);
		potentialNeighbours.add(southNeighbour);
		potentialNeighbours.add(eastNeighbour);
		potentialNeighbours.add(westNeighbour);
		
		for(Coordinate coordinate : potentialNeighbours)
		{
			if(coordinate.x <= 15 && coordinate.x >= 0 && coordinate.y <= 15 && coordinate.y >= 0)
			{
				neighbours.add(coordinate);
			}
		}
				
		return neighbours;
	}
}

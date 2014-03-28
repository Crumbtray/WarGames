package com.wargames.client.helpers;

import java.util.ArrayList;

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
		for(int i = 0; i < unit.getMobility(); i++)
		{
			// We loop through every possible direction.
		}
		
		return validLocations;
	}
	
	private class Coordinate{
		private int x;
		private int y;
		
		private Coordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
	}
}

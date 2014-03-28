package com.wargames.client.model;
import java.util.ArrayList;


public class Game {
	public Map gameMap;
	public ArrayList<Player> players;
	public Player currentTurn;
	
	public Game(Map gameMap)
	{
		this.gameMap = gameMap;
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player player)
	{
		this.players.add(player);
	}
	
	/**
	 * Moves a unit from its initial position to its final position.
	 * @param xStart
	 * @param yStart
	 * @param xDest
	 * @param yDest
	 */
	public void moveUnit(int xStart, int yStart, int xDest, int yDest)
	{
		try{
			gameMap.moveUnit(xStart, yStart, xDest, yDest);
		}
		catch(MapException e)
		{
			System.out.println("Game: Error in moving unit.  This should never happen.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Uses the unit to capture a structure.
	 * @param x The X coordinate of the structure.
	 * @param y The Y coordinate of the structure.
	 * @param capturer The Unit that is trying to capture.
	 */
	public void captureStructure(int x, int y, Unit capturer)
	{
		try {
			this.gameMap.captureStructure(x, y, capturer);
		}
		catch(MapException e)
		{
			System.out.println("Game: Error in capturing structure.  This should never happen.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Damages a unit on the field.
	 * @param x
	 * @param y
	 * @param damageAmount
	 */
	public void damageUnit(int x, int y, int damageAmount)
	{
		Unit targetUnit = gameMap.getUnitAt(x, y);
		if(damageAmount > targetUnit.health)
		{
			this.gameMap.deleteUnitAt(x, y);
		}
		else
		{
			targetUnit.damageUnit(damageAmount);
		}
	}
	
	/**
	 * Creates a new unit at the X and Y location.
	 * X and Y should be a factory.
	 * @param x
	 * @param y
	 * @param unitType
	 * @param owner
	 */
	public void createUnit(int x, int y, UnitType unitType, Player owner)
	{
		Unit newUnit = null;
		switch(unitType)
		{
			case SOLDIER:
				newUnit = Unit.createSoldier(owner);
				break;
			case TANK:
				newUnit = Unit.createTank(owner);
				break;
			case ARTILLERY:
				newUnit = Unit.createArtillery(owner);
				break;
		}
		try {
			this.gameMap.createUnit(x, y, newUnit);
		}
		catch(MapException e)
		{
			System.out.println("Game: Error in creating new unit. This should never happen.");
			e.printStackTrace();
		}
	}
	
	public void setTurn(Player player)
	{
		this.currentTurn = player;
	}
}

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
	
	public void moveUnit(int xStart, int yStart, int xDest, int yDest)
	{
		
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param capturer
	 */
	public void captureStructure(int x, int y, Unit capturer)
	{
		Structure capturableStructure = (Structure) gameMap.getTerrainAt(x, y);
		// Gotta do some checking here.
		// We shall see.
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
}

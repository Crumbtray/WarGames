package com.wargames.client.model;

/**
 * Representation of a Unit.
 * @author Clinton
 *
 */
public class Unit {
	private UnitType unitType;
	public int health;
	private MoveType moveType;
	private int range;
	private int mobility;
	private String description;
	private Player owner;
	private boolean isActive;
	
	private Unit(UnitType unitType, int health, MoveType moveType, int range, int mobility, String description, Player owner)
	{
		this.unitType = unitType;
		this.health = health;
		this.moveType = moveType;
		this.range = range;
		this.mobility = mobility;
		this.description = description;
		this.owner = owner;
	}
	
	/**
	 * Creates a new Soldier Unit for the given player.
	 * @return A Unit object containing the statistics for Soldier.
	 */
	public static Unit createSoldier(Player owner) 
	{
		Unit newUnit = new Unit(UnitType.SOLDIER, 10, MoveType.FOOT, 1, 3, "The cheapest unit.  They can capture bases.", owner);
		newUnit.deactivate();
		return newUnit;
	}
	
	/**
	 * Creates a new Tank Unit for the given player.
	 * @param owner
	 * @return A Unit object containing the statistics for the tank.
	 */
	public static Unit createTank(Player owner)
	{
		Unit newUnit = new Unit(UnitType.TANK, 10, MoveType.TANK, 1, 6, "Tanks are heavy damage dealers.", owner);
		newUnit.deactivate();
		return newUnit;
	}
	
	public static Unit createArtillery(Player owner)
	{
		Unit newUnit = new Unit(UnitType.ARTILLERY, 10, MoveType.TIRE, 1, 5, "Artillery are ranged attackers.", owner);
		newUnit.deactivate();
		return newUnit;
	}
	
	public UnitType getUnitType() 
	{
		return this.unitType;
	}
	
	public MoveType getMoveType()
	{
		return this.moveType;
	}
	
	public int getRange()
	{
		return this.range;
	}
	
	public int getMobility()
	{
		return this.mobility;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public Player getOwner()
	{
		return this.owner;
	}
	
	public boolean isActive()
	{
		return this.isActive;
	}
	
	public void activate()
	{
		this.isActive = true;
	}
	
	public void deactivate()
	{
		this.isActive = false;
	}
	
	public int damageUnit(int damage)
	{
		this.health = this.health - damage;
		return this.health;
	}
}
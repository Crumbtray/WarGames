package com.wargames.client.model;

import java.util.Hashtable;

public class Map {
	private Unit[][] unitMap;
	private Terrain[][] terrainMap;
	private int height;
	private int width;
	public Hashtable<UnitType, Integer> unitCost;
	
	public Map(int width, int height)
	{
		this.height = height;
		this.width = width;
		
		this.unitMap = new Unit[width][height];
		this.terrainMap = new Terrain[width][height];
		this.unitCost = new Hashtable<UnitType, Integer>();
	}

	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public Unit getUnitAt(int width, int height)
	{
		return this.unitMap[width][height];
	}
	
	public Unit deleteUnitAt(int x, int y)
	{
		Unit targetUnit = this.unitMap[x][y];
		this.unitMap[x][y] = null;
		return targetUnit;
	}
	
	public Terrain getTerrainAt(int width, int height)
	{
		return this.terrainMap[width][height];
	}
	
	/**
	 * Adds a terrain to the map at the specified location.
	 * Should be used in initialization only.
	 * @param width
	 * @param height
	 * @param terrain
	 * @throws MapException
	 */
	public void addTerrain(int width, int height, Terrain terrain) throws MapException
	{
		if (this.getTerrainAt(width, height) == null)
		{
			this.terrainMap[width][height] = terrain;
		}
		else
		{
			throw new MapException("MapException: Terrain already exists.  Try again.");
		}
	}
	
	/**
	 * Creates a specified unit at the location.
	 * @param x
	 * @param y
	 * @param unit
	 * @throws MapException
	 */
	public void createUnit(int x, int y, Unit unit) throws MapException
	{
		if(this.unitMap[x][y] == null)
		{
			if(this.terrainMap[x][y].terrainType.equals(TerrainType.Factory))
			{
				this.unitMap[x][y] = unit;
			}
			else
			{
				throw new MapException("MapException: Units can only be created on Factory locations.");
			}	
		}
		else
		{
			throw new MapException("MapException: There is already a unit in this location.");
		}
	}
}

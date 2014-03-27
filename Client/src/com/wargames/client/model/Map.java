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
	
	public Terrain getTerrainAt(int width, int height)
	{
		return this.terrainMap[width][height];
	}
	
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
}

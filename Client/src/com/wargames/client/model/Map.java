package com.wargames.client.model;

import java.util.Hashtable;

public class Map {
	private Unit[][] unitMap;
	private Terrain[][] terrainMap;
	private int height;
	private int width;
	public Hashtable<UnitType, Integer> unitCost;
	
	private Map(int width, int height)
	{
		this.height = height;
		this.width = width;
		
		this.unitMap = new Unit[width][height];
		this.terrainMap = new Terrain[width][height];
		this.unitCost = new Hashtable<UnitType, Integer>();
	}
	
	public static Map createNewMap()
	{
		Map newMap = new Map(16,16);
		return null;
	}
	
	public Unit getUnitAt(int width, int height)
	{
		return this.unitMap[width][height];
	}
	
	public Terrain getTerrainAt(int width, int height)
	{
		return this.terrainMap[width][height];
	}
	
	public void createTerrain(int width, int height, TerrainType terrainType)
	{
		if (this.getTerrainAt(width, height) == null)
		{
			Terrain newTerrain = null;
			
			switch(terrainType) {
			case Plain:
				newTerrain = Terrain.createPlainTerrain();
				break;

			case Mountain:
				newTerrain = Terrain.createMountainTerrain();
				break;

			case Road:
				newTerrain = Terrain.createRoadTerrain();
				break;

			case Wood:
				newTerrain = Terrain.createWoodTerrain();
				break;

			case Sea:
				newTerrain = Terrain.createSeaTerrain();
				break;

			default:
				// We should never hit this case.
				System.out.println("CreateTerrain: Hit the default terrain case.");
				break;

			}
			
			this.terrainMap[width][height] = newTerrain;
		}
		
		
	}
}

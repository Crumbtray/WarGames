package com.wargames.client.gui;

import java.awt.Graphics;

import com.wargames.client.model.Map;

public class GuiMap {
	private Map logicalMap;
	private GuiUnit[][] graphicalUnits;
	private GuiTerrain[][] graphicalTerrain;
	
	private int mapWidth;
	private int mapHeight;
	
	public GuiMap(Map gameMap)
	{
		this.logicalMap = gameMap;
		mapWidth = gameMap.getWidth();
		mapHeight = gameMap.getHeight();
		
		for(int i = 0; i < mapWidth; i++)
		{
			for(int j = 0; j < mapHeight; j++)
			{
				GuiTerrain newGuiTerrain = new GuiTerrain(gameMap.getTerrainAt(i, j), i, j);
				this.graphicalTerrain[i][j] = newGuiTerrain;
			}
		}
	}
	
	public void draw(Graphics g)
	{
		
	}
}

package com.wargames.client.gui;

import java.awt.Graphics;

import com.wargames.client.model.Map;

public class GuiMap {
	private Map logicalMap;
	private GuiUnit[][] graphicalUnits;
	private GuiTerrain[][] graphicalTerrain;
	
	private final int MapOffsetX = 44;
	private final int MapOffsetY = 44;
	private final int TILEWIDTH = 32;
	private final int TILEHEIGHT = 32;
	private int mapWidth;
	private int mapHeight;
	
	public GuiMap(Map gameMap)
	{
		this.logicalMap = gameMap;
		mapWidth = gameMap.getWidth();
		mapHeight = gameMap.getHeight();
		this.graphicalUnits = new GuiUnit[mapWidth][mapHeight];
		this.graphicalTerrain = new GuiTerrain[mapWidth][mapHeight];
		
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
		for(int i = 0; i < mapWidth; i++)
		{
			for(int j = 0; j < mapHeight; j++)
			{
				g.drawImage(this.graphicalTerrain[i][j].getImage(), i * TILEWIDTH + MapOffsetX, j * TILEHEIGHT + MapOffsetY, null);
			}
		}
	}
}

package com.wargames.client.gui;

import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;

import com.wargames.client.helpers.Coordinate;
import com.wargames.client.model.Map;
import com.wargames.client.model.MapException;

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
				GuiTerrain newGuiTerrain = new GuiTerrain(gameMap.getTerrainAt(i, j), i * TILEWIDTH + MapOffsetX, j * TILEHEIGHT + MapOffsetY);
				this.graphicalTerrain[i][j] = newGuiTerrain;
				if(gameMap.getUnitAt(i, j) != null)
				{
					this.graphicalUnits[i][j] = new GuiUnit(gameMap.getUnitAt(i,j), i * TILEWIDTH + MapOffsetX, j * TILEHEIGHT + MapOffsetY);
				}
			}
		}
	}
	
	public void draw(Graphics g)
	{
		for(int i = 0; i < mapWidth; i++)
		{
			for(int j = 0; j < mapHeight; j++)
			{
				GuiTerrain terrain = this.graphicalTerrain[i][j];
				g.drawImage(terrain.getImage(), terrain.getX(), terrain.getY(), null);
				if(graphicalUnits[i][j] != null)
				{
					GuiUnit unit = graphicalUnits[i][j];
					g.drawImage(unit.getImage(), unit.getX(), unit.getY(), null);		
				}
			}
		}
	}
	
	public GuiTerrain getTerrainAt(int x, int y) throws MapException
	{
		if(x < 44 || x > 556 || y < 44 || y > 556)
		{
			throw new MapException("MapException: X or Y is out of bounds.");
		}
		
		int realX = (x - MapOffsetX) / TILEWIDTH;
		int realY = (y - MapOffsetY) / TILEHEIGHT;
		
		System.out.println("Trying to get terrain at (" + realX + "," + realY + ")");
		return graphicalTerrain[realX][realY];
	}
	
	public GuiUnit getUnitAt(int x, int y) throws MapException
	{
		if(x < 44 || x > 556 || y < 44 || y > 556)
		{
			throw new MapException("MapException: X or Y is out of bounds.");
		}
		
		int realX = (x - MapOffsetX) / TILEWIDTH;
		int realY = (y - MapOffsetY) / TILEHEIGHT;
		
		System.out.println("Trying to get unit at (" + realX + "," + realY + ")");
		return graphicalUnits[realX][realY];
	}
	
	public Coordinate getCoordinateOfUnit(GuiUnit unit)
	{
		int graphicalX = unit.getX();
		int graphicalY = unit.getY();
		
		int logicalX = (graphicalX - MapOffsetX) / TILEWIDTH;
		int logicalY = (graphicalY - MapOffsetY) / TILEHEIGHT;
		
		Coordinate coordinate = new Coordinate(logicalX, logicalY);
		return coordinate;
	}
}

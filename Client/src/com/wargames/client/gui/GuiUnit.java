package com.wargames.client.gui;

import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

import com.wargames.client.model.Unit;

public class GuiUnit {
	private Image img;
	private int x;
	private int y;
	private Unit logicalUnit;
	
	public GuiUnit(Unit unit, int x, int y)
	{
		this.logicalUnit = unit;
		this.x = x;
		this.y = y;
		
		// Assign the img based on terrain type.
		String filename = "";
		
		switch(unit.getUnitType())
		{
			case SOLDIER:
				filename="soldier";
				break;
			case TANK:
				filename="tank";
				break;
			case ARTILLERY:
				filename="artillery";
				break;
		}
		filename = filename.concat("_" + unit.getOwner().color + "01.png");
		URL urlUnitImage = getClass().getResource("/com/wargames/client/gui/img/" + filename);
		this.img = new ImageIcon(urlUnitImage).getImage();
	}
	
	public Unit getLogicalUnit()
	{
		return this.logicalUnit;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public Image getImage()
	{
		return this.img;
	}
}

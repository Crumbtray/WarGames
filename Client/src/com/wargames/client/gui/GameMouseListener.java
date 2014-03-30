package com.wargames.client.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.wargames.client.model.MapException;

public class GameMouseListener implements MouseListener{

	private GameClientGui client;
	
	public GameMouseListener(GameClientGui client)
	{
		this.client = client;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseXPosition = e.getX();
		int mouseYPosition = e.getY();
		
		System.out.println("Mouse at (" + mouseXPosition + "," + mouseYPosition + ")");
		
		if(mouseXPosition >= 44 && mouseXPosition < 556)
		{
			if(mouseYPosition >= 44 && mouseYPosition < 556)
			{
				try {
					this.client.selectedTerrain = this.client.guiMap.getTerrainAt(mouseXPosition, mouseYPosition);
					this.client.selectedUnit = this.client.guiMap.getUnitAt(mouseXPosition, mouseYPosition);
					this.client.repaint();
				} catch (MapException e1) {
					// TODO Auto-generated catch block
					System.out.println("THIS SHOULD NEVER HAPPEN.");
					e1.printStackTrace();
				}
			}
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

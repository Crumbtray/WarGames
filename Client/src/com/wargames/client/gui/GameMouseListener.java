package com.wargames.client.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import com.wargames.client.helpers.Coordinate;
import com.wargames.client.helpers.MoveValidator;
import com.wargames.client.model.MapException;
import com.wargames.client.model.Unit;

public class GameMouseListener implements MouseListener{

	public MouseState mouseState;
	private GameClientGui client;
	public Coordinate lastClick;
	
	public GameMouseListener(GameClientGui client)
	{
		this.mouseState = MouseState.NothingSelected;
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
			
		if(mouseXPosition >= 44 && mouseXPosition < 556)
		{
			if(mouseYPosition >= 44 && mouseYPosition < 556)
			{
				// We're manipulating the map!
				switch(this.mouseState)
				{
					case NothingSelected:
						handleOnNothingSelected(e);
						break;
					case UnitSelected:
						handleOnUnitSelected(e);
						break;
					case FactorySelected:
						handleOnFactorySelected(e);
						break;
					case UnitActionSelection:
						handleOnUnitActionSelection(e);
						break;
					case FindingAttackTarget:
						handleOnUnitAttackSelection(e);
						break;
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

	private void handleOnUnitSelected(MouseEvent e)
	{
		int mouseXPosition = e.getX();
		int mouseYPosition = e.getY();
		Coordinate unitCoordinate = this.client.guiMap.getCoordinateOfUnit(this.client.selectedUnit);
		ArrayList<Coordinate> validLocations = MoveValidator.validLocations(unitCoordinate.x, unitCoordinate.y, this.client.selectedUnit.getLogicalUnit(), this.client.game.gameMap);
		ArrayList<Coordinate> attackableUnits = MoveValidator.getAttackableCoordinates(unitCoordinate.x, unitCoordinate.y, this.client.selectedUnit.getLogicalUnit(), this.client.game.gameMap);
		
		// If we click anywhere outside the valid locations, we go back to nothing selected state.
		Coordinate mouseCoordinate = client.guiMap.getCoordinate(mouseXPosition, mouseYPosition);
		if(this.client.selectedUnit.getLogicalUnit().getOwner() == this.client.game.currentTurn)
		{
			if(attackableUnits.contains(mouseCoordinate))
			{
				// Basic attack
				System.out.println("Basic attack on (" + mouseCoordinate.x + "," + mouseCoordinate.y + ")");
			}
			if(validLocations.contains(mouseCoordinate))
			{
				ArrayList<UnitActionType> possibleActions = new ArrayList<UnitActionType>();
				// If it's a square adjacent to an attackable unit, valid actions are Move and Attack
				for(Coordinate attackableUnit : attackableUnits)
				{
					if(MoveValidator.isNeighbour(mouseCoordinate, attackableUnit))
					{
						if(client.selectedUnit.getLogicalUnit().canMoveAndAttack())
						{
							possibleActions.add(UnitActionType.Attack);
						}
					}
				}
				
				// Open the action window here.
				// Get all possible actions

				possibleActions.add(UnitActionType.Move);
				
				ActionWindow actionWindow = new ActionWindow(client, e, possibleActions, this);
			}
		}
		else
		{
			// Otherwise, close the selection, treat it as if nothing happened.
			this.mouseState = MouseState.NothingSelected;
			handleOnNothingSelected(e);
		}
	}
	
	private void handleOnFactorySelected(MouseEvent e)
	{
		
	}
	
	private void handleOnUnitActionSelection(MouseEvent e)
	{
		
	}
	
	private void handleOnUnitAttackSelection(MouseEvent e)
	{
		Coordinate victimCoordinate = this.client.guiMap.getCoordinate(e.getX(), e.getY());
		System.out.println("From (" + lastClick.x + "," + lastClick.y + ")");
		System.out.println("Attacking unit at (" + victimCoordinate.x + "," + victimCoordinate.y + ")" );
		Coordinate moveCoordinate = new Coordinate(lastClick.x, lastClick.y);
		
		//Attack code here!
		this.client.guiMap.moveAttackUnit(this.client.selectedUnit, moveCoordinate, victimCoordinate);
		
		this.mouseState = MouseState.NothingSelected;
		this.client.repaint();
	}
	
	private void handleOnNothingSelected(MouseEvent e)
	{
		int mouseXPosition = e.getX();
		int mouseYPosition = e.getY();
		try {
			this.client.selectedTerrain = this.client.guiMap.getTerrainAt(mouseXPosition, mouseYPosition);
			this.client.selectedUnit = this.client.guiMap.getUnitAt(mouseXPosition, mouseYPosition);
			if(this.client.selectedUnit != null && this.client.selectedUnit.getLogicalUnit().isActive())
			{
				this.mouseState = MouseState.UnitSelected;
			}
			this.client.repaint();
		} catch (MapException e1) {
			// TODO Auto-generated catch block
			System.out.println("THIS SHOULD NEVER HAPPEN.");
			e1.printStackTrace();
		}
	}
}

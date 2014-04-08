package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.GameClientGui;
import com.wargames.client.helpers.Coordinate;
import com.wargames.client.model.Player;
import com.wargames.client.model.Unit;
import com.wargames.client.model.UnitType;

public class EntityUpdatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JPanel client) {
		short id = buff.getShort();
		byte unitType = buff.get();
		byte color = buff.get();
		byte owner = buff.get();
		byte xPos = buff.get();
		byte yPos = buff.get();
		byte hp = buff.get();
		
		//TODO: do stuff with response

		GameClientGui clientWindow = (GameClientGui) client;
		
		Coordinate targetUnitCoord = clientWindow.guiMap.logicalGame.gameMap.findCoordinateByUnitID(id);
		if (targetUnitCoord == null)
		{
			UnitType newUnitType;
			switch(unitType)
			{
				case 1:
					newUnitType = UnitType.SOLDIER;
					break;
				case 2:
					newUnitType = UnitType.TANK;
					break;
				default:
					newUnitType = UnitType.SOLDIER;
			}
			
			Coordinate newUnitCoordinates = new Coordinate(xPos, yPos);
			
			Player playerOwner = clientWindow.guiMap.logicalGame.gameMap.findPlayerByNumber(owner);
			// We have to create a unit.
			clientWindow.guiMap.CreateUnit(newUnitType, playerOwner, id, newUnitCoordinates);
		}
		else
		{
			// We will update the units with information
			Unit targetUnit = clientWindow.guiMap.logicalGame.gameMap.getUnitAt(targetUnitCoord.x, targetUnitCoord.y);
			
			// Check if health is correct.
			if(targetUnit.health != hp)
			{
				targetUnit.health = hp;
			}
			
			// Lastly check if the position is correct.
			if(targetUnitCoord.x != xPos || targetUnitCoord.y != yPos)
			{
				// Coordinates are different.  Move the unit.
				clientWindow.guiMap.logicalGame.moveUnit(targetUnitCoord.x, targetUnitCoord.y, xPos, yPos);
				clientWindow.guiMap.UpdateGui();
			}
		}
	}
	

}

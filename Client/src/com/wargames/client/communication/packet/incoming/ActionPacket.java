package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.GameClientGui;
import com.wargames.client.helpers.Coordinate;

public class ActionPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		short initiatorID = buff.getShort();
		byte xPos = buff.get();
		byte yPos = buff.get();
		byte action = buff.get();
		short targetID = buff.getShort();
		byte selfdmg = buff.get();
		byte targetdmg = buff.get();
		
		//TODO: do stuff with response

		GameClientGui gameClient = (GameClientGui) client;
		
		if(action == 0)
		{
			// Move
			Coordinate unitCoord = gameClient.guiMap.logicalGame.gameMap.findCoordinateByUnitID(initiatorID);
			gameClient.guiMap.logicalGame.moveUnit(unitCoord.x, unitCoord.y, xPos, yPos);
			gameClient.guiMap.UpdateGui();
		}
		if(action == 1)
		{
			// Attack
			Coordinate targetCoord = gameClient.guiMap.logicalGame.gameMap.findCoordinateByUnitID(targetID);

			gameClient.guiMap.logicalGame.damageUnit(xPos, yPos, selfdmg);
			
			gameClient.guiMap.logicalGame.damageUnit(targetCoord.x, targetCoord.y, targetdmg);
			
			gameClient.guiMap.UpdateGui();
		}
		
		// We ignore if action == 2, or 3.
		return client;
	}

}

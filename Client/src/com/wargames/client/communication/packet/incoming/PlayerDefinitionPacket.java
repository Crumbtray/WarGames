package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.GameClientGui;
import com.wargames.client.model.Player;

public class PlayerDefinitionPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JPanel client) {
		int id = buff.getInt();
		byte[] name = new byte[10];
		for (int j = 0; j < 10; j++)
		{
			name[j] = buff.get();
		}
		byte color = buff.get();
		byte team = buff.get();
		byte number = buff.get();
		
		//We update the player here.
		String newName = new String(name);

		GameClientGui gui = (GameClientGui) client;
		Player player = gui.guiMap.logicalGame.gameMap.findPlayerByNumber(number);
		if(player.name != newName)
		{
			player.name = newName;
		}
		if(player.teamNumber != team)
		{
			player.teamNumber = team;
		}
		
		if(player.ID != id)
		{
			player.ID = id;
		}
	}


}

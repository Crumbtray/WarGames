package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.GameClientGui;
import com.wargames.client.model.Player;

public class TurnChangePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JPanel client) {
		byte playernumber = buff.get();
		
		GameClientGui window = (GameClientGui) client;
		//TODO: do stuff with response

		Player nextTurn = null;
		for(Player player : window.guiMap.logicalGame.gameMap.players)
		{
			if(player.playerNumber == playernumber)
			{
				nextTurn = player;
			}
		}
		window.guiMap.logicalGame.setTurn(nextTurn);
	}

}

package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.GameClientGui;
import com.wargames.client.model.Player;

public class GameOverPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte winnerNumber = buff.get();
		int winnerID = buff.getInt();
		
		//TODO: do stuff with response
		Player winner = null;
		GameClientGui window = (GameClientGui) client;
		for(Player player : window.guiMap.logicalGame.gameMap.players)
		{
			if(player.playerNumber == winnerNumber)
			{
				winner = player;
			}
			else
			{
				if(player.ID == winnerID)
				{
					winner = player;
				}
			}
		}
		window.endGame(winner);
		return client;
	}

}

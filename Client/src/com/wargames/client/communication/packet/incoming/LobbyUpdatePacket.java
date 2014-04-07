package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.wargames.client.gui.LobbyWindow;
import com.wargames.client.model.Player;

public class LobbyUpdatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JPanel client) {
		byte maxSize = buff.get();
		byte mapID = buff.get();
		byte players = buff.get();
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		
		for (int i = 0; i < players; i++)
		{
			int id = buff.getInt();
			byte team = buff.get();
			byte color = buff.get();
			byte[] name = new byte[10];
			for (int j = 0; j < 10; j++)
			{
				name[j] = buff.get();
			}
			
			//TODO: do stuff with response
			Player player;
			if(color == 1)
			{
				player = new Player(id, new String(name), team, i, "red");
			}
			else
			{
				player = new Player(id, new String(name), team, i, "blue");
			}
			playerList.add(player);
		}
	
		LobbyWindow window = (LobbyWindow) client;
		window.lobby.setPlayers(playerList);
		window.lobby.setMapID(mapID);
		window.lobby.setMaxPlayers(maxSize);
	}
}

package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.wargames.client.gui.LobbyListWindow;
import com.wargames.client.gui.LobbyWindow;
import com.wargames.client.model.Lobby;
import com.wargames.client.model.Player;

public class LobbyUpdatePacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
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
	
		if(client.getClass().getSimpleName().contains("LobbyList"))
		{
			// Client is a LobbyListWindow.
			// We need to create and make it a LobbyWindow.
			LobbyListWindow window = (LobbyListWindow) client;
			Lobby selectedLobby = new Lobby(0, playerList.size(), (int)maxSize, (int)mapID, playerList.get(0).name);
			selectedLobby.setPlayers(playerList);
			String currentPlayer = window.username;
			// Swap out screens
			JFrame topframe = (JFrame) SwingUtilities.getWindowAncestor(client);
			topframe.dispose();
			return new LobbyWindow(selectedLobby, currentPlayer);
		}
		else
		{
			LobbyWindow window = (LobbyWindow) client;
			window.lobby.setPlayers(playerList);
			window.lobby.setMapID(mapID);
			window.lobby.setMaxPlayers(maxSize);
			return window;
		}
	}
}

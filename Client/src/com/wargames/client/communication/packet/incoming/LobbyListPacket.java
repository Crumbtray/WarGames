package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.wargames.client.gui.LobbyListWindow;
import com.wargames.client.gui.LoginWindow;
import com.wargames.client.model.Lobby;

public class LobbyListPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte numlobbies = buff.get();
		ArrayList<Lobby> lobbies = new ArrayList<Lobby>();
		for (int i = 0; i < numlobbies; i++)
		{
			short id = buff.getShort();
			byte size = buff.get();
			byte maxSize = buff.get();
			byte mapID = buff.get();
			byte[] name = new byte[10];
			for (int j = 0; j < 10; j++)
			{
				name[j] = buff.get();
			}
			
			
			//TODO: do stuff with response
			Lobby newLobby = new Lobby(id, size, maxSize, mapID, new String(name).trim());
			lobbies.add(newLobby);
		}
		
		LoginWindow window = (LoginWindow) client;
		String username = window.username.getText();
		
		// Get rid of current Login Window, and show the lobby window.
		JFrame topframe = (JFrame) SwingUtilities.getWindowAncestor(client);
		topframe.dispose();
		return new LobbyListWindow(lobbies, username);
	}

}

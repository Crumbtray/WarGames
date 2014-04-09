package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.wargames.client.gui.GameClientGui;
import com.wargames.client.gui.LobbyWindow;
import com.wargames.client.model.Game;
import com.wargames.client.model.Lobby;
import com.wargames.client.model.Map;
import com.wargames.client.model.MapGenerator;
import com.wargames.client.model.Player;

public class GameLoadPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte mapID = buff.get();
		
		LobbyWindow window = (LobbyWindow) client;
		
		// Take the client's parent, create a new map, and give it to the Game Client GUI.
		JFrame topframe = (JFrame) SwingUtilities.getWindowAncestor(client);
		topframe.dispose();
		// Initialize the game.
		Lobby gameLobby = window.lobby;
		ArrayList<Player> players = gameLobby.players;
		
		if(players.size() == 2)
		{
			Map gameMap;
			System.out.println("MAP ID: " + mapID);
			switch(mapID)
			{
				case 1:
					gameMap = MapGenerator.generateMap01(players.get(0), players.get(1));
					break;
				case 2:
					gameMap = MapGenerator.generateMap02(players.get(0), players.get(1));
					break;
				case 3:
					gameMap = MapGenerator.generateMap03(players.get(0), players.get(1));
					break;
				default:
					gameMap = MapGenerator.generateMap02(players.get(0), players.get(1));
				
			}
			Game game = new Game(gameMap, false);
			Player currentPlayer = null;
			for(Player player : players)
			{
				if(player.name.equals(window.username))
				{
					currentPlayer = player;
				}
			}
			if(currentPlayer == null)
			{
				currentPlayer = players.get(0);
			}
			return new GameClientGui(game, currentPlayer);
		}
		return client;
	}

}

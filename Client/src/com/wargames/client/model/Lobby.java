package com.wargames.client.model;

import java.util.ArrayList;

/**
 * Representation of a Lobby that we can connect to.
 * @author Clinton
 *
 */
public class Lobby {
	
	public int ID;
	public int numPlayers;
	public int maxPlayers;
	public int mapID;
	public String host;
	public ArrayList<Player> players;
	
	public Lobby(int id, int numPlayers, int maxPlayers, int mapID, String host)
	{
		this.ID = id;
		this.numPlayers = numPlayers;
		this.maxPlayers = maxPlayers;
		this.mapID = mapID;
		this.host = host;
		this.players = new ArrayList<Player>();
	}

	public void setPlayers(ArrayList<Player> players)
	{
		this.players = players;
	}
	
	public void setMaxPlayers(int maxPlayers)
	{
		this.maxPlayers = maxPlayers;
	}
	
	public void setMapID(int mapID)
	{
		this.mapID = mapID;
	}
}

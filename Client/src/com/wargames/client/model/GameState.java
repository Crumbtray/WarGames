package com.wargames.client.model;
import java.util.ArrayList;


public class GameState {
	public Map gameMap;
	public ArrayList<Player> players;
	public Player currentTurn;
	
	public GameState(Map gameMap)
	{
		this.gameMap = gameMap;
		this.players = new ArrayList<Player>();
	}
	
	public void addPlayer(Player player)
	{
		this.players.add(player);
	}
}

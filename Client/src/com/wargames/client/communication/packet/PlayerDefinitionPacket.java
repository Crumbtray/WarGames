package com.wargames.client.communication.packet;

public class PlayerDefinitionPacket extends Packet {

	private int PlayerID;
	private String PlayerName;
	private String PlayerColor;
	private int Team;
	private int PlayerNumber;
	
	private PlayerDefinitionPacket(int size, int playerID, String playerName, String playerColor, int team, int playerNumber)
	{
		this.ID = 0x12;
		this.size = size;
		this.PlayerID = playerID;
		this.PlayerName = playerName;
		this.PlayerColor = playerColor;
		this.Team = team;
		this.PlayerNumber = playerNumber;
	}
	
	@Override
	public int getSize() {
		return this.size;
	}

	@Override
	public int getID() {
		return this.ID;
	}

	@Override
	public byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPlayerID()
	{
		return this.PlayerID;
	}
	
	public String getPlayerName()
	{
		return this.PlayerName;
	}
	
	public String getPlayerColor()
	{
		return this.PlayerColor;
	}
	
	public int getPlayerTeam()
	{
		return this.Team;
	}
	
	public int getPlayerNumber()
	{
		return this.PlayerNumber;
	}

	public static PlayerDefinitionPacket parse(byte[] bytes)
	{
		//TODO: Implement
		return null;
	}
}

package com.wargames.client.communication.packet.incoming;

public class PostGamePacket extends Packet {

	private int playerID;
	private int playerTeam;
	private String playerColor;
	private boolean winOrLose;
	private int score;
	private int totalWins;
	private int totalLosses;
	
	@Override
	int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getPlayerID()
	{
		return this.playerID;
	}
	
	public int getPlayerTeam()
	{
		return this.playerTeam;
	}
	
	public String getPlayerColor()
	{
		return this.playerColor;
	}
	
	public boolean getWinOrLose()
	{
		return this.winOrLose;
	}
	
	public int getScore()
	{
		return this.score;
	}
	
	public int getTotalWins()
	{
		return this.totalWins;
	}
	
	public int getTotalLosses()
	{
		return this.totalLosses;
	}
	
	public static PostGamePacket parse(byte[] bytes)
	{
		return null;
	}

}

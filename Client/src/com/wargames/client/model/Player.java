package com.wargames.client.model;

public class Player {
	public int funds;
	public String name;
	
	/**
	 * Adds funds to the player.
	 * @param newFunds The new funds to add to the player.
	 * @return The total funds that the player has.
	 */
	public int addFunds(int newFunds)
	{
		this.funds = this.funds + newFunds;
		return this.funds;
	}
	
	/**
	 * Creates a new Player with initial funds, and name.
	 * @param initialFunds The initial funds of the player.
	 * @param name The name of the player.
	 */
	public Player(int initialFunds, String name)
	{
		this.funds = initialFunds;
		this.name = name;
	}
}

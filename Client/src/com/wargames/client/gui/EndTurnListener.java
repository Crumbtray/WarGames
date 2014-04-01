package com.wargames.client.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class for handling the End Turn button.
 * @author Crumbs
 *
 */
public class EndTurnListener implements ActionListener{
	
	private GameClientGui client;
	
	public EndTurnListener(GameClientGui client)
	{
		this.client = client;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Trying to end turn...");
		this.client.game.endTurn(this.client.game.currentTurn);
		this.client.repaint();
	}

}
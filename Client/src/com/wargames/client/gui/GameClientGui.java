package com.wargames.client.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wargames.client.model.GameState;

public class GameClientGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7504649405406424171L;
	private GameState gameState;
	
	public GameClientGui()
	{
		// create application frame and set visible
		//
		JFrame f = new JFrame();
		f.setSize(80, 80);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setSize(512, 512);
	}

	
	public static void main(String[] args)
	{
		new GameClientGui();
	}
}

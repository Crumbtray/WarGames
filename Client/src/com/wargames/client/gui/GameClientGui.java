package com.wargames.client.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wargames.client.model.Game;
import com.wargames.client.model.MapGenerator;
import com.wargames.client.model.Player;

public class GameClientGui extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7504649405406424171L;
	private Game game;
	private Player player1;
	private Player player2;
	
	// Gui objects
	private GuiMap guiMap;
	private Image imgBackground; // background image
	
	public GameClientGui()
	{
		// background
		URL urlBackgroundImg = getClass().getResource("/com/wargames/client/gui/img/background.png");
		this.imgBackground = new ImageIcon(urlBackgroundImg).getImage();
		
		// Initialize the game.
		player1 = new Player(0, "Clinton", 1, 1, "red");
		player2 = new Player(1, "Jesus", 2, 2, "blue");
		
		game = new Game(MapGenerator.generateMap01(player1, player2));		
		
		// Wrap our Map around the game Map
		guiMap = new GuiMap(game.gameMap);
		
		// create application frame and set visible
		//
		JFrame f = new JFrame();
		f.setSize(80, 80);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setSize(imgBackground.getWidth(null), imgBackground.getHeight(null));
	}

	
	@Override
	protected void paintComponent(Graphics g)
	{
		// draw background
		g.drawImage(this.imgBackground, 0, 0, null);
		
	}
	
	public static void main(String[] args)
	{
		new GameClientGui();
	}
}

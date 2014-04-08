package com.wargames.client.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wargames.client.model.Game;
import com.wargames.client.model.MapGenerator;
import com.wargames.client.model.Player;

public class MainGui extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4059868460110309749L;
	public JFrame f;
	public Image imgBackground;
	public JButton onlineGameButton;
	public JButton localGameButton;

	public MainGui()
	{
		URL urlBackgroundImg = getClass().getResource("/com/wargames/client/gui/img/mainBackground.png");
		this.imgBackground = new ImageIcon(urlBackgroundImg).getImage();

		this.localGameButton = new JButton("New Local Game");
		localGameButton.setActionCommand("localGame");
		localGameButton.addActionListener(this);
		this.add(localGameButton);

		this.onlineGameButton = new JButton("Login");
		onlineGameButton.setActionCommand("login");
		onlineGameButton.addActionListener(this);
		this.add(onlineGameButton);

		// create application frame and set visible
		f = new JFrame();
		f.setSize(100, 100);
		f.setVisible(true);
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setSize(800, 624);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		g.drawImage(this.imgBackground, 0, 0, null);
		localGameButton.setLocation(400, 300);
		onlineGameButton.setSize(localGameButton.getWidth(), onlineGameButton.getHeight());
		onlineGameButton.setLocation(400, 333);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		//TODO: if online game clicked, display login screen
		String  action = e.getActionCommand();

		switch (action){
		case "localGame":
			this.f.dispose();
			// Initialize the game.
			Player player1 = new Player(0, "Clinton", 1, 0, "red");
			Player player2 = new Player(1, "Jesus", 2, 1, "blue");

			Game game = new Game(MapGenerator.generateMap03(player1, player2), true);
			new GameClientGui(game, player1);
			break;
		case "login":
			//TODO: display login screen
			break;
		default:
			break;
		}
	}

}

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

import com.wargames.client.communication.packet.outgoing.LobbyActionPacket;
import com.wargames.client.helpers.PacketSender;
import com.wargames.client.model.Lobby;

public class LobbyWindow  extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Lobby lobby;
	private JFrame f;
	private Image imgBackground;
	private JButton startGameButton;
	public String username;
	
	/**
	 * Displays a lobby.
	 * @param lobby
	 */
	public LobbyWindow(Lobby lobby, String username)
	{
		this.username = username;
		this.lobby = lobby;
		URL urlBackgroundImg = getClass().getResource("/com/wargames/client/gui/img/mainBackground.png");
		this.imgBackground = new ImageIcon(urlBackgroundImg).getImage();

		startGameButton = new JButton("Start Game");
		this.add(startGameButton);
		startGameButton.setActionCommand("Start");
		startGameButton.addActionListener(this);
		
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
		startGameButton.setLocation(300, 300);
	}
	
	/**
	 * Gets called whenever a button is clicked.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if(action.equals("Start"))
		{
			byte lobbyAction = 0x01;
			LobbyActionPacket packet = new LobbyActionPacket(lobbyAction);
			PacketSender.sendPacket(packet);
		}		
	}

	public static void main(String[] args)
	{
		Lobby newLobby = new Lobby(1, 0, 2, 1, "ha");
		new LobbyWindow(newLobby, "clinton");
	}
	
}

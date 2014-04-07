package com.wargames.client.gui;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.wargames.client.communication.packet.outgoing.LobbyJoinPacket;
import com.wargames.client.helpers.PacketSender;
import com.wargames.client.model.Lobby;

public class LobbyListWindow extends JPanel implements ActionListener{

	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 6073655611286339364L;
	private JFrame f;
	private Image imgBackground;
	private ArrayList<Lobby> lobbyList;
	private ArrayList<JButton> buttonList;
	public String username;
	
	public LobbyListWindow(ArrayList<Lobby> lobbyList, String username)
	{
		this.username = username;
		URL urlBackgroundImg = getClass().getResource("/com/wargames/client/gui/img/mainBackground.png");
		this.imgBackground = new ImageIcon(urlBackgroundImg).getImage();
		buttonList = new ArrayList<JButton>();
		this.lobbyList = lobbyList;
		
		this.setLayout(new GridLayout(lobbyList.size(), 1));
		
		for(int i = 0; i < lobbyList.size(); i++)
		{			
			Lobby lobby = lobbyList.get(i);
			JButton joinButton = new JButton("Join Lobby " + lobby.ID + "(" + lobby.numPlayers + "/" + lobby.maxPlayers + ")");
			this.add(joinButton);
			joinButton.addActionListener(this);
			joinButton.setActionCommand(Integer.toString(lobby.ID));
		}
		
		// create application frame and set visible
		f = new JFrame();
		f.setVisible(true);
		f.setResizable(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(this);
		f.setSize(100,100);
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(this.imgBackground, 0, 0, null);
	}
	
	/**
	 * We do this on button click.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		short id = Short.parseShort(action);
		LobbyJoinPacket packet = new LobbyJoinPacket(id);
		PacketSender.sendPacket(packet);
	}
}

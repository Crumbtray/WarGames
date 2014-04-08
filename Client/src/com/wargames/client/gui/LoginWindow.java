package com.wargames.client.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.wargames.client.communication.packet.incoming.IncomingPacketList;
import com.wargames.client.communication.packet.outgoing.LoginPacket;
import com.wargames.client.helpers.PacketSender;
import com.wargames.client.helpers.SocketSingleton;

public class LoginWindow extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField username;
	private JPasswordField password;
	private JButton loginButton;
	private JFrame f;
	private Image imgBackground;
	
	public LoginWindow()
	{
		username = new JTextField(16);
		this.add(username);
		password = new JPasswordField(16);
		password.setActionCommand("Login");
		password.addActionListener(this);

		this.add(password);

		loginButton = new JButton("Login");
		loginButton.setActionCommand("Login");
		loginButton.addActionListener(this);

		this.add(loginButton);
		URL urlBackgroundImg = getClass().getResource("/com/wargames/client/gui/img/mainBackground.png");
		this.imgBackground = new ImageIcon(urlBackgroundImg).getImage();
		
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
		username.setLocation(300,300);
		password.setLocation(300,340);
		loginButton.setLocation(400, 400);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Login"))
		{
			char[] input = password.getPassword();
			String inputPassword = new String(input);
			System.out.println("Password: " + inputPassword);
			System.out.println("Username: " + username.getText());
			login(username.getText(), inputPassword);
		}

	}

	public void login(String username, String password)
	{	
		PacketSender.sendPacket(new LoginPacket(username, password));
	}

	public static void main(String[] args)
	{
		LoginWindow window = new LoginWindow();
		SocketReader socketReader = new SocketReader(window);
		socketReader.execute();
	}
	
	
	static class SocketReader extends SwingWorker<Void, Integer> {

		private DatagramSocket socket;
		private JPanel client;

		public SocketReader(JPanel client)
		{
			this.socket = SocketSingleton.getInstance().socket;
			this.client = client;
		}

		@Override
		protected Void doInBackground() throws Exception {
			System.out.println("Starting to read...");
			while(true) {
				try {

					// Try to read back
					byte[] initialBytes = new byte[3];
					DatagramPacket initialPacket = new DatagramPacket(initialBytes, initialBytes.length);

					System.out.println("Waiting to receive...");
					socket.receive(initialPacket);
					System.out.println("Received a packet!");
					byte[] packetData = initialPacket.getData();
					byte id = packetData[0];
					byte size = packetData[1];

					IncomingPacketList.parse(packetData, client);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		@Override
		protected void done() {

		}

	}
}

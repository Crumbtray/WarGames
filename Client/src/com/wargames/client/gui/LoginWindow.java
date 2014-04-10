package com.wargames.client.gui;

import java.awt.Color;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import com.wargames.client.communication.packet.incoming.IncomingPacketList;
import com.wargames.client.communication.packet.outgoing.AccountCreationPacket;
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
	private JButton backButton;
	private JFrame f;
	private Image imgBackground;
	private JButton registerButton;
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JTextArea textResponse;
	public String responseText;
	
	public LoginWindow()
	{
		this.backButton = new JButton("<--Back");
		backButton.setActionCommand("Back");
		backButton.addActionListener(this);
		this.add(backButton);
		
		usernameLabel = new JLabel("Username:");
		this.add(usernameLabel);
		
		passwordLabel = new JLabel("Password:");
		this.add(passwordLabel);
		
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
		
		registerButton = new JButton("Register");
		registerButton.setActionCommand("Register");
		registerButton.addActionListener(this);
		this.add(registerButton);
		
		textResponse = new JTextArea(5, 30);
		textResponse.setLineWrap(true);
		textResponse.setBackground(Color.gray);
		textResponse.setForeground(Color.WHITE);
		this.add(textResponse);
		Font font = new Font("Verdana", Font.BOLD, 12);
		textResponse.setFont(font);
		textResponse.getCaret().setVisible(false);
		textResponse.getCaret().setSelectionVisible(false);
		responseText = "";
		
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
		usernameLabel.setLocation(220, 300);
		passwordLabel.setLocation(220,340);
		username.setLocation(300,300);
		password.setLocation(300,340);
		loginButton.setLocation(400, 400);
		backButton.setLocation(10, 10);
		registerButton.setLocation(300, 400);
		textResponse.setLocation(220,450);
		textResponse.setText(responseText);
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
		else if(cmd.equals("Register"))
		{
			char[] input = password.getPassword();
			String inputPassword = new String(input);
			AccountCreationPacket packet = new AccountCreationPacket(username.getText(), inputPassword);
			PacketSender.sendPacket(packet);
		}
		else if(cmd.equals("Back"))
		{
			this.f.dispose();
			MainGui.main(null);
		}
	}

	public void login(String username, String password)
	{	
		PacketSender.sendPacket(new LoginPacket(username, password));
	}
}

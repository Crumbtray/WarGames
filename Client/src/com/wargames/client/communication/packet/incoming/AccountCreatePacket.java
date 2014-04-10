package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.LoginWindow;

public class AccountCreatePacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte response = buff.get();
		
		LoginWindow window = (LoginWindow) client;
		
		if(response == 1)
		{
			window.responseText = "Account successfully created.";
		}
		else if(response == 2)
		{
			window.responseText = "Invalid Username/Password";
		}
		else
		{
			window.responseText = "Server error.";
		}

		client.revalidate();
		client.repaint();
		return client;
	}

}

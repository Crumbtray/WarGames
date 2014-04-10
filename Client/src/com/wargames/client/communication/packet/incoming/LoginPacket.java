package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.LoginWindow;


public class LoginPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte response = buff.get();
		int wins = buff.getInt();
		int losses = buff.getInt();
		
		LoginWindow window = (LoginWindow) client;
		
		if(response == 1)
		{
			window.responseText = "Authentication Successful.";
		}
		else
		{
			window.responseText = "Authentication Failure.";
		}
		return client;
	}

}

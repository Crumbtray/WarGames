package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

public class AccountCreatePacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte response = buff.get();
		
		if(response == 1)
		{
			System.out.println("Account successfully created.");
		}
		else if(response == 2)
		{
			System.out.println("Invalid Username/Password");
		}
		else
		{
			System.out.println("Server error.");
		}

		return client;
	}

}

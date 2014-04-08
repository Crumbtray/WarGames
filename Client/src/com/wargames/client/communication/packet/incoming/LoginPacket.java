package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;


public class LoginPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte response = buff.get();
		int wins = buff.getInt();
		int losses = buff.getInt();
		
		//TODO: do stuff with response
		System.out.println("Response: " + response);
		System.out.println("Wins: " + wins);
		System.out.println("Losses: " + losses);
		
		if(response == 1)
		{
			System.out.println("Logged in.");
		}
		return client;
	}

}

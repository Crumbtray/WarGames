package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JFrame;

public class AccountCreatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JFrame client) {
		byte response = buff.get();
		
		System.out.println(response);
		
		//TODO: do stuff with response

	}

}

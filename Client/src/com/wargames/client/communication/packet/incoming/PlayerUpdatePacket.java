package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JFrame;

public class PlayerUpdatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JFrame client) {
		int money = buff.getInt();
		int score = buff.getInt();
		
		//TODO: do stuff with response

	}

}

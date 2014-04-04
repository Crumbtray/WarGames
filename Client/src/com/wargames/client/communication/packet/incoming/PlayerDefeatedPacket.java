package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JFrame;

public class PlayerDefeatedPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JFrame client) {
		byte playerNumber = buff.get();
		int playerID = buff.getInt();
		
		//TODO: do stuff with response

	}

}

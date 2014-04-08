package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

public class LobbyChatPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte msgsize = buff.get();
		String msg = buff.asCharBuffer().toString();
		
		//TODO: do stuff with response
		// Not necessary for our implementation.
		return client;
	}

}

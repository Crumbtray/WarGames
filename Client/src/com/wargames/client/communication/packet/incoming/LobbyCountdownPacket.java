package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

public class LobbyCountdownPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte time = buff.get();
		
		//TODO: do stuff with response
		// Not necessary for our implementation.
		return client;
	}

}

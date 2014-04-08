package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

public class PlayerDefeatedPacket extends PacketFunctor {

	@Override
	public JPanel parse(ByteBuffer buff, JPanel client) {
		byte playerNumber = buff.get();
		int playerID = buff.getInt();
		
		// Not necessary, when Player Defeated, the game should end.
		return client;
	}

}

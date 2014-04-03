package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class PlayerDefeatedPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte playerNumber = buff.get();
		int playerID = buff.getInt();
		
		//TODO: do stuff with response

	}

}

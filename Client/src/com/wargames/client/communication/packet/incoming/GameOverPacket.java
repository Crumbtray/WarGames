package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class GameOverPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte winnerNumber = buff.get();
		int winnerID = buff.getInt();
		
		//TODO: do stuff with response

	}

}

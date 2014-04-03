package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class PlayerUpdatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		int money = buff.getInt();
		int score = buff.getInt();
		
		//TODO: do stuff with response

	}

}

package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class LoginPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte response = buff.get();
		int wins = buff.getInt();
		int losses = buff.getInt();
		
		//TODO: do stuff with response

	}

}

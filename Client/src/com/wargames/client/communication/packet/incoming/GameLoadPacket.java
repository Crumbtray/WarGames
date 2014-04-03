package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class GameLoadPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte mapID = buff.get();
		
		//TODO: do stuff with response

	}

}

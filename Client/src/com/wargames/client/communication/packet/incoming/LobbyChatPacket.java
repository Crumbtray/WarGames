package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class LobbyChatPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte msgsize = buff.get();
		String msg = buff.asCharBuffer().toString();
		
		//TODO: do stuff with response

	}

}

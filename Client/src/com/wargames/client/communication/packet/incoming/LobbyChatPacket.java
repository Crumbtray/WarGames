package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import javax.swing.JFrame;

public class LobbyChatPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JFrame client) {
		byte msgsize = buff.get();
		String msg = buff.asCharBuffer().toString();
		
		//TODO: do stuff with response

	}

}

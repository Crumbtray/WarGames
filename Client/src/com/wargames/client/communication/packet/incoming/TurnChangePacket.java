package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JFrame;

public class TurnChangePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JFrame client) {
		byte playernumber = buff.get();
		
		//TODO: do stuff with response

	}

}

package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class TurnChangePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte playernumber = buff.get();
		
		//TODO: do stuff with response

	}

}

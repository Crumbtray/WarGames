package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class LobbyCountdownPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte time = buff.get();
		
		//TODO: do stuff with response

	}

}

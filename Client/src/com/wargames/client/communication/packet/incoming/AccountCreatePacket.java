package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class AccountCreatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte response = buff.get();
		
		System.out.println(response);
		
		//TODO: do stuff with response

	}

}

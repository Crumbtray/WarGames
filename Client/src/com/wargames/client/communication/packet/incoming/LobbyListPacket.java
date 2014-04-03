package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

public class LobbyListPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff) {
		byte numlobbies = buff.get();
		
		for (int i = 0; i < numlobbies; i++)
		{
			short id = buff.getShort();
			byte size = buff.get();
			byte maxSize = buff.get();
			byte mapID = buff.get();
			byte[] name = new byte[10];
			for (int j = 0; j < 10; j++)
			{
				name[j] = buff.get();
			}
			
			//TODO: do stuff with response
			
		}

	}

}

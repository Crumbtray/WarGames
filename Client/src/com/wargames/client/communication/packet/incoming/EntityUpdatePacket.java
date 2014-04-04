package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JFrame;

public class EntityUpdatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JFrame client) {
		short id = buff.getShort();
		byte unitType = buff.get();
		byte color = buff.get();
		byte owner = buff.get();
		byte xPos = buff.get();
		byte yPos = buff.get();
		byte hp = buff.get();
		
		//TODO: do stuff with response

	}
	

}

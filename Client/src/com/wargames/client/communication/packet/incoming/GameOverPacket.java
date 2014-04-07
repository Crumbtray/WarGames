package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.GameClientGui;

public class GameOverPacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JPanel client) {
		byte winnerNumber = buff.get();
		int winnerID = buff.getInt();
		
		//TODO: do stuff with response
		GameClientGui window = (GameClientGui) client;
	}

}

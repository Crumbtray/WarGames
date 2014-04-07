package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

import com.wargames.client.gui.GameClientGui;
import com.wargames.client.model.Player;

public class PlayerUpdatePacket extends PacketFunctor {

	@Override
	public void parse(ByteBuffer buff, JPanel client) {
		int money = buff.getInt();
		int score = buff.getInt();
		
		// This is money that the player gets.
		GameClientGui clientGui = (GameClientGui) client;
		Player selfPlayer = clientGui.loggedInPlayer;
		selfPlayer.addFunds(money);
	}

}

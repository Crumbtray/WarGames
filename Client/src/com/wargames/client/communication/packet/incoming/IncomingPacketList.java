package com.wargames.client.communication.packet.incoming;

import java.nio.ByteBuffer;

import javax.swing.JPanel;

public class IncomingPacketList {
	static PacketFunctor[] packetParser = new PacketFunctor[32];
	
	static
	{
		packetParser[0x01] = new LoginPacket();
		packetParser[0x02] = new AccountCreatePacket();
		packetParser[0x03] = new LobbyUpdatePacket();
		packetParser[0x04] = new LobbyChatPacket();
		packetParser[0x05] = new LobbyCountdownPacket();
		packetParser[0x06] = new PostGamePacket();
		packetParser[0x07] = new LobbyListPacket();
		packetParser[0x10] = new GameLoadPacket();
		packetParser[0x11] = new EntityUpdatePacket();
		packetParser[0x12] = new PlayerDefinitionPacket();
		packetParser[0x13] = new TurnChangePacket();
		packetParser[0x14] = new PlayerUpdatePacket();
		packetParser[0x15] = new ActionPacket();
		packetParser[0x16] = new PlayerDefeatedPacket();
		packetParser[0x17] = new GameOverPacket();
	}
	
	public static JPanel parse(byte type, ByteBuffer buff, JPanel client)
	{
		try
		{
			return packetParser[type].parse(buff, client);
		}
		catch (Error e)
		{
			System.out.println("Attempted to parse packet "+type+" failed!");
		}
		return client;
	}
	
}

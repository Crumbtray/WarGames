package com.wargames.client.gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.JPanel;

import com.wargames.client.communication.packet.incoming.AccountCreatePacket;
import com.wargames.client.communication.packet.incoming.ActionPacket;
import com.wargames.client.communication.packet.incoming.EntityUpdatePacket;
import com.wargames.client.communication.packet.incoming.GameLoadPacket;
import com.wargames.client.communication.packet.incoming.GameOverPacket;
import com.wargames.client.communication.packet.incoming.IncomingPacketList;
import com.wargames.client.communication.packet.incoming.LobbyChatPacket;
import com.wargames.client.communication.packet.incoming.LobbyCountdownPacket;
import com.wargames.client.communication.packet.incoming.LobbyListPacket;
import com.wargames.client.communication.packet.incoming.LobbyUpdatePacket;
import com.wargames.client.communication.packet.incoming.LoginPacket;
import com.wargames.client.communication.packet.incoming.PacketFunctor;
import com.wargames.client.communication.packet.incoming.PlayerDefeatedPacket;
import com.wargames.client.communication.packet.incoming.PlayerDefinitionPacket;
import com.wargames.client.communication.packet.incoming.PlayerUpdatePacket;
import com.wargames.client.communication.packet.incoming.PostGamePacket;
import com.wargames.client.communication.packet.incoming.TurnChangePacket;
import com.wargames.client.helpers.SocketSingleton;

public class SocketReader implements Runnable {

	private DatagramSocket socket;
	private JPanel client;
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
	
	public SocketReader(JPanel client)
	{
		this.socket = SocketSingleton.getInstance().socket;
		this.client = client;
	}

	@Override
	public void run() {
		System.out.println("Starting to read...");
		while(true) {
			try {

				// Try to read back
				byte[] initialBytes = new byte[256];
				DatagramPacket initialPacket = new DatagramPacket(initialBytes, initialBytes.length);

				System.out.println("Waiting to receive...");
				socket.receive(initialPacket);
				System.out.println("Received a packet!");
				byte[] packetData = initialPacket.getData();

				int begin = 0;
				
				while (begin < packetData.length)
				{
					byte type = packetData[begin];
					byte size = packetData[begin+1];

					if (type == 0)
						break;
					
					ByteBuffer buff = ByteBuffer.allocate(size);
					buff.order(ByteOrder.LITTLE_ENDIAN);
					buff.put(packetData, begin+2, (begin+2)+size < packetData.length ? size : packetData.length - (begin+2));
					buff.rewind();
					try
					{
						client = packetParser[type].parse(buff, client);
					}
					catch (Error e)
					{
						System.out.println("Attempted to parse packet "+type+" failed!");
					}
					begin = begin+size;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
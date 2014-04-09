package com.wargames.client.gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import javax.swing.JPanel;

import com.wargames.client.communication.packet.incoming.IncomingPacketList;

import com.wargames.client.helpers.SocketSingleton;

public class SocketReader implements Runnable {

	private DatagramSocket socket;
	private JPanel client;
	
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
						client = IncomingPacketList.parse(type, buff, client);
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
package com.wargames.client.gui;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

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

				IncomingPacketList.parse(packetData, client);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
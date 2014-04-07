package com.wargames.client.helpers;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.wargames.client.communication.packet.outgoing.Packet;

public class PacketSender {

	private final static String ipAddress = "50.65.25.35";
	private final static int port = 31111;
	
	/**
	 * Sends a packet through the socket.
	 * @param packet
	 */
	public static void sendPacket(Packet inputPacket)
	{
		byte[] data = inputPacket.toByteArray();
		InetAddress address;
		DatagramSocket socket;
		try {
			socket = SocketSingleton.getInstance().socket;
			address = InetAddress.getByName(ipAddress);
			DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
			socket.send(packet);
			System.out.println("Sent packet " + inputPacket.getClass().getName());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

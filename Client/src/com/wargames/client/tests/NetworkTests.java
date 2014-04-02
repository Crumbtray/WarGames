package com.wargames.client.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Before;
import org.junit.Test;

public class NetworkTests {

	private DatagramSocket socket;
	
	private final String Address = "127.0.0.1";
	private final int PORT = 31111;
	
	@Before
	public void setUp() throws Exception {
		socket = new DatagramSocket();
	}

	@Test
	public void testAccountCreation() {
		byte[] accountCreationPacket = new byte[29];
		
		accountCreationPacket[0] = 0x01;
		accountCreationPacket[1] = 0x1C;
		String userName = "Clinton012";
		String password = "testpassword";
		byte[] userNameBytes = userName.getBytes();
		byte[] passwordBytes = password.getBytes();
		
		for(int i = 0; i < 10; i++)
		{
			accountCreationPacket[i + 2] = userNameBytes[i];
		}
		
		for(int i = 0; i < passwordBytes.length; i++)
		{
			accountCreationPacket[i + 12] = passwordBytes[i];
		}
		
		InetAddress address;
		try {
			address = InetAddress.getByName(Address);
			DatagramPacket packet = new DatagramPacket(accountCreationPacket, accountCreationPacket.length, address, PORT);
			socket.send(packet);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Try to read back
		byte[] initialBytes = new byte[3];
		DatagramPacket initialPacket = new DatagramPacket(initialBytes, initialBytes.length);
		try {
			socket.receive(initialPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		byte[] packetData = initialPacket.getData();
		short id = (short) packetData[0];
		short size = (short) packetData[1];
		short result = packetData[2];
		System.out.println("ID: " + id);
		System.out.println("Size: " + size);
		System.out.println("Result: " + result);
	}
	
	@Test
	public void testByteInitializer()
	{
		byte[] newByteArray = new byte[5];
		String zero = "0";
		
		byte[] zeroBytes = zero.getBytes();
		for(int i = 0; i < zeroBytes.length; i++)
		{
			newByteArray[i] = zeroBytes[i];
		}
		
		for(int i = 0; i < newByteArray.length; i++)
		{
			System.out.println("Byte Value:"+ newByteArray[i]);
		}
		
		short testValue = 8;
		int provenValue = (int) testValue;
		
		System.out.println("Short: " + testValue);
		System.out.println("Int: " + provenValue);
	}

}

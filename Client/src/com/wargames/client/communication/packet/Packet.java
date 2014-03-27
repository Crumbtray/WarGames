package com.wargames.client.communication.packet;

/**
 * All Packets sent and received should follow this basic structure.
 * @author Clinton
 *
 */
abstract class Packet {
	protected int ID;
	protected int size;
	
	abstract int getSize();
	
	abstract int getID();
	
	abstract byte[] toByteArray();
}

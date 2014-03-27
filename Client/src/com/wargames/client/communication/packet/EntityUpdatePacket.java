package com.wargames.client.communication.packet;

public class EntityUpdatePacket extends Packet {
	
	private int nextPlayerNumber;

	private EntityUpdatePacket(int size, int nextPlayerNumber)
	{
		this.ID = 0x11;
		this.size = size;
		this.nextPlayerNumber = nextPlayerNumber;
	}
	
	@Override
	int getSize() {
		return this.size;
	}

	@Override
	int getID() {
		return this.ID;
	}

	@Override
	byte[] toByteArray() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Gets the next player number from the packet.
	 * @return
	 */
	public int getNextPlayer()
	{
		return this.nextPlayerNumber;
	}
	
	public static EntityUpdatePacket parse(byte[] bytes)
	{
		return null;
	}
}

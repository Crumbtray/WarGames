package com.wargames.client.helpers;

public class SocketSingleton {
	private static SocketSingleton instance = null;
	
	private SocketSingleton()
	{
		
	}
	
	public static synchronized SocketSingleton getInstance()
	{
		if(instance == null)
		{
			instance = new SocketSingleton();
		}
		return instance;
	}

}

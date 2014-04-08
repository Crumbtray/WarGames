package com.wargames.client.gui;

public class Main {

	public static SocketReader reader;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		LoginWindow window = new LoginWindow();
		reader = new SocketReader(window);
		new Thread(reader).start();
	}

}

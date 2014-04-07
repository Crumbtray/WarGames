package com.wargames.client.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.wargames.client.gui.LobbyListWindow;
import com.wargames.client.model.Lobby;

public class GuiTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testLobbyWindow() {
		ArrayList<Lobby> lobbyList = new ArrayList<Lobby>();
		Lobby newLobby = new Lobby(1, 0, 2, 1, "host");
		Lobby secondLobby = new Lobby(2, 0, 2, 1, "la");
		lobbyList.add(newLobby);
		lobbyList.add(secondLobby);
		new LobbyListWindow(lobbyList, "la");
	}

}

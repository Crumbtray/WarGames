package com.wargames.client.tests;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.wargames.client.model.MoveType;
import com.wargames.client.model.Player;
import com.wargames.client.model.Terrain;
import com.wargames.client.model.Unit;
import com.wargames.client.model.UnitType;

public class ModelTests {

	@Test
	public void testUnitCreation() {
		Player testPlayer = new Player(0, "Clinton", 1, 0, "red");
		Unit soldierUnit = Unit.createSoldier(testPlayer, 1);
		assertTrue(soldierUnit.getUnitType().equals(UnitType.SOLDIER));
		assertTrue(soldierUnit.health == 10);
		assertTrue(soldierUnit.id == 1);
		assertTrue(soldierUnit.getOwner().equals(testPlayer));
		assertTrue(soldierUnit.getMoveType().equals(MoveType.FOOT));
	}

	@Test
	public void testTerrainCreation() {
		Player testPlayer = new Player(0, "Clinton", 1, 0, "red");
		Unit soldierUnit = Unit.createSoldier(testPlayer, 1);
		Unit tankUnit = Unit.createTank(testPlayer, 2);
		Terrain mountainTerrain = Terrain.createMountainTerrain();
		assertTrue(mountainTerrain.defense == 4);
		assertTrue(mountainTerrain.canMove(soldierUnit));
		assertFalse(mountainTerrain.canMove(tankUnit));
	}
}

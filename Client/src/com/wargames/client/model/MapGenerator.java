package com.wargames.client.model;

/**
 * Helper class that generates maps.
 * @author Clinton
 *
 */
public class MapGenerator {

	/**
	 * Starter map for two players.
	 * Contains two player controlled factories, one at each corner.
	 * The rest is plains.
	 * @return
	 */
	public static Map generateMap01(Player player1, Player player2){
		Map newMap = new Map(16,16);
		for (int i = 0; i < 16; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				// Loop through all the coordinates
				if(i == 0 && j == 0)
				{
					// Player 1's factory.
					Terrain playerOneFactory = Structure.createFactory(player1);
					try {
						newMap.addTerrain(0, 0, playerOneFactory);
					} 
					catch (MapException e)
					{
						System.out.println("GenerateMap01 Terrain Adding Error: This should never happen.");
						e.printStackTrace();
					}
				}
				else if(i == 15 && j == 15)
				{
					// Player 2's factory.
					Terrain playerTwoFactory = Structure.createFactory(player2);
					try {
						newMap.addTerrain(i, j, playerTwoFactory);
					}
					catch(MapException e)
					{
						System.out.println("GenerateMap01 Terrain Adding Error: This should never happen.");
						e.printStackTrace();
					}
				}
				else
				{
					// Fill with plain.
					Terrain plainTerrain = Terrain.createPlainTerrain();
					try {
						newMap.addTerrain(i, j, plainTerrain);
					}
					catch(MapException e)
					{
						System.out.println("GenerateMap01 Terrain Adding Error: This should never happen.");
						e.printStackTrace();
					}
				}
			}
		}
		return newMap;
	}
}

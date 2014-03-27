package com.wargames.client.client;
import com.wargames.client.model.*;

public class GameClient {
	public Game gameState;
	public Player thisPlayer;
	
	public GameClient(Game gameState)
	{
		this.gameState = gameState;
	}
	
	public void MoveUnit(int startXPosition, int startYPosition, int destXPosition, int destYPosition)
	{
		
	}
	
	
	public void MoveAndAttackUnit(int startXPosition, int startYPosition, int destXPosition, int destYPosition, int targetXPosition, int targetYPosition)
	{
		
	}
	
	public void AttackUnit(int unitXPosition, int unitYPosition, int targetXPosition, int targetYPosition)
	{
		
	}
	
	public void CreateUnit(int factoryXPosition, int factoryYPosition, UnitType unitType)
	{
		// Validate with the server.
		
	}
	
	public void MoveAndCaptureStructure(int startXPosition, int startYPosition, int structureXPosition, int structureYPosition, Unit capturer)
	{
		
	}
	
	public void CaptureStructure(int structureXPosition, int structureYPosition, Unit capturer)
	{
		
	}
}

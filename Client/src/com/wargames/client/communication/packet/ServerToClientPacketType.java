package com.wargames.client.communication.packet;

public enum ServerToClientPacketType {
	LoginStatusPacket, 
	AccountCreationPacket, 
	LobbyUpdatePacket, 
	LobbyChatPacket, 
	LobbyCountdownPacket, 
	PostGamePacket, 
	GameLoadPacket, 
	EntityUpdatePacket,
	PlayerDefinitionPacket,
	TurnChangePacket,
	PlayerUpdatePacket,
	ActionPacket,
	PlayerDefeatedPacket,
	GameOverPacket,
}

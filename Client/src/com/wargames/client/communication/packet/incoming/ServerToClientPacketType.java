package com.wargames.client.communication.packet.incoming;

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

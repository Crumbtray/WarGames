#include "player.h"

CPlayer::CPlayer()
{
	id = 0;
}

CPlayer::~CPlayer()
{

}

const char* CPlayer::GetName()
{
	return m_username.c_str();
}

bool CPlayer::packetQueueEmpty()
{
	return PacketQueue.empty();
}
void CPlayer::clearPacketQueue()
{
	while (!packetQueueEmpty())
	{
		delete popPacket();
	}
}
void CPlayer::pushPacket(CPacket* packet)
{
	PacketQueue.push_back(packet);
}
CPacket* CPlayer::popPacket()
{
	CPacket* PPacket = PacketQueue.front();
	PacketQueue.pop_front();
	return PPacket;
}
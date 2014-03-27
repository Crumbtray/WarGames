#include "player.h"
#include "packets\packet.h"

CPlayer::CPlayer(int id)
{
	this->id = id;
}

CPlayer::~CPlayer()
{

}

const char* CPlayer::GetName()
{
	return m_username.c_str();
}

void CPlayer::SetName(const char* name)
{
	m_username.clear();
	m_username.append(name);
}

void CPlayer::SetWins(uint32 wins)
{
	m_wins = wins;
}

void CPlayer::SetLosses(uint32 losses)
{
	m_losses = losses;
}

uint32 CPlayer::GetWins()
{
	return m_wins;
}

uint32 CPlayer::GetLosses()
{
	return m_losses;
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
#include "player.h"
#include "packets\packet.h"

CPlayer::CPlayer(int id)
{
	this->id = id;
	m_wins = 0;
	m_losses = 0;
	m_team = 0;
	m_color = 0;
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

void CPlayer::SetTeam(uint8 team)
{
	m_team = team;
}

void CPlayer::SetColor(uint8 color)
{
	m_color = color;
}

uint32 CPlayer::GetWins()
{
	return m_wins;
}

uint32 CPlayer::GetLosses()
{
	return m_losses;
}

uint8 CPlayer::GetTeam()
{
	return m_team;
}

uint8 CPlayer::GetColor()
{
	return m_color;
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
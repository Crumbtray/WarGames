#include "lib/cbasetypes.h"
#include <deque>

class CPacket;

typedef std::deque<CPacket*> PacketQueue_t;

class CPlayer
{
private:
	string_t m_username;
	uint32 m_wins;
	uint32 m_losses;
	uint8 m_team;
	uint8 m_color;
	int32 m_money;
	int32 m_score;

	PacketQueue_t PacketQueue;

public:
	uint32 id;
	const char* GetName();
	void SetName(const char*);
	void SetWins(uint32);
	void SetLosses(uint32);
	void SetTeam(uint8);
	void SetColor(uint8);
	void SetMoney(int32);
	void AddMoney(int32);
	void SetScore(int32);
	void AddScore(int32);
	uint32 GetWins();
	uint32 GetLosses();
	uint8 GetTeam();
	uint8 GetColor();
	int32 GetScore();
	int32 GetMoney();

	bool packetQueueEmpty();
	void clearPacketQueue();
	void pushPacket(CPacket*);
	CPacket* popPacket();

	CPlayer(int);
	~CPlayer();
};
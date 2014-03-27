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

	PacketQueue_t PacketQueue;

public:
	uint32 id;
	const char* GetName();
	void SetName(const char*);
	void SetWins(uint32);
	void SetLosses(uint32);
	uint32 GetWins();
	uint32 GetLosses();

	bool packetQueueEmpty();
	void clearPacketQueue();
	void pushPacket(CPacket*);
	CPacket* popPacket();

	CPlayer(int);
	~CPlayer();
};
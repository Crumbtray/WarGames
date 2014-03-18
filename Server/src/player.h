#include "lib/cbasetypes.h"
#include <deque>

class CPacket;

typedef std::deque<CPacket*> PacketQueue_t;

class CPlayer
{
private:
	string_t m_username;
	PacketQueue_t PacketQueue;

public:
	uint32 id;
	const char* GetName();

	bool packetQueueEmpty();
	void clearPacketQueue();
	void pushPacket(CPacket* packet);
	CPacket* popPacket();

	CPlayer();
	~CPlayer();
};
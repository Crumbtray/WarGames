

#ifndef _LOBBYCDPACKET_H
#define _LOBBYCDPACKET_H

#include "packet.h"

class CPlayer;

class CLobbyCountdownPacket : public CPacket
{
public:
	CLobbyCountdownPacket(uint8);
};


#endif
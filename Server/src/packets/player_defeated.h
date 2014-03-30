

#ifndef _PLAYERDEFEATEDPACKET_H
#define _PLAYERDEFEATEDPACKET_H

#include "packet.h"

class CPlayer;

class CPlayerDefeatedPacket : public CPacket
{
public:
	CPlayerDefeatedPacket(CPlayer*);
};


#endif
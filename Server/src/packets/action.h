

#ifndef _ACTIONPACKET_H
#define _ACTIONPACKET_H

#include "packet.h"

enum ACTION
{
	ACTION_MOVE,
	ACTION_ATTACK,
	ACTION_CAPTURE,
	ACTION_PRODUCE
};

class Unit;

class CActionPacket : public CPacket
{
public:
	CActionPacket(Unit* initiator, Unit* target, ACTION, int8 dmginit, int8 dmgtarget);
};


#endif
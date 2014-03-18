

#ifndef _PACKET_H
#define _PACKET_H

#include "../lib/cbasetypes.h"

class CPacket
{
protected:
	uint8 type;
	uint8 size;
	uint8 data[256];
public:
	uint8 getSize();
	uint8 getType();

	CPacket();
};

#endif

#include "entity_update.h"

#include "../lib/socket.h"

CEntityUpdatePacket::CEntityUpdatePacket()
{
	this->type = 0x11;
	this->size = 0x0A;

	//WBUFW(packet, 0x02) = ID
	//WBUFB(packet, 0x04) = type
	//WBUFB(packet, 0x05) = color
	//WBUFB(packet, 0x06) = owner
	//WBUFB(packet, 0x07) = xpos
	//WBUFB(packet, 0x08) = ypos
	//WBUFB(packet, 0x09) = hp
}
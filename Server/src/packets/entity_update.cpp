
#include "entity_update.h"

#include "../lib/socket.h"

CEntityUpdatePacket::CEntityUpdatePacket(Unit* unit, std::pair<uint8, uint8> pos)
{
	this->type = 0x11;
	this->size = 0x0A;

	//WBUFW(packet, 0x02) = unit->id;
	//WBUFB(packet, 0x04) = type
	//WBUFB(packet, 0x05) = color
	//WBUFB(packet, 0x06) = owner
	//WBUFB(packet, 0x07) = pos.first;
	//WBUFB(packet, 0x08) = pos.second;
	//WBUFB(packet, 0x09) = hp
}
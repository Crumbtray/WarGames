
#include "entity_update.h"

#include "../lib/socket.h"

CEntityUpdatePacket::CEntityUpdatePacket()
{
	this->type = 0x11;
	this->size = 0x0A;

	//TODO: change constructor to include entity and implement packet!
}
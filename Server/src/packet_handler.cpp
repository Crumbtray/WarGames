#include "server.h"
#include "packet_handler.h"
#include "showmsg.h"

namespace packethandler
{
	uint8 PacketSizes[256];
	void(*PacketParser[256])(session_data_t*, CPlayer*, int8*);

	void EmptyHandler(session_data_t* session, CPlayer* PPlayer, int8* data)
	{
		ShowWarning(CL_YELLOW"packet parser: unhandled packet %02hX from username %s\n" CL_RESET, RBUFB(data, 0), PPlayer->GetName());
	}

	void init()
	{
		for (uint8 i = 0; i < 256; ++i)
		{
			PacketSizes[i] = 0;
			PacketParser[i] = &EmptyHandler;
		}
	}
}
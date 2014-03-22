#include "server.h"
#include "packet_handler.h"

#include "lib/showmsg.h"

namespace packethandler
{
	uint8 PacketSizes[256];
	void(*PacketParser[256])(session_data_t*, CPlayer*, int8*);

	void EmptyHandler(session_data_t* session, CPlayer* PPlayer, int8* data)
	{
		ShowWarning(CL_YELLOW"packet parser: unhandled packet %02hX from username %s\n" CL_RESET, RBUFB(data, 0), PPlayer->GetName());
	}

	void Packet0x01(session_data_t* session, CPlayer* player, int8* data)
	{

	}

	void init()
	{
		for (uint8 i = 0; i < 255; ++i)
		{
			PacketSizes[i] = 0;
			PacketParser[i] = &EmptyHandler;
		}
		PacketSizes[0x01] = 0x1C; PacketParser[0x01] = &Packet0x01;
	}
}
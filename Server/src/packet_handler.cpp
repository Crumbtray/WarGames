#include "server.h"
#include "packet_handler.h"

#include "lib/showmsg.h"

#include "packets\packet.h"
#include "packets\account_creation.h"
#include "packets\login.h"

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
		char* query = "SELECT id, handle FROM accounts WHERE handle = '%s' AND password = PASSWORD('%s');";

		char handle[10];
		memcpy(handle, data + 0x02, 10);
		char pass[16];
		memcpy(pass, data + 0x0C, 16);

		char escapedHandle[(sizeof handle) * 2 + 1];
		char escapedPass[(sizeof pass) * 2 + 1];

		Sql_EscapeStringLen(SqlHandle, escapedHandle, handle, sizeof handle);
		Sql_EscapeStringLen(SqlHandle, escapedPass, pass, sizeof pass);

		int ret = Sql_Query(SqlHandle, query, escapedHandle, escapedPass);

		if (ret != SQL_ERROR)
		{
			if (Sql_NumRows(SqlHandle) != 0)
			{
				//fill in account specific stuff (wins/losses/etc)
				delete session->PPlayer;
				session->PPlayer = new CPlayer(Sql_GetUIntData(SqlHandle, 0));
				session->PPlayer->SetName(handle);
				session->PPlayer->pushPacket(new CLoginPacket(session->PPlayer, LOGIN_SUCCESS));
			}
			else
			{
				session->PPlayer->pushPacket(new CLoginPacket(session->PPlayer, LOGIN_INVALID_CRED));
			}
		}
		else
		{
			session->PPlayer->pushPacket(new CLoginPacket(session->PPlayer, LOGIN_UNDEF));
		}
	}

	void Packet0x02(session_data_t* session, CPlayer* player, int8* data)
	{
		char* query = "SELECT id, handle FROM accounts WHERE handle LIKE '%s';";

		char handle[10];
		memcpy(handle, data + 0x02, 10);
		char pass[16];
		memcpy(pass, data + 0x0C, 16);

		char escapedHandle[(sizeof handle) * 2 + 1];
		char escapedPass[(sizeof pass) * 2 + 1];

		Sql_EscapeStringLen(SqlHandle, escapedHandle, handle, sizeof handle);
		Sql_EscapeStringLen(SqlHandle, escapedPass, pass, sizeof pass);

		int ret = Sql_Query(SqlHandle, query, escapedHandle);

		//TODO: validate name/pass
		if (ret != SQL_ERROR)
		{
			if (Sql_NumRows(SqlHandle) == 0)
			{
				query = "INSERT INTO accounts id = (SELECT max(id) from accounts), handle = '%s', password = PASSWORD('%s');";

				ret = Sql_Query(SqlHandle, query, escapedHandle, escapedPass);

				if (ret != SQL_ERROR && Sql_AffectedRows(SqlHandle) == 1)
				{
					session->PPlayer->pushPacket(new CAccountCreatePacket(CREATE_SUCCESS));
				}
				else
				{
					session->PPlayer->pushPacket(new CAccountCreatePacket(CREATE_UNDEF));
				}
			}
			else
			{
				session->PPlayer->pushPacket(new CAccountCreatePacket(CREATE_NAME_TAKEN));
			}
		}
		else
		{
			session->PPlayer->pushPacket(new CAccountCreatePacket(CREATE_UNDEF));
		}
	}

	void Packet0x04(session_data_t* session, CPlayer* player, int8* data)
	{
		int length = WBUFB(data, 0x02);
		//TODO: push chat packet to other players in lobby/game
	}

	void init()
	{
		for (uint8 i = 0; i < 255; ++i)
		{
			PacketSizes[i] = 0;
			PacketParser[i] = &EmptyHandler;
		}
		PacketSizes[0x01] = 0x1C; PacketParser[0x01] = &Packet0x01;
		PacketSizes[0x02] = 0x1C; PacketParser[0x02] = &Packet0x02;
		PacketSizes[0x04] = 0x00; PacketParser[0x04] = &Packet0x04;
	}
}
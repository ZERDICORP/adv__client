package requests;



import core.Client;
import core.Packet;

import constants.PacketType;



public class Request_Disconnect
{
	public Request_Disconnect()
	{
		Packet req = new Packet()
      .type(PacketType.DISCONNECT)
			.payload(new int[] { Client.id() });

    Client.send(req);
	}
}

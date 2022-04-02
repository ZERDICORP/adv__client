package requests;



import core.Client;
import core.Packet;

import constants.PacketType;



public class Request_SetBlock
{
	public Request_SetBlock()
	{
		Packet req = new Packet()
      .type(PacketType.SET_BLOCK)
      .payload(new int[] { Client.id() });

		Client.send(req);
	}
}

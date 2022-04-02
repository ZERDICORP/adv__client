package requests;



import core.Client;
import core.Packet;

import constants.PacketType;



public class Request_Connect
{
	public Request_Connect()
	{
		Packet req = new Packet()
      .type(PacketType.CONNECT);

    Client.send(req);
	}
}

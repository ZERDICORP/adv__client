package requests;



import core.Client;
import core.Packet;

import constants.PacketType;



public class Request_Shoot
{
	public Request_Shoot()
	{
		Packet req = new Packet()
      .type(PacketType.SHOOT)
      .payload(new int[] { Client.id() });

		Client.send(req);
	}
}

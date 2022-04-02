package requests;



import core.Client;
import core.Packet;

import constants.PacketType;



public class Request_Move
{
	public Request_Move(int direction)
	{
		Packet req = new Packet()
      .type(PacketType.MOVE)
      .payload(new int[] {
				Client.id(),
				direction
			});

		Client.send(req);
	}
}

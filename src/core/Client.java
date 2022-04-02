package core;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.io.IOException;

import gui.Window;

import constants.LogMsg;
import constants.PacketType;

import requests.Request_Connect;
import requests.Request_Disconnect;

import tools.Tools;

import core.Packet;



public class Client
{
  private static DatagramSocket socket;
	private static InetAddress address;
	private static int port;
	private static int id; 
  private static boolean gameRunning;
  private static Window window; 
  private static byte[] buffer;
  
  static
  {   
    buffer = new byte[8000];
  }   
 
  public static int id() { return id; }

	public static void start()
	{
		while (socket.isConnected())
    {
    	Packet req = read();
			if (req == null)
			{
				Tools.log(LogMsg.CONNECTION_CLOSED);
				break;
			}

			if (req.type() != PacketType.UPDATE.ordinal())
			{
				if (req.type() == PacketType.KICKED_AFK.ordinal())
					Tools.log(LogMsg.KICKED_AFK);

				break;
			}

      window.parse(req.payload());
    }

		window.close();
	}

	public static boolean connect(String connString)
	{
		if (!parseConnString(connString))
		{
			Tools.log(LogMsg.INCORRECT_HOST);
			return false;
		}
		
		try
		{
			socket = new DatagramSocket();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
			return false;
		}

		socket.connect(address, port);
		
		new Request_Connect();

		Packet res = read();
		if (res == null)
			return false;

		if (res.type() != PacketType.ACCEPT.ordinal())
		{
			if (res.type() == PacketType.SERVER_IS_FULL.ordinal())
				Tools.log(LogMsg.SERVER_IS_FULL);

			return false;
		}

		int[] payload = res.payload();

		HashMap<String, Integer> fields = new PayloadParser(payload).next();

		id = fields.get("id");

		window = new Window(
			fields.get("mapWidth"),
			fields.get("mapHeight"),
			fields.get("livingSectorWidth"),
			fields.get("livingSectorHeight")
		);

		return true;
	}
	
	public static boolean parseConnString(String connString)
	{
		try
		{
			URI uri = new URI("my://" + connString);
			
			if (uri.getHost() == null || uri.getPort() == -1)
				throw new URISyntaxException(null, null);

			address = InetAddress.getByName(uri.getHost());
			port = Integer.valueOf(uri.getPort());
		}
		catch (URISyntaxException | UnknownHostException e) { return false; }

		return true;
	}

	public static Packet read()
  {   
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
   
    try 
    {   
      socket.receive(packet);
    }   
    catch (IOException e) { return null; }
 
    Packet req = new Packet(
      packet.getAddress(),
      packet.getPort()
    );  
 
    if (!req.parse(Arrays.copyOfRange(buffer, 0, packet.getLength())))
      return null;
 
    return req;
  }   
 
  public static void send(Packet packet)
  {   
    byte[] payload = packet.make();
   
    try 
    {   
      socket.send(new DatagramPacket(
        payload,
        payload.length
      )); 
    }   
    catch (IOException e)
    {   
      Tools.log(LogMsg.FAILED_TO_SEND_PACKET);
    }   
  }   
  
  public static void stop()
  {
		new Request_Disconnect();

    socket.close();
  }
}

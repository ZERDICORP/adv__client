package com.adv_client.www;



import core.Client;

import tools.Tools;

import constants.LogMsg;



public class Main
{
	public static void main(String[] args)
	{
		if (args.length == 0)
		{
			Tools.log(LogMsg.NEED_TO_SPECIFY_SERVER);
			return;
		}

		if (!Client.connect(args[0]))
		{
			Tools.log(LogMsg.FAILED_TO_CONNECT_TO_THE_SERVER);
			return;
		}

		Client.start();
	}
}

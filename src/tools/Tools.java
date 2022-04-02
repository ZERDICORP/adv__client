package tools;



public class Tools
{
	public static void log(String msg)
	{
		System.out.println(
			"[adv:log] " +
			msg.substring(0, 1).toUpperCase() + msg.substring(1) +
			".."
		);
	}
}

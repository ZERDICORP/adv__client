package gui;



import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import core.Client;

import constants.Direction;

import requests.Request_Move;
import requests.Request_Shoot;
import requests.Request_SetBlock;



public class Window extends JFrame
{
	DrawPane pane;

	{
		pane = new DrawPane();
	}

	public void parse(int[] payload) { pane.update(payload); }

	public Window(int width, int height, int livingSectorWidth, int livingSectorHeight)
	{
		super("ADV Online");

		Config.width = width * Config.sqw;
		Config.height = height * Config.sqw;
		Config.livingSectorWidth = livingSectorWidth * Config.sqw;
		Config.livingSectorHeight = livingSectorHeight * Config.sqw;
		Config.livingSectorX = (Config.width - Config.livingSectorWidth) / 2;
		Config.livingSectorY = (Config.height - Config.livingSectorHeight) / 2;
		Config.cols = Config.livingSectorWidth / Config.sqw;
    Config.rows = Config.livingSectorHeight / Config.sqw;

		pane.setSize(Config.width, Config.height + Config.panelHeight);
		add(pane);

		setSize(Config.width, Config.height + Config.panelHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		addKeyListener(new KeyAdapter()
		{
			private boolean keyDown = false;

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (keyDown)
					return;

				keyDown = true;

				switch (e.getKeyCode())
				{
					case KeyEvent.VK_ESCAPE: close(); break;
					case KeyEvent.VK_W: new Request_Move(Direction.UP.ordinal()); break;
					case KeyEvent.VK_S: new Request_Move(Direction.DOWN.ordinal()); break;
					case KeyEvent.VK_D: new Request_Move(Direction.RIGHT.ordinal()); break;
					case KeyEvent.VK_A: new Request_Move(Direction.LEFT.ordinal());	break;
					case KeyEvent.VK_E: new Request_Shoot(); break;
					case KeyEvent.VK_F: new Request_SetBlock(); break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) { keyDown = false; }
		});

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e) { close();	}
		});
	}

	public void close()
	{
		dispose();

		Client.stop();
	}
}

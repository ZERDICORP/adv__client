package gui;



import javax.swing.JPanel;
import java.util.HashMap;
import java.util.Arrays;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

import constants.ObjectType;
import constants.Direction;
import constants.Colors;

import core.PayloadParser;
import core.Client;



class DrawPane extends JPanel
{
	private Graphics2D g2d;
	private int[] payload;
	private long lastUpdateTime;
	private boolean wave = false;

	public void update(int[] p)
	{
		payload = p;
		repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (payload == null)
			return;

		g2d = (Graphics2D) g;



		/*\
		 * Drawing map grid.
		 */

		g2d.setColor(Colors.BACKGROUND);
		g2d.fillRect(0, 0, Config.width, Config.height);

		for (int row = 0; row < Config.rows; ++row)
			for (int col = 0; col < Config.cols; ++col)
			{
				g2d.setStroke(new BasicStroke(1));
				g2d.setColor(Colors.GRID);

				g2d.drawRect(
					col * Config.sqw + Config.livingSectorX,
					row * Config.sqw + Config.livingSectorY,
					Config.sqw,
					Config.sqw
				);
			}



		/*\
		 * Drawing objects based on information
		 * received from the server.
		 */

		PayloadParser parser = new PayloadParser(payload);

		while (parser.hasNext())
		{
			HashMap<String, Integer> fields = parser.next();
			
			if (fields == null)
				continue;		

			switch (ObjectType.values()[fields.get("type")])
			{
				case PLAYER:
				{
					int x = fields.get("x") * Config.sqw;
					int y = fields.get("y") * Config.sqw;

					if (fields.get("isAlive") == 0)
					{
						g2d.setStroke(new BasicStroke(6));
						g2d.setColor(Color.RED);

						g2d.drawLine(
							x	+ Config.sqwQuarter,
							y + Config.sqwQuarter,
							x + Config.sqw - Config.sqwQuarter,
							y + Config.sqw - Config.sqwQuarter
						);

						g2d.drawLine(
							x + Config.sqw - Config.sqwQuarter,
							y + Config.sqwQuarter,
							x + Config.sqwQuarter,
							y + Config.sqw - Config.sqwQuarter
						);
						
						continue;
					}

					g2d.setStroke(new BasicStroke(4));
					g2d.setColor(Colors.PLAYER[fields.get("id")]);

					g2d.drawOval(
						x + Config.sqwQuarter,
						y + Config.sqwQuarter,
						Config.sqwQuarter * 2,
						Config.sqwQuarter * 2
					);
					
					g2d.setStroke(new BasicStroke(4));
					g2d.setColor(Colors.PLAYER[fields.get("id")]);

					drawNose(fields.get("direction"), x, y, Config.sqwHalf);

					if (fields.get("id") == Client.id())
						g2d.fillOval(
							x + Config.sqwQuarter,
							y + Config.sqwQuarter,
							Config.sqwQuarter * 2,
							Config.sqwQuarter * 2
						);

					g2d.setColor(Colors.BULLET);

					if (fields.get("cartridges") >= 1)
						g2d.fillOval(
							x,
							y,
							Config.bulletW,
							Config.bulletW
						);

					if (fields.get("cartridges") >= 2)
						g2d.fillOval(
							x + Config.sqw - Config.bulletW,
							y,
							Config.bulletW,
							Config.bulletW
						);

					if (fields.get("cartridges") >= 3)
						g2d.fillOval(
							x,
							y + Config.sqw - Config.bulletW,
							Config.bulletW,
							Config.bulletW
						);

					if (fields.get("cartridges") == 4)
						g2d.fillOval(
							x + Config.sqw - Config.bulletW,
							y + Config.sqw - Config.bulletW,
							Config.bulletW,
							Config.bulletW
						);

					break;
				}

				case ENEMY:
				{
					int x = fields.get("x") * Config.sqw;
					int y = fields.get("y") * Config.sqw;

					g2d.setStroke(new BasicStroke(2));
					g2d.setColor(Colors.ENEMY);

					g2d.drawOval(
						x + 4,
						y + 4,
						Config.sqw - 8,
						Config.sqw - 8
					);
					
					if (fields.get("lifes") >= 2)
						g2d.drawOval(
							x + 10,
							y + 10,
							Config.sqw - 20,
							Config.sqw - 20
						);
					
					if (fields.get("lifes") == 3)
						g2d.fillOval(
							x + 14,
							y + 14,
							Config.sqw - 28,
							Config.sqw - 28
						);

					break;
				}

				case BLOCK:
				{
					int x = fields.get("x") * Config.sqw;
					int y = fields.get("y") * Config.sqw;
					
					g2d.setStroke(new BasicStroke(4));
					g2d.setColor(Colors.BLOCK);

					g2d.drawRect(
						x + 1,
						y + 1,
						Config.sqw - 2,
						Config.sqw - 2
					);
					
					if (fields.get("strength") >= 2)
						g2d.drawRect(
							x + 10,
							y + 10,
							Config.sqw - 20,
							Config.sqw - 20
						);
					
					if (fields.get("strength") == 3)
						g2d.fillRect(
							x + 16,
							y + 16,
							Config.sqw - 32,
							Config.sqw - 32
						);

					break;
				}

				case GAME:
				{
					drawGameInfo(fields);

					break;
				}
			}
		}
	}

	public void drawGameInfo(HashMap<String, Integer> fields)
	{
		int y = Config.height;

		g2d.setColor(Colors.BACKGROUND);
		g2d.fillRect(0, y, Config.width, Config.panelHeight);

		int w = Config.width / 3;

		drawText(
      "players: " + fields.get("players") + " / " + Colors.PLAYER.length,
      0, Config.height, 
			w, Config.panelHeight,
      24,
      Color.WHITE
    );

		drawText(
      "blocks: " + fields.get("blocks"),
      w, Config.height, 
			w, Config.panelHeight,
      24,
      fields.get("blocks") > 0 ? Color.WHITE : Color.RED
    );

		drawText(
			"new wave: " + fields.get("timeToNewWave"),
      w * 2, Config.height, 
			w, Config.panelHeight,
      24,
      Color.WHITE
    );
		
		if (fields.get("timeToReload") > 0)
			drawText(
				"reloading.. (" + fields.get("timeToReload") + ")",
				0, 0, 
				Config.width, 100,
				24,
				Color.WHITE
			);

		drawNewWave(fields.get("timeToNewWave"));

		if (fields.get("timeToRebirth") > 0)
			drawYouDied(fields.get("timeToRebirth"));
	}

	public void drawNewWave(int timeToNewWave)
	{
		if (timeToNewWave > 0)
			wave = false;

		if (!wave && timeToNewWave == 0)
		{
			wave = true;
			lastUpdateTime = System.currentTimeMillis();
		}
		
		if (wave)
		{
			long diff = System.currentTimeMillis() - lastUpdateTime;

			if (diff < 2000)
			{
				fillShadow(0.9f);

				drawText(
					"New Wave",
					0, 0,
					Config.width, Config.height,
					60,
					Color.RED
				);
			}
		}
	}

	public void drawYouDied(int timeToRebirth)
  {
		fillShadow(0.9f);

		drawText(
			"You Died",
			0, 0,
			Config.width, Config.height,
			60,
			Color.RED
		);
		
		drawText(
			Integer.toString(timeToRebirth),
			0, Config.height / 2,
			Config.width, Config.height / 2,
			40,
			Color.WHITE
		);
  }

	public void drawText(String text, int x, int y, int w, int h, int fontSize, Color color)
  {
    Font font = new Font("Verdana", Font.BOLD, fontSize);
    FontMetrics metrics = g2d.getFontMetrics(font);

		x += (w - metrics.stringWidth(text)) / 2;
		y += ((h - metrics.getHeight()) / 2);

    g2d.setColor(color);
    g2d.setFont(font);
		g2d.drawString(text, x, y);
  }

	public void fillShadow(float alpha)
	{
		g2d.setColor(Color.BLACK);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g2d.fillRect(0, 0, Config.width + 1, Config.height + 1);
	}

	public void drawNose(int direction, int x, int y, int len)
	{
		switch (Direction.values()[direction])
		{
			case UP:
				g2d.drawLine(
					x + Config.sqwHalf,
					y + Config.sqwHalf - len,
					x + Config.sqwHalf,
					y + Config.sqwHalf
				);
				break;

			case DOWN:
				g2d.drawLine(
					x	+ Config.sqwHalf,
					y + Config.sqwHalf,
					x + Config.sqwHalf,
					y + Config.sqwHalf + len
				);
				break;

			case LEFT:
				g2d.drawLine(
					x + Config.sqwHalf,
					y + Config.sqwHalf,
					x + Config.sqwHalf - len,
					y + Config.sqwHalf
				);
				break;

			case RIGHT:
				g2d.drawLine(
					x + Config.sqwHalf + len,
					y + Config.sqwHalf,
					x + Config.sqwHalf,
					y + Config.sqwHalf
				);
				break;
		}
	}
}

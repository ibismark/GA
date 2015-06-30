package nxt.sim.ga;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NXTViewer extends JPanel
{
	private BufferedImage image;
	private ArrayList<Position> trace;
	private int radius = 3;  //センサー半径

	public NXTViewer(String file)
	{
		image = loadImage(file);
		JFrame frame = new JFrame();
		frame.add(this);
		frame.setTitle("NXTViewer");
		frame.setSize(650, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		trace = new ArrayList<Position>();
	}

	BufferedImage loadImage(String name)
	{
		try
		{
			FileInputStream in = new FileInputStream(name);
			BufferedImage bi = ImageIO.read(in);
			in.close();
			return bi;
		}
		catch (IOException e)
		{
			System.out.println(e);
			return null;
		}
	}

	public int[] getSensorValue(int x, int y)
	{
		int[] rgb = new int[3];
		rgb[0] = rgb[1] = rgb[2] = 0;
		int cnt = 0;
		for (int i = x - radius; i <= x + radius; i++)
		{
			for (int j = y - radius; j <= y + radius; j++)
			{
				int r = (int) Math.sqrt((i - x) * (i - x) + (j - y) * (j - y));
				if (r <= radius)
				{
					int value = image.getRGB(i, j);
					rgb[0] += (value >> 16) & 0xff;
					rgb[1] += (value >> 8) & 0xff;
					rgb[2] += value & 0xff;
					cnt++;
				}
			}
		}
		rgb[0] /= cnt;
		rgb[1] /= cnt;
		rgb[2] /= cnt;
		System.out.println(rgb[0] + ", " + rgb[1] + ", " + rgb[2]);

		return rgb;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2D = (Graphics2D) g;

		double imageWidth = image.getWidth();
		double imageHeight = image.getHeight();
		double panelWidth = this.getWidth();
		double panelHeight = this.getHeight();

		//画像がコンポーネントの何倍の大きさか計算
		double sx = (panelWidth / imageWidth);
		double sy = (panelHeight / imageHeight);
		double s = sx;
		if (sx > sy)
		{
			s = sy;
		}

		// スケーリング
		AffineTransform af = AffineTransform.getScaleInstance(s, s);
		g2D.drawImage(image, af, this);

		// トレース
		g2D.setColor(Color.red);
		BasicStroke wideStroke = new BasicStroke(2.0f);
		g2D.setStroke(wideStroke);
		for (int k=0; k<trace.size()-1; k++)
		{
			Position p0 = (Position) trace.get(k);
			Position p1 = (Position) trace.get(k+1);
			g2D.draw(new Line2D.Double(p0.x, p0.y, p1.x, p1.y));
		}
	}

	public void setTrace(ArrayList<Position> tr)
	{
		trace = tr;
	}

	/*
	public static void main(String[] args)
	{
		NXTViewer view = new NXTViewer();
	}
	*/
	
}

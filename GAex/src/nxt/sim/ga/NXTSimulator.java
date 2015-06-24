package nxt.sim.ga;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NXTSimulator
{
	private BufferedImage img;
	private int gw, gh;
	private int radius = 4; // �Z���T�[���a
	private int rgb[];

	public static void main(String[] args)
	{
		NXTSimulator sim = new NXTSimulator();
		for(int i=0; i<3; i++){
			System.out.println(sim.rgb[i]);
		}
	}

	public NXTSimulator()
	{
		img = loadImage("img/ETRobocon.png");
		gw = img.getWidth();
		gh = img.getHeight();
		System.out.println(gw + "\t" + gh);
		//int rgb[];
		rgb = getSensorValue(500,40); // ��
		rgb = getSensorValue(500,50); //
		rgb = getSensorValue(500,53); //
		rgb = getSensorValue(500,60); // ��
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
					int value = img.getRGB(i, j);
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
}

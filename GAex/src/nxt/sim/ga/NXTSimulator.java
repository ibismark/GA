package nxt.sim.ga;
import java.util.ArrayList;

public class NXTSimulator
{
	static double dt = 0.01; // delay
	static double r = 27; // タイヤ半径
	static double l = 110; // 車幅
	static double SL = 360; // 左輪の回転速度
	static double SR = 720; // 右輪の回転速度
	private static NXTViewer view;
	private static ArrayList<Position> trace;

	public static void main(String[] args)
	{
		view = new NXTViewer("img/ETRobocon.png");
		NXTSimulator sim = new NXTSimulator();
	}

	public NXTSimulator()
	{
		int rgb[];

		Position p0 = new Position(250,40);
		System.out.println("p0.x = " + p0.x + "\tp0.y = " + p0.y);
		Position p1 = new Position(255,40);
		System.out.println("p1.x = " + p1.x + "\tp1.y = " + p1.y);
		Position p2 = getTrace(p0,p1);
		System.out.println("p2.x = " + p2.x + "\tp2.y = " + p2.y);

		rgb = view.getSensorValue((int) p2.x, (int) p2.y);
		SL = setLWheel(rgb);
		SR = setLWheel(rgb);
		
		trace = new ArrayList<Position>();
		trace.add(p0);
		trace.add(p1);
		trace.add(p2);
		view.setTrace(trace);
		view.repaint();

		for ( int t=0; t<1000; t++ )
		{
			trace.clear();
			p0 = p1;
			p1 = p2;
			p2 = getTrace(p0,p1);
			rgb = view.getSensorValue((int) p2.x, (int) p2.y);
			SL = setLWheel(rgb);
			SR = setRWheel(rgb);

			trace.add(p0);
			trace.add(p1);
			trace.add(p2);
			view.setTrace(trace);
			view.repaint();
		}
	}

	private double setLWheel(int[] rgb)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	private double setRWheel(int[] rgb)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public static Position getTrace( Position p0, Position p1 )
	{
		// NXTの向き
		double ratio = 0;
		double theta = 0;
		if (p1.x-p0.x != 0)
		{
			ratio = (p1.y-p0.y)/(p1.x-p0.x);
			theta = Math.atan(ratio);
		}
		else
		{
			if ( p1.y > p0.y )
			{
				theta = Math.PI/2.0;
			}
			else if ( p1.y < p0.y )
			{
				theta = -Math.PI/2.0;
			}
		}
		System.out.println("theta = " + theta);
		
		double diff = Math.abs(SR-SL);
		double phi = 2*Math.PI*(r/l)*diff*dt/360.0;
		double z = 0;
		double dx = 0, dy = 0;
		if ( SL == SR )
		{
			double dr = 2*Math.PI*r*dt*SL/360.0;
			dx = dr*Math.cos(theta);
			dy = dr*Math.sin(theta);
		}
		else if ( SL < SR )
		{
			z = l*SL/diff;
			dx = -(z+0.5*l)*Math.sin(theta)+(z+0.5*l)*Math.sin(theta+phi);
			dy =  (z+0.5*l)*Math.cos(theta)-(z+0.5*l)*Math.cos(theta+phi);
		}
		else
		{
			z = l*SR/diff;
			dx =  (z+0.5*l)*Math.sin(theta)-(z+0.5*l)*Math.sin(theta-phi);
			dy = -(z+0.5*l)*Math.cos(theta)+(z+0.5*l)*Math.cos(theta-phi);
		}
		System.out.println("phi = " + phi + "(" + 180*phi/3.14 + ")" + "\tz = " + z);
		System.out.println("dx = " + dx + "\tdy = " + dy);

		Position p2 = new Position(p1.x+dx,p1.y+dy);
		return p2;
	}
}

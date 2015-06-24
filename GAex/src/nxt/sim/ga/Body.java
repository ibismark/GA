package nxt.sim.ga;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class Body
{
	private static Body onlyone = new Body();
	float pid = 0.0f;

	private Body()
	{
	}

	public static Body getInstance()
	{
		return onlyone;
	}

	public void turnRight()
	{
		pid = LineSensor.getPIDCal();
		//連続カーブ時に曲がりきれない
		//スピードを緩めることで解決可能だが当然タイムは低下する
		//pid値が大きくなれば左車輪と右車輪の速度差が大きくなり大きく旋回する
		LWheel.setSpeed(520 + pid);
		RWheel.setSpeed(320 - pid);
		LWheel.forward();
		RWheel.forward();
	}

	public void turnLeft()
	{
		pid = LineSensor.getPIDCal();
		LWheel.setSpeed(400 - pid);
		RWheel.setSpeed(500 + pid);
		LWheel.forward();
		RWheel.forward();
	}

	static NXTRegulatedMotor LWheel = Motor.C; 
	static NXTRegulatedMotor RWheel = Motor.A;
}

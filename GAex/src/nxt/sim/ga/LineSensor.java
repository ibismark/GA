package nxt.sim.ga;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.ColorHTSensor;

public class LineSensor extends Thread
{
	//singletonパターン
	private static LineSensor onlyone = new LineSensor();
	PIDcontrol pid;
	static float value; //時点でのpid値格納変数
	int current = 0;	//時点でのセンサー値格納変数
	//int Smax = 800, Smin = 10;

	private LineSensor()
	{
		//限界感度法は不使用
		pid = new PIDcontrol(1.6F, 0.03F, 0.003F);
	}

	public static LineSensor getInstance()
	{
		return onlyone;
	}

	public void readyGo()
	{
		aSwitch = true;
		start();
	}

	public void run()
	{
		while (aSwitch)
		{
			//光の関係上青で判定すると安定、赤だと不安定なことを確認(環境の問題)
			current = aSensor.getColor().getBlue();
			
			//pidの算出 
			value = pid.calcPID(current, 20);
			
			//左向きに走行
			//センサー値が20より小さければ黒及び灰色、大きければ白と判定
			if (current < 20)
				//左旋回
				Body.getInstance().turnLeft();
			else
				//右旋回
				Body.getInstance().turnRight();
			try
			{
				sleep(4);
			}
			catch (Exception e)
			{
			}

		}

	}

	private boolean aSwitch = false;
	private static ColorHTSensor aSensor = new ColorHTSensor(SensorPort.S3);

	public static float getPIDCal()
	{
		//pid値
		return value;
	}
}

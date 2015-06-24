package nxt.sim.ga;

public class PIDcontrol {
    private final float DELTA = 0.01F; //PID制御が呼ばれる周期
    private float Kp = 0.0F;
    private float Ki = 0.0F;
    private float Kd = 0.0F;
    private float difference[] = { 0.0F, 0.0F};
    private float integral = 0.0F;

    PIDcontrol(float p, float i, float d){
        this.Kp = p;
        this.Ki = i;
        this.Kd = d;
        difference[1] = integral = 0.0F;		
    }

    void resetPID(){
        difference[1] = integral = 0.0F;
    }

    float calcPID(int currentValue, int targetValue){
        float p, i, d;
        difference[0] = difference[1];
        difference[1] = currentValue - targetValue;
        //変化が無ければ0に初期化
        if(difference[0] == difference[1])
        	integral = 0;
        integral += (difference[0] + difference[1]) / 2.0 * DELTA;
        p = Kp * difference[1];
        i = Ki * integral;
        d = Kd * (difference[1] - difference[0]) / DELTA;
        return (p + i + d);
    }
}

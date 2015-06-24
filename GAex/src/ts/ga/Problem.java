package ts.ga;

//import java.util.zip.ZipEntry;

public class Problem {
	private double f;
	private int problem, dim;
	public double upp, low;

	public Problem( int no, int d ) {
		problem = no;
		dim = d;
		if ( problem == 0 ) {
			upp = 5.12;
			low = -5.12;
		}
		else if ( problem == 1 ) {
			upp = Math.PI;
			low = 0;
		}
	}

	public double eval( double[] x )
	{
		if ( problem == 0 ) {
			f = sphere(x);
		}
		else if ( problem == 1 ) {
			f = michalewicz(x);
		}
		return f;
	}

	public double sphere( double[] x )
	{
		f = 0;
		for ( int i=0; i<dim; i++ ) {
			f += x[i]*x[i];
		}
		return f;
	}

	public double michalewicz( double[] x )
	{
		f = 0;
		for ( int i=0; i<dim; i++ ) {
			f += Math.sin(x[i])*Math.pow(Math.sin((i+1)*x[i]*x[i]/Math.PI),20);
		}
		return -f;
	}
	
	
	/*
	public static void main(String[] args){
		Problem pr = new Problem(0,  5);
		double x[] = new double[5];
		x[0] = x[1] = x[2] = x[3] = x[4] = -1;
		System.out.println("eval = " + pr.eval(x));
		//1.19
		
	}
	*/


}

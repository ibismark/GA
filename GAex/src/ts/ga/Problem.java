package ts.ga;

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

}

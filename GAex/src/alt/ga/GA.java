package alt.ga;

public class GA
{

	public static void main(String[] args)
	{
		int problem, dim, size, length;
		problem = 1;
		dim = 5;
		size = 100;
		length = 24;
		double p = 0.05;
		
		int generation = 10000;
		Problem pr = new Problem(problem,dim);
		double vtr = pr.vtr;

		Population pop = new Population(problem, dim, size, length);

		for ( int i=0; i<generation; i++ )
		{
			pop.crossover();
			pop.mutation(p);
			pop.selection();

			double fitness = pop.getBest();

			System.out.println(i + "\t" + fitness);
			
			if ( fitness < vtr )
			{
				break;
			}
		
			//pop.showAll();
		}
	
	}

}

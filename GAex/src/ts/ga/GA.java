package ts.ga;

public class GA {

	//private int problem;

	//private int dim;

	//private int generation;

	//private int population_size;
	
	//private length;

	public static void main(String[] args) {
		int problem =1;
		int dim=10;
		int population_size=200;
		int generation=0;
		int length = 10;
		Population p = new Population(dim, length, problem, generation, population_size);
		p.selection();
	}

}

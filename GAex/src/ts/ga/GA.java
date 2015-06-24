package ts.ga;

public class GA {

	public static void main(String[] args) {
		int problem =1;
		int dim=5;
		int population_size=100;
		int generation=0;
		int length = 24;
		Population p = new Population(dim, length, problem, generation, population_size);
		
		while(p.getbest()>-4.687){
			p.selection();
			generation++;
		}
		System.out.println(p.getbest());
		System.out.println(generation);
	}

}

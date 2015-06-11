package ts.ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Population {

	private int problem;		//problem number
	private int dim;			//dimension
	private int dna_length;		//dimension * length 
	private int generation;
	private int length;			//individual dna length
	private ArrayList<DNA> p;	//population
	private int N;				//population size
	private DNA[] t;			//dna array
	private int sum;
	
	//二次元配列で管理した方が良い？
	private int[] c_array1;		//child dna array
	private int[] c_array2;
	//private DNA child1;
	//private DNA child2;
	//private int[] g_code;

	
	public Population(int d, int len, int no, int ge, int po) {
		dim = d;
		length = len;
		dna_length = dim * length;
		problem = no;
		generation = ge;
		N = po;
		p = new ArrayList<DNA>(N);
		t = new DNA[N];
		
		sum=0;
		
		for(int i=0; i<N; i++){
			int[] g_code = new int[dna_length]; 	//gray code
			for(int j=0; j<dna_length; j++){
				double r = Math.random();
				if(r<0.5){
					g_code[j] = 1;
				}else{
					g_code[j] = 0;
				}
			}
			t[i] = new DNA(dim, length, g_code);	//create dna
			p.add(t[i]);		//add arraylist to DNA object
			//System.out.println(p.get(i).evaluation(problem));
		}
	}

	
	//一点交叉
	//後で確率を追加＆二点交叉に変更
	public void crossover(DNA p1, DNA p2) {
		//int pair = N/2;
		//int rnd = (int)(Math.random()*dna_length);
		int[] te1 = p1.getDNA();	//get gray code of parents
		int[] te2 = p2.getDNA();
		c_array1 = new int[dna_length];
		c_array2 = new int[dna_length];
		//int cross_p;				//probability of crossover
		int rep_line;				//location crossover
		
		c_array1 = (int[])te1.clone();	//clone() : copy array 
		c_array2 = (int[])te2.clone();
		//child1 = new DNA(dim, length);
		//child2 = new DNA(dim, length);
		System.out.println(Arrays.toString(c_array1));
		System.out.println(Arrays.toString(c_array2));
		rep_line = (int)(Math.random()*dna_length)+1;
		System.out.println(rep_line);
		
		//perform crossover
		for(int i=rep_line;i<dna_length;i++){
			c_array1[i] = te2[i];
			c_array2[i] = te1[i];
		}
		System.out.println(Arrays.toString(c_array1));
		System.out.println(Arrays.toString(c_array2));
	}
	
	/*
	public void crossover_2(DNA p1, DNA p2){
		int[] te1 = p1.getDNA();	//get gray code of parents
		int[] te2 = p2.getDNA();
		c_array1 = new int[dna_length];
		c_array2 = new int[dna_length];
		//int cross_p;				//probability of crossover
		int rep_line1;				//location crossover
		int rep_line2;
		
		c_array1 = (int[])te1.clone();	//clone() : copy array 
		c_array2 = (int[])te2.clone();
		//child1 = new DNA(dim, length);
		//child2 = new DNA(dim, length);
		System.out.println(Arrays.toString(c_array1));
		System.out.println(Arrays.toString(c_array2));
		rep_line1 = (int)(Math.random()*dna_length)+1;
		rep_line2 = (int)(Math.random()*dna_length)+1;
		System.out.println(rep_line1);
		System.out.println(rep_line2);
		
		//perform crossover
		for(int i=rep_line;i<dna_length;i++){
			c_array1[i] = te2[i];
			c_array2[i] = te1[i];
		}
		System.out.println(Arrays.toString(c_array1));
		System.out.println(Arrays.toString(c_array2));
	}
	*/

	
	public void mutation() {
		double mu_p;
        for(int i=0;i<dna_length;i++){
        	mu_p = Math.random()*1000;
        	if(mu_p <= 10){
        		sum++;
        		//System.out.println("mutation!");
        		c_array1[i] = ~c_array1[i];
        	}
        }
        for(int i=0;i<dna_length;i++){
        	mu_p = Math.random()*1000;
        	if(mu_p <= 10){
        		sum++;
        		//System.out.println("mutation!");
        		c_array2[i] = ~c_array2[i];
        	}
        }

	}

	
	public void selection() {
		int rnd1, rnd2;
		DNA parent1, parent2, child1, child2;
		double[] srt_array = new double[4];
		double min, temp=0.0;
		int k;
		//HashMap<Character, Double> srt_dict = new HashMap<Character, Double>();
		/*
		for(int i=0;i<N;i++){
			System.out.println(p.get(i).evaluation(problem));
		}
		*/
		for(int i=0;i<20;i++){
		rnd1 = (int)(Math.random()*N);
		rnd2 = (int)(Math.random()*N);
		
		parent1 = p.get(rnd1);
		parent2 = p.get(rnd2);
		//System.out.println(parent1.evaluation(problem));
		//System.out.println(parent2.evaluation(problem));
		
		crossover(parent1, parent2);
		mutation();
		
		//2*pair分だけ子供の配列を作ると良い？
		child1 = new DNA(dim, length, c_array1);
		child2 = new DNA(dim, length, c_array2);
		
		srt_array[0] = parent1.evaluation(problem);
		srt_array[1] = parent2.evaluation(problem);
		srt_array[2] = child1.evaluation(problem);
		srt_array[3] = child2.evaluation(problem);
		
		for(int j=0;j<4;j++){
			if(srt_array[0] > srt_array[j])
				k=j;
		}
		
		System.out.println(Arrays.toString(srt_array));		
		Arrays.sort(srt_array);
		System.out.println(Arrays.toString(srt_array));
		
		//p.set(rnd1, )
		
		}
		
		
		//System.out.println(child1.evaluation(problem));
		System.out.println("generate mutation : " +sum);
	}
	
	public void run() {
	}

}

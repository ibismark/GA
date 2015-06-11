package ts.ga;


public class DNA {

	private int[] dna;  //突然変異をするメソッドを加える
	//private int problem;
	private int dim;
	private int length;
	private int dna_length;	
	private int[] dna_bin;
	private int[] dec;
	private double[] x;
	
	public DNA(int d, int len, int[] gr) {
		dim = d;  //次元
		length = len;
		dna_length = dim * length;
		dna = gr;
		
		/*
		dna = new int[dna_length]; //gray_code
		for(int i=0; i<dna_length; i++){
			double r = Math.random();
			if(r<0.5){
				dna[i] = 1;
			}else{
				dna[i] = 0;
			}
		}
		*/
				
		//for(int i=0;i<dna_length;i++){
		//	System.out.println(dna[i]);
		//}
	}

	
	public int[] getDNA(){
		int[] ddna = dna;
		return ddna;
	}
	
	//grayコードを2進数へ変換
	public void toBin(){
		dna_bin = new int[dna_length];
		for ( int i=0; i<dna_length; i++ ) {
			if ( i%length == 0 ) {
				dna_bin[i] = dna[i];
			}
			else {
				dna_bin[i] = dna[i] ^ dna_bin[i-1];
			}
		}
		/*
		for(int i=0;i<dna_length;i++){
			System.out.println(dna_bin[i]);
		}
		*/
	}

	
	//2進数を10進数へ変換
	public void toDec(){
		int i,j,num;
		int le=0, base;
		dec = new int[dim];
		for(j=0; j<dim; j++){
			le=j*length;
			num=0;
			base=1;
			//System.out.println(le);
			//System.out.println((j+1)*length);
			for(i=le;i<(j+1)*length;i++){
				if(dna_bin[i]==1){
					num=num+base;
				}
				base*=2;
			}
			dec[j] = num;
			//System.out.println(num);
		}
	}
	
	public void toDouble(double upp, double low){
		x = new double[dim];
		for(int i=0;i<dim;i++){
			x[i] = dec[i]*(upp-low) / (Math.pow(2, length)-1)+low;
		}
		//System.out.println(x[0]);
		//System.out.println(x[1]);
	}
	
	public double evaluation(int no) {
		Problem pr = new Problem(no, dim);
		toBin();
		toDec();
		toDouble(pr.upp, pr.low);
		double eval = pr.eval(x);
		//System.out.println(eval);
		return eval;
	}

}

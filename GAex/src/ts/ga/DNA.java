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
		for(int j=0; j<dna_length; j++){
			double r = Math.random();
			if(r<0.5){
				dna[j] = 1;
			}else{
				dna[j] = 0;
			}
		}
		*/
	}

	
	/*
	public DNA(int[] c, int d){
		
	}
	*/
	
	
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
			for(i=le;i<(j+1)*length;i++){
				if(dna_bin[i]==1){
					num=num+base;
				}
				base*=2;
			}
			dec[j] = num;
		}
	}
	
	public void toDouble(double upp, double low){
		x = new double[dim];
		for(int i=0;i<dim;i++){
			x[i] = dec[i]*(upp-low) / (Math.pow(2, length)-1)+low;
		}
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
	
	
	/*
	public static void main(String[] args){
		double[] g_code= new double[3];
		for(int j=0; j<3; j++){
			double r = Math.random();
			if(r<0.5){
				g_code[j] = 1;
			}else{
				g_code[j] = 0;
			}
		}
		//DNA dna = new DNA(1, 3, g_code);
	}
	*/

}

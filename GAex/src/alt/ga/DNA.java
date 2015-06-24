package alt.ga;

public class DNA
{

	private int[] dna;
	private int dim, length, dna_length;
	private int bin[], dec[];
	private double x[];

	public DNA(int d, int len)
	{
		dim = d;
		length = len;
		dna_length = dim * length;
		dna = new int[dna_length];
		for (int i = 0; i < dna_length; i++)
		{
			double r = Math.random();
			if (r < 0.5)
			{
				dna[i] = 1;
			}
			else
			{
				dna[i] = 0;
			}
		}
	}

	public DNA(int[] c1, int d)
	{
		dim = d;
		dna_length = c1.length;
		length = dna_length / dim;
		dna = new int[dna_length];
		for (int i = 0; i < dna_length; i++)
		{
			dna[i] = c1[i];
		}
	}

	public double evaluation(int no)
	{
		Problem pr = new Problem(no, dim);

		bin = new int[dna_length];
		for (int i = 0; i < dna_length; i++)
		{
			if (i % length == 0)
			{
				bin[i] = dna[i];
			}
			else
			{
				bin[i] = dna[i] ^ bin[i - 1];
			}
		}

		dec = new int[dim];
		for (int i = 0; i < dim; i++)
		{
			dec[i] = 0;
			for (int j = 0; j < length; j++)
			{
				// dec[i] += Math.pow(2, j)*bin[i*dim+j];
				dec[i] += Math.pow(2, j) * bin[i * length + j];
			}
		}

		x = new double[dim];
		for (int i = 0; i < dim; i++)
		{
			x[i] = dec[i] * (pr.upp - pr.low) / (Math.pow(2, length) - 1)
					+ pr.low;
		}

		double eval = pr.eval(x);
		return eval;
	}

	public int get(int i)
	{
		return dna[i];
	}

	public void mutation(double p)
	{
		for (int i = 0; i < dna_length; i++)
		{
			double r = Math.random();
			if (r < p)
			{
				dna[i] = 1 - dna[i];
			}
		}
	}

	public static void main(String[] args)
	{
		DNA dna = new DNA(1, 4);
		for (int i = 0; i < dna.dna_length; i++)
		{
			System.out.print(dna.dna[i]);
		}
		System.out.println();
		System.out.println("eval = " + dna.evaluation(0));
		for (int i = 0; i < dna.dna_length; i++)
		{
			System.out.print(dna.bin[i]);
		}
		System.out.println();
		for (int i = 0; i < dna.dim; i++)
		{
			System.out.print(dna.dec[i]);
		}
		System.out.println();
	}

}

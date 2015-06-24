package alt.ga;

import java.util.ArrayList;

public class Population
{
	private int problem, dim, size, length, dna_length;
	private ArrayList<DNA> population;
	private DNA parent1, parent2, child1, child2;
	private double best = 1.0e+4;

	public Population(int no, int d, int s, int len)
	{
		problem = no;
		dim = d;
		size = s;
		length = len;
		dna_length = dim * length;
		// �̏W�c���`
		population = new ArrayList<DNA>(size);
		// �̂�size���������C�̏W�c�֒ǉ�
		for (int i = 0; i < size; i++)
		{
			DNA dna = new DNA(dim, length);
			population.add(dna);
		}
		// �����Ƀ\�[�g
		sort();
		// showAll();
		// �ŗǌ̎擾
		DNA bestDNA = (DNA) population.get(0);
		// �ŗǌ̊֐��l�擾
		best = bestDNA.evaluation(problem);
	}

	public void crossover()
	{
		// �e1�I��
		int index = (int) (Math.random() * population.size());
		parent1 = population.remove(index);

		// �e2�I��
		index = (int) (Math.random() * population.size());
		parent2 = population.remove(index);

		// ��l����
		int c1[] = new int[dna_length];
		int c2[] = new int[dna_length];
		for (int i = 0; i < dna_length; i++)
		{
			if (Math.random() < 0.5)
			{
				c1[i] = parent1.get(i);
				c2[i] = parent2.get(i);
			}
			else
			{
				c1[i] = parent2.get(i);
				c2[i] = parent1.get(i);
			}
		}
		// �q�̐���
		child1 = new DNA(c1, dim);
		child2 = new DNA(c2, dim);
	}

	public void mutation(double p)
	{
		// �m��p�œˑR�ψٓK�p
		child1.mutation(p);
		child2.mutation(p);
	}

	public void selection()
	{
		// 4�̂��W�c�֖߂�
		population.add(parent1);
		population.add(parent2);
		population.add(child1);
		population.add(child2);

		// �����\�[�g
		sort();

		// �ň��̓���
		population.remove(population.size() - 1);
		population.remove(population.size() - 1);

		// �ŗǌ̎擾
		DNA bestDNA = (DNA) population.get(0);
		// �ŗǌ̊֐��l�擾
		best = bestDNA.evaluation(problem);
	}

	public void sort()
	{
		for (int j = 0; j < population.size() - 1; j++)
		{
			for (int i = 0; i < population.size() - 1 - j; i++)
			{
				DNA obj0 = (DNA) population.get(i);
				DNA obj1 = (DNA) population.get(i + 1);
				if (obj0.evaluation(problem) > obj1.evaluation(problem))
				{
					// ����ւ�
					DNA obj = population.remove(i);
					population.add(i + 1, obj);
				}
			}
		}
	}

	public double getBest()
	{
		return best;
	}

	public void showAll()
	{
		for (int i = 0; i < population.size(); i++)
		{
			DNA obj = (DNA) population.get(i);
			System.out.println(obj.evaluation(problem));
		}
	}

}

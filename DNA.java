import java.util.*;
public class DNA extends Genome<Codon>
{
	//public static int LENGTH = 60;
	public int LENGTH;
	public DNA(int size)
	{
		this.geneSequence = new ArrayList<Codon>();
		for(int i = 0; i < size; i++)
			geneSequence.add(new Codon());
		this.LENGTH = size;
	}

	public DNA(ArrayList<Codon> geneSequence, int size)
	{
		this.geneSequence = new ArrayList<Codon>(geneSequence);
		this.LENGTH = size;
	}

	public void mutate() {
		Random random = new Random();
		geneSequence.get(random.nextInt(LENGTH-1)).mutate();
	}

	public DNA crossover(DNA genome, int point)
	{
		ArrayList<Codon> newSequence = new ArrayList<Codon>();
		int max = point<LENGTH? point:LENGTH;
		max = (max==0)? 1:max;
		//System.out.println(point);
		for(int i = 0; i < max; i++)
		{
			newSequence.add(this.geneSequence.get(i));
		}

		for(int i = max; i < LENGTH; i++)
		{
			newSequence.add(genome.geneSequence.get(i));
		}

		DNA newDNA = new DNA(newSequence, genome.LENGTH);
		return newDNA;
	}

	public String getProteinSequence()
	{
		String protein = "";
		for(int i = 0; i < this.geneSequence.size(); i++)
		{
			protein = protein.concat(this.geneSequence.get(i).getAminoAcid());
		}
		return protein;
	}

	public String toString()
	{
		String sequence = "";
		for(int i = 0; i < this.geneSequence.size(); i++)
		{
			sequence = sequence.concat(this.geneSequence.get(i).toString());
		}
		return sequence;
	}
}
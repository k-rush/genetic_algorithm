import java.util.*;


/**
 *  A list of a number of Codons.  An instance of DNA will code for a sequence of amino acids, representing a biological gene.
 * 	DNA instances can be crossed over, where segments of the strand are switched with another DNA instance, during reproduction.
 *	Individual codons within the DNA can be mutated.  DNA will be scored based on how close it is to the goal protein in the  
 *	genetic algorithm.
 **/
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

	/**
	 * Mutates a random Codon within sequence.  Used in reproduction phase of algorithm.
	 **/
	public void mutate() {
		Random random = new Random();
		geneSequence.get(random.nextInt(LENGTH-1)).mutate();
	}

	/**
	 * Switches two segments of DNA after a certain point.  Used in reproduction phase of algorithm.
	 **/
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

	/**
	 * Returns string of amino acids each Codon codes for.
	 **/
	public String getProteinSequence()
	{
		String protein = "";
		for(int i = 0; i < this.geneSequence.size(); i++)
		{
			protein = protein.concat(this.geneSequence.get(i).getAminoAcid());
		}
		return protein;
	}

	/**
	 * Returns each individual nucleotide base within sequence of Codons.
	 **/
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
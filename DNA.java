import java.util.*;
public class DNA extends Genome<Codon>
{
	public static final LENGTH = 60;

	public DNA() 
	{
		this.geneString = new ArrayList<Codon>();
		for(int i = 0; i < LENGTH; i++)
			geneString.add(new Codon());
	}

	public DNA(ArraYList<Codon> geneString)
	{
		this.geneString = new ArrayList<Codon>(geneString);
	}

	public DNA crossover(DNA genome, int point)
	{
		ArrayList<Codon> newString = new ArrayList<Codon>();
		int max = point<length? point:length;
		for(int i = 0; i < max; i++)
		{
			newString().add(this.geneString.get(i));
		}

		for(int i = max; i < length; i++)
		{
			newString.add(geneome.geneString.get(i));
		}

		DNA newDNA = new DNA(newString);
		return newDNA;
	}
}
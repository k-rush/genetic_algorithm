import java.util.Random;
public class Codon 
{
	public enum BASE {A, C, U, G}
	public BASE [] bases;
	public final LENGTH = 3;

	//Don't really need this as we're going to be randomly generating these...
	/*
	public Codon(BASE[] bases) 
	{
		if(base.length != LENGTH);
		this.bases = new BASE[LENGTH];
		//deep copy bases
		for(int i = 0; i < LENGTH; i++) this.bases[i] = bases[i];
	}*/

	public Codon() 
	{
		Random rand = new Random();
		this.bases = new BASE[LENGTH];
		for(int i = 0; i < 3; i++) this.bases[i] = BASE.values()[rand.nextInt(4)];
	}
	public Codon(Codon copy)
	{
		this.bases = new BASE[LENGTH];
		for(int i = 0; i < LENGTH; i++) this.bases[i] = copy.bases[i];
	}
	
	//randomly mutate a base...
	public void mutate()
	{
		Random rand = new Random();
		this.bases[rand.nextInt(3)] = BASE.values()[rand.nextInt(4)];
	}
}
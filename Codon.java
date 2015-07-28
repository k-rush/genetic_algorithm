import java.util.Random;

/**
 * Codon
 * Individual segments of the DNA strands.  Codons consist of three bases, which represent nucleobases in biology.  
 * These bases are coded by a character - A, G, C or U (Adenosine, Guanosine, Cytidine, Uridine).  
 * A Codon, a set of 3 bases, will code for a specific amino acid during protein synthesis.
 */
public class Codon
{
	public enum BASE {A, C, U, G}
	public BASE [] bases;
	public static int LENGTH = 3;

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

	public String toString()
	{
		String s = "";
		for(int i = 0; i < LENGTH; i++)
		{
			switch(this.bases[i])
			{
				case A: 
					s = s.concat("A");
					break;
				case C:
					s = s.concat("C");
					break;
				case U:
					s = s.concat("U");
					break;
				case G:
					s = s.concat("G");
					break;
			}
			
		}
		return s;
	}

	/** Returns a 1 character string of the amino acid a Codon will code for.
	 */
	public String getAminoAcid()
	{
		String Amino = "";
		switch(this.toString())
		{

			case "GCU": case "GCC": case "GCA": case "GCG": 
				//Alanine
				Amino = "A";
				break;
			case "CGU": case "CGC": case "CGA": case "CGG": case "AGA": case "AGG": 
				//Arganine
				Amino = "R";
				break;
			case "AAU": case "AAC":
				//Asparagine
				Amino = "N";
				break;
			case "GAU": case "GAC":
				//Aspartic Acid
				Amino = "D";
				break;
			case "UGU": case "UGC":
				//Cys
				Amino = "C";
				break;
			case "CAA": case "CAG":
				//Gln
				Amino = "Q";
				break;
			case "GAA": case "GAG":
				//Glu
				Amino = "E";
				break;
			case "GGU": case "GGC": case "GGA": case "GGG":
				//Gly
				Amino = "G";
				break;
			case "CAU": case "CAC":
				//His
				Amino = "H";
				break;
			case "AUU": case "AUC": case "AUA":
				//Ile
				Amino = "I";
				break;
			case "UUA": case "UUG": case "CUU": case "CUC": case "CUA": case "CUG": 
				//Leu
				Amino = "L";
				break;
			case "AAA": case "AAG":
				//Lys
				Amino = "K";
				break;
			case "AUG":
				//Met
				Amino = "M";
				break;
			case "UUU": case "UUC":
				//Phe
				Amino = "F";
				break;
			case "CCU": case "CCC": case "CCA": case "CCG":
				//Pro
				Amino = "P";
				break;
			case "UCU": case "UCC": case "UCA": case "UCG": case "AGU": case "AGC": 
				//Ser
				Amino = "S";
				break;
			case "ACU": case "ACC": case "ACA": case "ACG":
				//Thr
				Amino = "T";
				break;
			case "UGG":
				//Trp
				Amino = "W";
				break;
			case "UAU": case "UAC":
				//Tyr
				Amino = "Y";
				break;
			case "GUU": case "GUC": case "GUA": case "GUG":
				//Val
				Amino = "V";
				break;
			case "UAA": case "UGA": case "UAG":
				//STOP
				Amino = "0";
				break;


		}
		return Amino;
	}
}
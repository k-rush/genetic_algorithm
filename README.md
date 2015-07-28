# genetic_algorithm
Genetic algorithm - modelling DNA and protein synthesis. 
Kyle Rush 2015

Genetic Algorithm program written in Java.  Uses DNA and protein synthesis as the problem.  

Classes

Codon 
  Description:
    Individual segments of the DNA strands.  Codons consist of three bases, which represent nucleobases in biology.  These bases
    are coded by a character - A, G, C or U (Adenosine, Guanosine, Cytidine, Uridine).  A Codon, a set of 3 bases, will code for
    a specific amino acid during protein synthesis.
  Methods:
    Codon(), Codon(Codon copy)
      Constructor, copy constructor.
    void mutate()
      Changes a random base within the Codon.  Used in reproduction phase of algorithm.
    String toString()
      Returns string representation of the Codon. (3 bases as characters.)
    String getAminoAcid()
      Returns the amino acid (as a 1 character string) the codon will code for.
      

abstract Genome<T>
  Description:
    Very simple abstract class, the only attribute being the collection (sequence) of genes.

DNA extends Genome<Codon>
  Description:
    A list of a number of Codons.  An instance of DNA will code for a sequence of amino acids, representing a biological gene.
    DNA instances can be crossed over, where segments of the strand are switched with another DNA instance, during reproduction.
    Individual codons within the DNA can be mutated.  DNA will be scored based on how close it is to the goal protein in the  
    genetic algorithm.
  Methods:
    DNA(int size), DNA(ArrayList<Codon> geneSequence, int size)
      Constructors.
    void mutate()
      Mutates a random Codon within sequence.  Used in reproduction phase of algorithm.
    DNA crossover(DNA genome, int point)
      Switches two segments of DNA after a certain point.  Used in reproduction phase of algorithm.
    String getProteinSequence()
      Returns string of amino acids each Codon codes for.
    String toString()
      Returns each individual nucleotide base within sequence of Codons.


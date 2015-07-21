import java.util.*;
public abstract class Genome<T>
{
	public ArrayList<T> geneString;
	public abstract Genome<T> crossover(Genome<T> genome, int point);
}
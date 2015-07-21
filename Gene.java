public abstract class Gene<T>
{
	public T gene;
	public Gene(T gene){
		this.gene = gene;
	}

	public abstract void mutate();
	public abstract void mutate(T mutated);

}
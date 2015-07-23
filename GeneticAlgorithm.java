import java.util.*;

public class GeneticAlgorithm
{
	
	public static final boolean verbose = true;
	public static final int MAX_ITER = 400;
	public static void main(String args[])
	{
		int populationSize, genomeSize = 60;
		double crossoverRate, mutationRate;
		Scanner in = new Scanner(System.in);
		Random random = new Random();

		DNA goal = new DNA(genomeSize); //Random goal DNA

		System.out.println("Welcome to Kyle's genetic algorithm.");

		System.out.println("Please enter the population size:");
		populationSize = in.nextInt();

		System.out.println("Please enter the crossover rate:");
		crossoverRate = in.nextDouble();

		System.out.println("Please enter the mutation rate:");
		mutationRate = in.nextDouble();

		DNA[] population = new DNA[populationSize];
		for(int i = 0; i < populationSize; i++) population[i] = new DNA(genomeSize); // populate with random DNA sequences.

		double[] scores = new double[populationSize];

		double totalScores;

		boolean solutionFound = false;
		int individualSolution = -1;
		int mutationRateRange = (int)Math.floor(1/mutationRate);
		int crossoverRateRange = (int)Math.floor(1/crossoverRate);
		double[] cumulativeScores = new double[populationSize];
		int iteration = 0;
		DNA solution = null;
		DNA[] newPopulation = new DNA[populationSize]; //Temp array for holding next generation.
		while(!solutionFound && iteration <= MAX_ITER)
		{
			if(verbose) System.out.println("Iteration:" + iteration);
			totalScores = 0;

			if(verbose)
			{
				System.out.println("Population:");
				for(int i = 0; i < populationSize; i++)
					System.out.println(population[i].getProteinSequence());
			}
			totalScores = 0;
			//Find scores for each DNA sequence, and check for solution.
			for(int i = 0; i < populationSize; i++)
			{
				scores[i] = score(goal, population[i]);
				totalScores += scores[i];
				if(scores[i] == 0) 
				{
					solutionFound = true;
					solution = population[i];
				}
			}
			if(verbose)
			{
				System.out.println("Scores:");
				for(int i = 0; i < populationSize; i++)
					System.out.println(scores[i]);
			}
			//Accumulate fitness scores.
			cumulativeScores[0] = scores[0];
			for(int i = 1; i < populationSize; i++)
				cumulativeScores[i] = (cumulativeScores[i-1])+(scores[i]);

			if(verbose)
			{
				System.out.println("Cumulative scores:");
				for(int i = 0; i < populationSize; i++)
					System.out.println(cumulativeScores[i]);
			}

			for(int i = 1; i < populationSize; i++)
				newPopulation[i] = null;

			//Crossover
			double randomFitness1, randomFitness2;
			for(int i = 0; i < populationSize; i+=2)
			{
					
				randomFitness1 = random.nextDouble() * cumulativeScores[populationSize-1];
				randomFitness2 = random.nextDouble() * cumulativeScores[populationSize-1];
				int index1 = Arrays.binarySearch(cumulativeScores, randomFitness1);
				if(index1<0) index1 = Math.abs(index1+1);
				int index2 = Arrays.binarySearch(cumulativeScores, randomFitness2);
				if(index2<0) index2 = Math.abs(index2+1);

				while(index1==index2)
				{
					randomFitness2 = random.nextDouble() * cumulativeScores[populationSize-1];
					index2 = Arrays.binarySearch(cumulativeScores, randomFitness2);
					if(index2<0) index2 = Math.abs(index2+1);
				}

				if(verbose) 
					{
						System.out.println("CHOICES: INDEX1: " + index1 + " INDEX2: " + index2);
					}

				if(random.nextDouble() <= crossoverRate)
				{
					DNA tempDNA1, tempDNA2;
					int crossoverPoint = random.nextInt(genomeSize);
					tempDNA1 = population[index1].crossover(population[index2], crossoverPoint);
					tempDNA2 = population[index2].crossover(population[index1], crossoverPoint);
					if(verbose) 
					{
						System.out.println("OLD PAIR:\n" + population[index1].getProteinSequence() + "\n" + population[index2].getProteinSequence());
						System.out.println("CROSSOVER: " + crossoverPoint);
						System.out.println("NEW PAIR:\n" + tempDNA1.getProteinSequence() + "\n" + tempDNA2.getProteinSequence());
					}
					newPopulation[i] = tempDNA1;
					newPopulation[i+1] = tempDNA2;
				}
				else
				{
					if(verbose) 
					{
						System.out.println("NEW PAIR (NO CROSSOVER):\n" + population[index1].getProteinSequence() + "\n" + population[index2].getProteinSequence());
						
					}
					newPopulation[i] = population[index1];
					newPopulation[i+1] = population[index2];
				}
				
			}
			



			//randomly crossover/mutate population. repeat.
			for(int j = 0; j < populationSize; j++)
			{
				

				//Mutate
				if(random.nextDouble() <= mutationRate)
				{
					newPopulation[j].mutate();
				}
			}

			for(int i = 1; i < populationSize; i++)
				population[i] = newPopulation[i];

			iteration++;
		}

		if(solutionFound)
		{
			System.out.println("Solution:" + solution.toString());
			System.out.println("Found after " + iteration + " generations.");
		}
			

		

	}

	public static double score(DNA goal, DNA individual)
	{
		String goalProtein = goal.getProteinSequence();
		String individualProtein = individual.getProteinSequence();
		if(verbose);
			//System.out.println(goalProtein + " compared to " + individualProtein);
		int distance = minDistance(goalProtein, individualProtein);
		double score = 1/(double)(distance);
		if(verbose);
			//System.out.println("Yields score: " + score);
		return score;
	}

	public static int minDistance(String word1, String word2) {
	int len1 = word1.length();
	int len2 = word2.length();
 
	// len1+1, len2+1, because finally return dp[len1][len2]
	int[][] dp = new int[len1 + 1][len2 + 1];
 
	for (int i = 0; i <= len1; i++) {
		dp[i][0] = i;
	}
 
	for (int j = 0; j <= len2; j++) {
		dp[0][j] = j;
	}
 
	//iterate though, and check last char
	for (int i = 0; i < len1; i++) {
		char c1 = word1.charAt(i);
		for (int j = 0; j < len2; j++) {
			char c2 = word2.charAt(j);
 
			//if last two chars equal
			if (c1 == c2) {
				//update dp value for +1 length
				dp[i + 1][j + 1] = dp[i][j];
			} else {
				int replace = dp[i][j] + 1;
				int insert = dp[i][j + 1] + 1;
				int delete = dp[i + 1][j] + 1;
 
				int min = replace > insert ? insert : replace;
				min = delete > min ? min : delete;
				dp[i + 1][j + 1] = min;
			}
		}
	}
 
	return dp[len1][len2];
}

	//Calculate distance between two strings.
	public static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            // j == 0; nw = lev(i - 1, j)
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }
}
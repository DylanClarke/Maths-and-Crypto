package uk.ac.ncl.ndjc9.Markov;

/**
 * 
 * @author Dylan Clarke
 * Creates a transition matrix for a simple example of an Absorbing Markov chain
 * Uses the Solver class to generate the expected value
 *
 */
public class Driver
{

	/**
	 * @param args - Class does not take any arguments
	 */
	public static void main(String[] args)
	{
		int SIZE = 2;  // the size of the transition matrix for all of the non-absorbing states in your Markov chain
				
		double[][] transitionMatrix = new double [SIZE][SIZE];

		//example transition matrix for a game where a coin is tossed
		//once per round until two heads occur
		
		//If 0 heads have occurred before this round, the probability of 0 heads is 0.5
		transitionMatrix[0][0] = 0.5;
		//If 1 heads have occurred before this round the probability of 1 head is 0.5
		transitionMatrix[0][1] = 0.5;
		//If 1 heads have occurred before this round, the probability of 0 heads is 0
		transitionMatrix[1][0] = 0;
		//If 1 heads have occurred before this round, the probability of 1 head is 0.5
		transitionMatrix[1][1] = 0.5;
		
		//The matrix doesn't consider the state where 2 heads have occurred as the game ends here
		//and hence it is the absorbing state		
		Solver mySolver = new Solver(transitionMatrix);
		System.out.println(mySolver.solve());
	}

}

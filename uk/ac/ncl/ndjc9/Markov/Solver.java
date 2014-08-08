package uk.ac.ncl.ndjc9.Markov;
import Jama.Matrix;
// Project requires JAMA library in the build path http://math.nist.gov/javanumerics/jama/

/**
 * 
 * @author Dylan Clarke
 * Takes the transition matrix for the non-absorbing states of an Absorbing Markov chain and
 * calculates the expected number of rounds until an absorbing state is reached.
 *
 */
public class Solver
{
	//the transition matrix in JAMA matrix form
	private Matrix QMatrix;

	/**
	 * Creates a solver for a given transition matrix
	 * @param transitionMatrix The transition matrix for all of the non-absorbing states in the Markov chain. The second dimension represents the cells in a row.
	 */
	public Solver (double[][] transitionMatrix)
	{
		//convert the array of doubles into a JAMA matrix
        QMatrix = new Matrix(transitionMatrix);
    
	}	
/**
 * Calculates the expected number of rounds until an absorbing state is reached for the transition matrix that the class was constructed with.
 * @return The expected number of rounds until an absorbing state is reached for the Absorbing Markov chain the transition matrix represents
 */
	public double solve()
	{
        int height = QMatrix.getRowDimension();
        int width = QMatrix.getColumnDimension();
        
        //All transition matrices must be square as they're from and to the same set of states
        //Throw an exception if it isn't
        if (height !=width)
        {
        	throw new IllegalArgumentException("Transition matrix is not square");
        }
        
        //generate an array of the same size filled with zeros 
        double[][] identity = new double[height][width];
        //put 1s in the middle diagonal to produce the identity matrix
        for (int i=0;i<height;i++)
        {
        	identity[i][i] = 1.0;
        	
        }
     
        //convert the array of doubles into a JAMA matrix
        Matrix IMatrix = new Matrix(identity);
        //subtract the transition matrix from the identity matrix
        Matrix NMatrix = IMatrix.minus(QMatrix);
        //take the inverse of the result
        Matrix invMatrix = NMatrix.inverse();
        
        double expectedValue = 0.0;
        //the expected value is the sum of the top row of the inverse matrix, so add them
        for (int i =0; i < width; i++)
        {
        	expectedValue = expectedValue + invMatrix.get(0,i);
        	
        }
        return expectedValue;
	}

}

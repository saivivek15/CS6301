/**
 * 
 */
package cs6301.g33.shortProject4;

import cs6301.g33.utils.FibonacciHelper;

/**
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */
public class FindFibonacci {
	/**
	 * @param args: Command line arguments (No command line arguments are passed in this program)
	 */
	public static void main(String[] args) {
		
		//Input to the fibonacci helper function to calculate the fibonocci of the input.
		int findFibanocciOf = 150;
		
		//Fibonacci Of a number using O(N) algorithm
		System.out.println("Fibonacci of Number "+findFibanocciOf+": "+FibonacciHelper.linearFibonacci(findFibanocciOf));
		
		//Fibonacci Of a number using O(logN) algorithm
		System.out.println("Fibonacci of Number "+findFibanocciOf+": "+FibonacciHelper.logFibonacci(findFibanocciOf));
	}

}

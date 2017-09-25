/**
 * 
 */
package cs6301.g33.utils;

import java.math.BigInteger;

/**
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */
/*
 * Class to find the fibonacci of a given number
 */
public class FibonacciHelper {

/**
 * @param number: Number for which Fibonacci number has to be calculated
 * @return Returns the fibanocci of the number.
 */
	
	public static BigInteger linearFibonacci(int number)
	{
		//Store the previous values in an array, so that they need not be computed again.
		BigInteger[] storedFibonaccis = new BigInteger[number+1];
		//Initialize the fibonacci numbers of 0 & 1
		storedFibonaccis[0]=new BigInteger("0");
		storedFibonaccis[1] = new BigInteger("1");
		
		//Iterate from 2 till the number and use the previously calculated values
		for(int i=2;i<=number;i++)
		{
			storedFibonaccis[i] = new BigInteger((storedFibonaccis[i-1].add(storedFibonaccis[i-2]))+"");
		}
		//Return the fibanocci calculated for the number.
		return storedFibonaccis[number];
	}
	/**
	 * 
	 * @param inputMatrix: Initial matrix with values [1,1][1,0]
	 * @param number
	 */
	public static void logFibonacci(BigInteger[][] inputMatrix,int number)
	{
		if(number == 0 || number == 1)
			return ;
		logFibonacci(inputMatrix, number/2);
		BigInteger[][] tempMatrix ={{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
		multiply(inputMatrix,inputMatrix);
		
		if(number%2 != 0)
			multiply(inputMatrix,tempMatrix);
		
	}
	
	/**
	 * 
	 * @param inputMatrix: Input matrix used in multiplication
	 * @param tempMatrix: Input matrix
	 */
	public static void multiply(BigInteger[][] inputMatrix,BigInteger[][] tempMatrix)
	{
		//Multiply both matrices
		BigInteger a = new BigInteger((inputMatrix[0][0].multiply(tempMatrix[0][0])).add(inputMatrix[0][1].multiply(tempMatrix[1][0]))+"");
		BigInteger b = new BigInteger((inputMatrix[0][0].multiply(tempMatrix[0][1])).add(inputMatrix[0][1].multiply(tempMatrix[1][1]))+"");
		BigInteger c = new BigInteger((inputMatrix[1][0].multiply(tempMatrix[0][0])).add(inputMatrix[1][1].multiply(tempMatrix[1][0]))+"");
		BigInteger d = new BigInteger((inputMatrix[1][0].multiply(tempMatrix[0][1])).add(inputMatrix[1][1].multiply(tempMatrix[1][1]))+"");
		
		inputMatrix[0][0] = a;
		inputMatrix[0][1] = b;
		inputMatrix[1][0] = c;
		inputMatrix[1][1] = d;
		
	}
/**
 * 
 * @param number: Number for which fibonacci has to be calculated
 * @return
 */
	public static BigInteger logFibonacci(int number) {	
		if(number==0 || number ==1)
			return BigInteger.valueOf(number);
		else
		{
			BigInteger[][] inputMatrix ={{BigInteger.valueOf(1),BigInteger.valueOf(1)},{BigInteger.valueOf(1),BigInteger.valueOf(0)}};
			logFibonacci(inputMatrix, number-1);
			return inputMatrix[0][0];
		}
		
	}
}

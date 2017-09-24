/**
 * 
 */
package cs6301.g33.longProject1;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cs6301.g33.utils.ParsePostfixExpression;

/**
 * @author Sushma
 * @author Sai Vivek Kanaparthy
 *
 */
public class LP1L3 {

	
	/**
	 * @param args: Takes input from command Line: n=args[0]: no of input expressions( Integer Value)
	 * In consecutive args[0...n] send the expressions to be evaluated.
	 * @throws InvalidAlgorithmParameterException 
	 */
	public static void main(String[] args)  {
	
		//Input to the program
		//String[] expressions = {"a = 90569784495866770974195656280275310090138980613960953881501965823101 ;"," b = 75040970647524038461398929683905540248523961720824412136973299943953 ;","c = a b - ;","c = b a - ;",";"};
		String[] expressions = {"x = 90 ;","p = 11 ;","y = 90 p - ;",";"};
		//No of expression in the input
		int no_of_expressions = expressions.length;
		//Base value in which the integers have to be stored
		int base_value = 10;
		
		
		//Create object to call the expression parser utility methods.
		ParsePostfixExpression parseExpression = new ParsePostfixExpression();
		
		//Iterate over the input expression and evaluate each expression
		for(int index = 0 ; index< no_of_expressions; index++)
		{
			//If it is the last expression (only ;), then set lastExpression value as true else to false. 
			if(index == no_of_expressions-1)
				/*
				 * expression: The input expression that has to be evaluated.
				 * base: base in which numbers are being stored.
				 * lastExpression: Check if the expression is the last line in the sample input
				 * lineNumbers(The helper function for Level -3 & 4 is same, the lineNumbers parameter is being used in Level -4, So we need to 	
				 */
				//evaluateExpression(String expression, int base,boolean lastExpression,Map<Integer,String> lineNumbers)
				parseExpression.evaluateExpression(expressions[index], base_value,true,null,0);

			else
				parseExpression.evaluateExpression(expressions[index], base_value,false,null,0);
		}

	}

}

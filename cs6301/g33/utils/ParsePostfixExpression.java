/**
 * 
 */
package cs6301.g33.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/**
 * @author Sushma
 * @author Sai Vivek Kanaparthy
 *
 */
public class ParsePostfixExpression {

	// Array to store the values of the operands( Since they are a-z in small
	// letter, we can create a static array of length 26, and initially assign
	// them to zero)
	static BigInteger[] variableValue = new BigInteger[26];
	static BigInteger finalValue = new BigInteger("0");

	/**
	 * 
	 * @param expression
	 *            : Expression given by user
	 */
	public void evaluateExpression(String expression, int base,boolean lastExpression) {
		
		if(lastExpression)
		{	
			System.out.print(base+": ");
			while(finalValue != BigInteger.ZERO)
			{
				System.out.print(finalValue.mod(BigInteger.valueOf(base))+" ");
				finalValue = finalValue.divide(BigInteger.valueOf(base));
			}
			System.exit(-1);
		}
		// Index of the character in the expression
		int index = 0;
		// Variable to hold the left hand operand
		char temp_variable = 'a';

		// Object to use the functions of operator precedence.
		OperatorPrecedence operatorPrecedence = new OperatorPrecedence();

		// Iterate the expression and evaluate the value of left hand operand
		while (index < expression.length()) {
			// Discard the spaces in the expression
			if (expression.charAt(index) == ' ')
			{
				index++;
				continue;
			}
			// If the character is an equal to ('=') start evaluating the right
			// hand side of the expression.
			if (expression.charAt(index) == '=') {
				// Evaluate the expression an assign it to the operand
				finalValue = variableValue[temp_variable - 'a'] = evaluatePostfixExpression(
						expression.substring(index + 1, expression.length()),
						operatorPrecedence, base);
				break;
			}
			// If it is an operand, store it in a temp_variable to store the
			// value later.
			else if (expression.charAt(index) >= 'a'
					|| expression.charAt(index) <= 'z')
				temp_variable = expression.charAt(index);
			index++;
		}
	}

	/**
	 * 
	 * @param postfixExpression
	 *            (Expression after the equal to operator)
	 * @return
	 */
	BigInteger evaluatePostfixExpression(String postfixExpression,
			OperatorPrecedence operatorPrecedence, int base) {
		int index = 0;
		//System.out.println("Postifix expression is:"+postfixExpression);
		// Stack to store the values of the operands
		Stack<BigInteger> stack = new Stack<BigInteger>();
		// Temp array to hold numbers > 9
		List<Integer> temp_values = new ArrayList<Integer>();
		BigInteger number1 = new BigInteger("0");
		BigInteger number2 = new BigInteger("0");

		while (index < postfixExpression.length()) {
			// Final Value
			long temp_value = 0;
			// If character is a space just continue with the loop
			if (postfixExpression.charAt(index) == ' ')
			{
				index++;
				continue;
			}

			if (operatorPrecedence.isOperator(postfixExpression.charAt(index))) {
				if (temp_values.size() > 0) {
					// Iterate throught the list ( only if numbers are provided
					// in place of operands)
					for (int i = temp_values.size()-1; i >=0; i--) {
						temp_value += temp_values.get(i)
								* (Math.pow(base, temp_values.size()-i));
					}
					temp_values.clear();
					//System.out.println("Pushing value to stack:"+temp_value);
					stack.push(BigInteger.valueOf(temp_value));
				}
				if(!operatorPrecedence.needsOneOperand(postfixExpression.charAt(index)))
				{
					try{
					//If it is an operator pop the last two values and push the result into the stack
					number1 = stack.pop();
					number2 = stack.pop();
					}catch(EmptyStackException e)
					{
						System.out.println("Invalid postfix expression");
						throw e;
					}
					//System.out.println("Number being sent are:"+number2+" "+number1);
					stack.push(operatorPrecedence.computeValue(number2, number1, postfixExpression.charAt(index)));
				}
				else
				{
					number1 = stack.pop();
					stack.push(operatorPrecedence.computeValue(number1, BigInteger.ZERO, postfixExpression.charAt(index)));
				}
				//Intialize back to zero for next iterations
				number1 = BigInteger.ZERO;
				number2 = BigInteger.ZERO;
			}

			else {
				// If the charector is an alphabet push the value of the
				// alphabet on to stack
				if (postfixExpression.charAt(index) >= 'a'
						&& postfixExpression.charAt(index) <= 'z')
				{
					//System.out.println("Variable value is:"+postfixExpression.charAt(index));
					stack.push(variableValue[postfixExpression
												.charAt(index)-'a']);
				}
				// If it is a digit push it into the array and calculate the
				// value later
				else if (postfixExpression.charAt(index) >= '0'
						&& postfixExpression.charAt(index) <= '9')
					temp_values.add(Integer.parseInt(postfixExpression
							.charAt(index) + ""));
				else if (postfixExpression.charAt(index) == ';')
				{
					if(temp_values.size() > 0)
					{
						//System.out.println("Size"+temp_values.size());
						for (int i = temp_values.size()-1; i >=0; i--) {
							temp_value += temp_values.get(i)
									* (Math.pow(base, temp_values.size()-1-i));
						}
						temp_values.clear();
						//System.out.println("Pushing value to stack:"+temp_value);
						stack.push(BigInteger.valueOf(temp_value));
						temp_values.clear();
						break;
					}
				}
			}
			index++;
		}
		try
		{
			//If stack is empty that means there is some issue with the given postfix expression, hence throw error.
			number1 = stack.pop();
			System.out.println(number1);
			return number1;
		}catch(EmptyStackException e)
		{
			System.out.println("Invalid Postfix Expression");
			throw e;
		}
	}
}

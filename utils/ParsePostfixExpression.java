/**
 * 
 */
package cs6301.g33.utils;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import cs6301.g33.shortProject2.ShuntingYard;

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
	//
	static BigInteger finalValue = new BigInteger("0");
	//Base in which the numbers are read from input or printed to the output.
	final static int BASEVALUE=10;

	/**
	 * @param expression: Input Expression to be evaluated
	 * @param base: Base in which numbers have to stored
	 * @param lastExpression: Is this expression the last expression of the input.
	 * @param lineNumbers: Map that contains line numbers and the corresponding expressions to the line numbers(Used in Level 4, Will be null for Level 3)
	 * @param isLoopExpression: If the expression sent contains conditional 
	 */
	public void evaluateExpression(String expression, int base,boolean lastExpression,Map<Integer,String> lineNumbers,int isLoopExpression) {
		// Variable to hold the left hand operand
	
		char temp_variable = 'a';
		//Expression to print the value of variable
		if(!expression.contains("=") && lastExpression == false)
		{
			for(int i=0;i<expression.length();i++)
			{
				if(expression.charAt(i)==' ')
					continue;
				if(expression.charAt(i)>='a'&&expression.charAt(i)<='z')
					temp_variable = expression.charAt(i);
			}
			finalValue = variableValue[temp_variable-'a'];
			System.out.println(finalValue);
			return;
		}
		//If it is the last expression to be evaluated, then print the value of final variable
		if(lastExpression)
		{	
			boolean isNegative = false;
			if(finalValue.compareTo(BigInteger.ZERO) < 0)
				isNegative = true;
				
			finalValue = finalValue.abs();
			System.out.print(base+": ");
			while(finalValue.compareTo(BigInteger.ZERO) >= 0)
			{
				System.out.print(finalValue.mod(BigInteger.valueOf(BASEVALUE))+" ");
				finalValue = finalValue.divide(BigInteger.valueOf(BASEVALUE));
				if(finalValue==BigInteger.ZERO)
					break;
			}
			if(isNegative)
				System.out.print("-");
			System.exit(-1);
		}
		// Index of the character in the expression
		int index = 0;
		

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
						operatorPrecedence, base,isLoopExpression);
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
	 * @param expression: Loop Expression in the input
	 * @param base: Base in which numbers have to be stored
	 * @param LineNumber: Line Number of the loop expression if present.
	 * @param lineNumbers : Map that contains the line numbers and the expressions corresponding to the line numbers
	 * @throws InvalidAlgorithmParameterException: If the expression is not valid then throw Invalid Argument Exception.
	 */

	public void evaluateLoopExpressions(String expression,int base,int LineNumber, Map<Integer,String> lineNumbers) throws InvalidAlgorithmParameterException
	{
		//Temp variable to check the value of the conditional variable 
		char temp_variable = 'a';
		
		//If the expression has a loop condition ( Two types of conditional variables, one which has to execute from line number if the operand mentioned is not zero, else from zero ( x ? 8 : 0; or x ? 8;)
		if(expression.contains("?") || !expression.contains(":"))
		{
			//Iterate through the expression so set it to zero initially
			int temp_index=0;
			
			//Variable that is used internally whether the expression is valid or not
			boolean is_temp_variable_set = false;
			
			//Get the line number to implement the goto function
			int lineNumber = 0;
			
			//Iterate through the expression until you reach a ? because you will get the operand on which you have to perform the conditional check
			while(expression.charAt(temp_index) != '?')
			{
				//If the character is a space then continue through the loop
				if(expression.charAt(temp_index) == ' ')
				{
					temp_index++;
					continue;
				}
				//If it a charector it means you got the operand on which you have make the conditional check ( operand == 0)
				else if(expression.charAt(temp_index) >= 'a' && expression.charAt(temp_index) <= 'z')
				{
					is_temp_variable_set = true;
					//Set the temp variable to the charector found at the current position
					temp_variable = expression.charAt(temp_index);
				}
				temp_index++;
			}
			//Once the '?' is reached you have to check whether the temp variable has been set or not else it is not a valid expression
			if(is_temp_variable_set)
			{
				//Now iterate through the expression to get the line number
				while(expression.charAt(temp_index) != ';')
				{
					//Escape the space charectors
					if(expression.charAt(temp_index) == ' ')
					{
						temp_index++;
						continue;
					}
					String result;
					//Once you find the line number check if the operand that we found earlier is zero or not
					if(expression.charAt(temp_index) >= '0' && expression.charAt(temp_index) <= '9')
					{
						//Conditional check for zero
						if(variableValue[temp_variable-'a'].intValue() != 0)
						{
							//Get the line number of the expression to which you have to transfer the control
							lineNumber = Integer.parseInt(expression.charAt(temp_index)+"");
							//	At every iteration check if the variable has been zero or not
							while(variableValue[temp_variable-'a'].compareTo(BigInteger.ZERO)>0)
							{
								
								//Go the line number that you would like to traverse by iterating through the keys 
								boolean startLineFound = false;
								for(Entry<Integer,String> entryOfLineNumber : lineNumbers.entrySet())
								{
									//Check if the key is the line number that you would like to transfer the control to.
									if(entryOfLineNumber.getKey() != lineNumber && startLineFound == false)
										continue;
									if(entryOfLineNumber.getKey() == LineNumber)
										break;
									//Execute the expressions between these line numbers
									char[] output = new char[entryOfLineNumber.getValue().length()-1];
									ShuntingYard.getPostFixNotation(entryOfLineNumber.getValue().substring(0,entryOfLineNumber.getValue().length()-2), output);
									result = String.valueOf(output);
									evaluateExpression(result, base, false, lineNumbers,1);
									startLineFound = true;
								}
							}
						}
					}
					temp_index++;
				}	
			}
			else{
				throw new InvalidAlgorithmParameterException();
			}
		}
	}
	/**
	 * 
	 * @param postfixExpression
	 *            (Expression after the equal to operator)
	 * @Param operatorPrecedence: Object of the operator precedence to call the helper function
	 * @Param base: Base in which numbers have to be stored.
	 * @Param isLoopExpression: (If the expression contains '?' in the expression then it is a loop expression and it has to be processed differently).
	 * @return Value of the Postfix expression.
	 */
	BigInteger evaluatePostfixExpression(String postfixExpression,
			OperatorPrecedence operatorPrecedence, int base,int isLoopExpression){ 
		int index = 0;
		// Stack to store the values of the operands
		Stack<BigInteger> stack = new Stack<BigInteger>();
		// Temp array to hold numbers > 9
		List<BigInteger> temp_values = new ArrayList<BigInteger>();
		BigInteger number1 = new BigInteger("0");
		BigInteger number2 = new BigInteger("0");

		while (index < postfixExpression.length()) {
			// Final Value
			BigInteger temp_value = new BigInteger("0");
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
						//temp_value = temp_value.add((temp_values.get(i).multiply(new BigDecimal(Math.pow(base, temp_values.size()-i-1)).toBigInteger())));
						temp_value = temp_value.add((temp_values.get(i).multiply(BigInteger.valueOf(BASEVALUE).pow(temp_values.size()-i-1))));
					}
					temp_values.clear();
					stack.push(convertBase(temp_value,base,BASEVALUE));
				}
				if(!operatorPrecedence.needsOneOperand(postfixExpression.charAt(index)))
				{
					try{
					//If it is an operator pop the last two values and push the result into the stack
					number1 = stack.pop();
					number2 = stack.pop();
					}catch(EmptyStackException e)
					{
						throw e;
					}
					stack.push(convertBase(operatorPrecedence.computeValue(convertBase(number2, BASEVALUE, base), convertBase(number1, BASEVALUE, base), postfixExpression.charAt(index)),base,BASEVALUE));
				}
				else
				{
					number1 = stack.pop();
					stack.push(convertBase(operatorPrecedence.computeValue(convertBase(number1, BASEVALUE, base), convertBase(BigInteger.ZERO, BASEVALUE, base), postfixExpression.charAt(index)),base,BASEVALUE));
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
					if(temp_values.size() > 0)
					{
						for (int i = temp_values.size()-1; i >=0; i--) {
							temp_value = temp_value.add((temp_values.get(i).multiply(BigInteger.valueOf(BASEVALUE).pow(temp_values.size()-i-1))));
						}
						temp_values.clear();
						stack.push(convertBase(temp_value,base,BASEVALUE));
						temp_values.clear();
					}
					stack.push(convertBase(variableValue[postfixExpression
												.charAt(index)-'a'],base,BASEVALUE));
				}
				// If it is a digit push it into the array and calculate the
				// value later
				else if (postfixExpression.charAt(index) >= '0'
						&& postfixExpression.charAt(index) <= '9')
				{
					temp_values.add(new BigInteger(postfixExpression.charAt(index)+""));
				}
				else if (postfixExpression.charAt(index) == ';')
				{
					if(temp_values.size() > 0)
					{
						for (int i = temp_values.size()-1; i >=0; i--) {
							
							temp_value = temp_value.add((temp_values.get(i).multiply(BigInteger.valueOf(BASEVALUE).pow(temp_values.size()-i-1))));
						}
						temp_values.clear();
						stack.push(convertBase(temp_value,base,BASEVALUE));
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
			number2 = convertBase(number1,BASEVALUE,base);
			if(isLoopExpression != 1)
			System.out.println(number2);
			return number2;
		}catch(EmptyStackException e)
		{
			System.out.println("Invalid Postfix Expression");
			throw e;
		}
	}
	/**
	 * 
	 * @param number:Number that has to be converted to another base
	 * @param radix: Base to be converted
	 * @param base: Base of the number
	 * @return: Number converted from base radix to base base.
	 */
	
	public static BigInteger convertBase(BigInteger number, int radix, int base ) {
		//If the number is below Integer.MAX value and > Integer.MIN value then execute this condition to change the base.
		if(number.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) < 0 && number.compareTo(BigInteger.valueOf(Integer.MIN_VALUE))>0)
			return new BigInteger(Integer.toString(Integer.parseInt(number.toString(radix),base)));
		//If it is a BigInteger, the below code is executed.
		else
		{
			//Set result to zero initially
			BigInteger result = new BigInteger("0");	
			//Check if the number is a negative number
			boolean isNegative = false;
			//Check if the number is -ve using compareTo function
			if(number.compareTo(BigInteger.valueOf(0))<0)
			{
				number = number.abs();
				isNegative = true;
			}
			//Convert the BigInteger to Number	
			String num = number+"";
			int index=num.length()-1;
			//If the radix and base value are not same, then convert the number to the radix specified from the base value. 
			if(radix != BASEVALUE)
			{
				for(int i=0;number.compareTo(BigInteger.ZERO) != 0 ;i++) {
					if(number.mod(BigInteger.valueOf(radix)).compareTo(BigInteger.valueOf(9)) < 0)
						result = result.add((number.mod(BigInteger.valueOf(radix)).add(BigInteger.valueOf(48))).multiply(BigInteger.valueOf(BASEVALUE).pow(i)));
					else
					number = number.divide(BigInteger.valueOf(radix));
				}
			}
			else
			{
				for(int i=index;i>=0;i--) {
					result = result.add((BigInteger.valueOf(Integer.parseInt(num.charAt(i)+""))).multiply(BigInteger.valueOf(base).pow(index-i)));
				}
			}
			//If number is negative , calculate and then add the negative sign.
			if(isNegative)
				return result.negate();
			return result;
		}
	   }
}

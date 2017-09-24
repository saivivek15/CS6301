/**
 * 
 */
package cs6301.g33.utils;

import java.math.BigInteger;

/**
 * @author Sushma
 * @author Sai Vivek Kanaparthy
 */
public class OperatorPrecedence {
	//Set all the operator that can be used in the expression and assign ranks in the rank array accordingly 
	int rank[] = {5,5,5,5,5,5,4,3,2,2,1,1,1,1,1,1};
	char operator[]={'(',')','{','}','[',']','!','^','/','*','+','-','|','%','?',':'};
	int length = 16;
	
	//Function to check if the expression is an postfix expression
	/**
	 * 
	 * @param expression: Input Expression
	 * @return: Is the input expression a postfix expression
	 */
	public boolean isPostfixExpression(String expression)
	{
		expression = expression.substring(0,expression.length()-1);
		int len = expression.length()-2;
	
		while(expression.charAt(len) == ' ' )
			len--;
		if(isOperator(expression.charAt(len)))
			return true;
	return false;
		
	}
	
	//Function to check if the expression has an operator or not
	/**
	 * 
	 * @param expression: Input Expression
	 * @return: Whether the input expression contains an operator or not
	 */
	public boolean hasOperator(String expression)
	{
		for(int i=0;i<operator.length;i++)
			if(expression.contains(operator[i]+""))
				return true;
		return false;
	}
	public boolean needsOneOperand(char operator)
	{
		if(operator == '!' || operator == '|')
			return true;
		else
			return false;
	}
	/**
	 * 
	 * @param charector
	 * @return
	 */
	//Check if the charactor is an operator or an operand
	public boolean isOperator(char charector)
	{
		for(int index=0;index<length;index++)
		{
			 if(charector==operator[index])
				 return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param charector
	 * @return: Rank of the operator
	 */
	//Returns the rank of the operator
	public int getRankOfOperator(char charector)
	{
		for(int index=0;index<length;index++)
		{
			 if(charector==operator[index])
				 return rank[index];
		}
		return -1;
	}
	
	//Check if the charactor is an open paranthesis 
	/**
	 * 
	 * @param charector
	 * @return: Whether the charactor is an open paranthesis
	 */
	public boolean isOpenParanthesis(char charector)
	{
		if(charector == '(' || charector == '[' || charector == '{')
			return true;
		return false;
	}
	
	//Checks if the parenthesis is a closed parenthesis
	/**
	 * 
	 * @param charector
	 * @return Whether the charactor is a closed paranthesis
	 */
	public boolean isCloseParanthesis(char charector)
	{
		if(charector == ')' || charector == ']' || charector == '}')
			return true;
		return false;
	}
	/**
	 * 
	 * @param number1: Operand1
	 * @param number2: Operand2
	 * @param operator: Operator
	 * @return: Value of the both operands with the operator
	 */
	public BigInteger computeValue(BigInteger number1, BigInteger number2, char operator)
	{
		BigInteger finalValue = new BigInteger("0");
		switch(operator)
		{
		case '+':{
			return number1.add(number2);
		}
		case '-':{
			return number1.subtract(number2);
		}
		case '*':{
			return number1.multiply(number2);
		}
		case '/':{
			if(number2.equals(0))
			{
				System.out.println("Divide by Zero Error!!");
				throw new IllegalArgumentException();
			}
			return number1.divide(number2);
		}
		case '^':{
			return number1.pow(number2.intValue());
		}
		case '%':{
			if(number2.equals(0))
			{
				System.out.println("Divide by Zero Error!!");
				throw new IllegalArgumentException();
			}
			return number1.mod(number2);
		}
		case '|':{
			//System.out.println(Math.sqrt(number1.doubleValue()));
			finalValue = new BigInteger((int)Math.sqrt(number1.intValue())+"");
			return finalValue;
			}
		}
		return finalValue;
	}
}

/**
 * 
 */
package cs6301.g33.longProject1;

import java.security.InvalidAlgorithmParameterException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cs6301.g33.shortProject2.ShuntingYard;
import cs6301.g33.utils.OperatorPrecedence;
import cs6301.g33.utils.ParsePostfixExpression;

/**
 * EvaluateExpressions: Evaluates and prints the value of expressions in infix/ Postfix notation.
 * @author Sushma, Sai Vivek Kanaparthy
 */
public class LP1L4 {

	//Base in which the numbers are read from input or printed to the output.
	 final static int BASE_VALUE=10;
	
	/**
	 * Reads multiple expressions( Postfix / infix) from input and prints the value of the evaluated expression.
	 * @param args A string array containing command line arguments
	 * @throws Exception: InvalidAlgorithmParameterException (Invalid expression as input)
	 * @return No Value
	 */
	public static void main(String[] args) throws InvalidAlgorithmParameterException {

		//Input
		//String[] expressions = {"x = 10 ;","p = 1;","8 p = p * x ;","10 x = x -1 ;","15 x ? 8 ;","p ;",";"};
		String[] expressions = {"1 x = 98765432109876543210987654321098765432109876543210 ;",
			"2 y = 10 ;","3 p = 1 ;","5 p = p * x ;","6 y = y - 1 ;","7 y ? 5 ;","p ;",";"};
		//	String[] expressions = {"x = 987654321098765432109876543210987654321 ;","y = 8 ;","5 x = x * x ;","x ;","y = y 1 - ;","7 y ? 5 ;",";"};
		//No of expressions
		int no_of_expressions = expressions.length;
		//Hash map to store the line corresponding to the line number
		LinkedHashMap<Integer,String> lineNumbers = new LinkedHashMap<Integer, String>();
		//Input expression stored to be processed by the function later
		List<ConditionalExpressionsHelperClass> inputExpressions = new ArrayList<ConditionalExpressionsHelperClass>();
		//Create object for postfix expression
		ParsePostfixExpression parseExpression = new ParsePostfixExpression();
		//Base value in which numbers have to be stored.
		int base_value = 10;
		OperatorPrecedence operatorPrecedence = new OperatorPrecedence();
		int no_line_number = -1;
		
		//Iterate through the list of expressions and check which of the statements have goto conditions
		for(int i=0;i<no_of_expressions;i++)
		{
			//Create an object of HasLine number, that stores the line number and its corresponding value that says whether it has a got statement or not.
			ConditionalExpressionsHelperClass hasLineNumber = new ConditionalExpressionsHelperClass();
			
			//Initially set the line to the string expression and has goto statement to false
			hasLineNumber.setExpression(expressions[i]);
			hasLineNumber.setHasLineNumber(false);
			hasLineNumber.setLineNumber(0);
			
			//Check if the expression starts with a number, if so change the HasLineNumber to true
			if(expressions[i].charAt(0)>='0' && expressions[i].charAt(0)<='9')
			{
				int index=0;
				//List to temporarily store the line number
				List<Integer> temp_values = new ArrayList<Integer>();
				int temp_value =0;
				
				//Iterate through the expression and set the key as line number and the remaining expression as value to this number
				while(index< expressions[i].length())
				{
					if(expressions[i].charAt(index) == ' ')
					{
						//Compute the final value
						for (int j = temp_values.size()-1; j >=0; j--) {
							temp_value += temp_values.get(j)
									* (Math.pow(BASE_VALUE, temp_values.size()-1-j));
						}
						//Set the line number, conditional expression w
						lineNumbers.put(temp_value, expressions[i].substring(index+1, expressions[i].length()));
						hasLineNumber.setLineNumber(temp_value);
						temp_values.clear();
						break;
					}
					else if(expressions[i].charAt(index) >= '0' && expressions[i].charAt(index) <= '9')
					{
						temp_values.add(Integer.parseInt(expressions[i].charAt(index) + ""));
					}
					index++;
				}
				hasLineNumber.setHasLineNumber(true);
			}
			else
			{
				no_line_number = no_line_number-1;
				lineNumbers.put(no_line_number, expressions[i]);
			}
			inputExpressions.add(hasLineNumber);
		}
		for(int i=0;i<inputExpressions.size();i++)
		{
			if(inputExpressions.get(i).isHasLineNumber() && operatorPrecedence.hasOperator(inputExpressions.get(i).getExpression()))
			{
				//Get expression associated with the line number
				String expression = lineNumbers.get(inputExpressions.get(i).getLineNumber());
				if(expression.contains("?"))
				{
					parseExpression.evaluateLoopExpressions(expression, base_value, inputExpressions.get(i).lineNumber, lineNumbers);
					continue;
				}
				//Convert output array to string 
				String result;
				//Output array to store the postfix notation of the infix notation.
				char[] output = new char[expression.length()];
				if(!operatorPrecedence.isPostfixExpression(expression))
				{
					output = ShuntingYard.getPostFixNotation(expression.substring(0,expression.length()-2), output);
					result = String.valueOf(output);
				}
				else
					result = expression;
				if(i == inputExpressions.size()-1)
				{	
					parseExpression.evaluateExpression(result, base_value, true,lineNumbers,1);
				}
				else
				{
					parseExpression.evaluateExpression(result, base_value, false,lineNumbers,1);
				}
			}
			else
			{
				//If it is the last statement being called then send last statement value as true, to print the final value
				if(i == inputExpressions.size()-1)
				{
					if(inputExpressions.get(i).hasLineNumber)
						parseExpression.evaluateExpression(inputExpressions.get(i).getExpression(), base_value, true,null,1);
					else
						parseExpression.evaluateExpression(inputExpressions.get(i).getExpression(), base_value, true,null,0);
				}
				else
				{
					if(inputExpressions.get(i).hasLineNumber)
						parseExpression.evaluateExpression(inputExpressions.get(i).getExpression(), base_value, false,null,1);
					else
						parseExpression.evaluateExpression(inputExpressions.get(i).getExpression(), base_value, false,null,0);
				}
			}
		}
	}
	
}

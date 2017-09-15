/**
 * 
 */
package cs6301.g33.longProject1;

import java.util.ArrayList;
import java.util.List;

import cs6301.g33.utils.ParsePostfixExpression;

/**
 * @author Sushma
 * @author Sai Vivek Kanaparthy
 *
 */
public class ReadInfixExpressions {

	/**
	 * @param args: Takes input from command Line: n=args[0]: no of input expressions( Integer Value)
	 * In consecutive args[0...n] send the expressions to be evaluated.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] expressions = {"a = 999;"," b = 8;","c = ab^ ;","d = ab+ ;",";"};
		int no_of_expressions = 5;
		int base_value=10;
		List<String> inputExpressions = new ArrayList<String>();
		for(int i=0;i<no_of_expressions;i++)
			inputExpressions.add(expressions[i]);
		ParsePostfixExpression parseExpression = new ParsePostfixExpression();
		
		for(int index = 0 ; index< no_of_expressions; index++)
		{
			//System.out.println("Reading expression:"+inputExpressions.get(index));
			if(index == no_of_expressions-1)
				parseExpression.evaluateExpression(inputExpressions.get(index), base_value,true);

			else
				parseExpression.evaluateExpression(inputExpressions.get(index), base_value,false);
		}

	}

}

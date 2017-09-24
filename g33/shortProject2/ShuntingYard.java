/**
 * 
 */
package cs6301.g33.shortProject2;

import cs6301.g33.utils.OperatorPrecedence;
import cs6301.g33.utils.Stack;

/**
 * @author Sushma
 * @author Sai Vivek Kanaparthy
 */
public class ShuntingYard {
	/**
	 * 
	 * @param infix_notation : Infix expression provided to the function
	 * @param output : Postfix expression form of the given infix expression
	 * @return
	 */
	//Generates the postfix expression of the given input expression
	public static char[] getPostFixNotation(String infix_notation, char[] output) {
		
		
		//Initialize the stack (This is a generic stack class in utils package
		Stack<Character> stack = new Stack<Character>(infix_notation.length(),
				new Character[infix_notation.length()]);
		//Helper function to evaluate whether a particular character is an operator or an operand
		OperatorPrecedence operatorPrec = new OperatorPrecedence();

		int output_index = 0; // index of the output array
		char elem ; // Element to store while we encounter close paranthesis
		char current_element; //Current element to be evaluated in the infix expression	
		
		
		//Iterate through the infix expression 
		for (int index = 0; index < infix_notation.length(); index++) {
			current_element = infix_notation.charAt(index);
			
			//If the current element is space skip and continue with next element.
			if(current_element==' ')
				continue;
			
			//Check if the current element is an operator or an operand
			if (operatorPrec.isOperator(current_element)) {
				
				//If it is an operator and the stack is not empty check for the ranks of current element and rank of the element in the stack.
				if (!stack.isEmpty() && operatorPrec.getRankOfOperator(current_element) >= operatorPrec.getRankOfOperator(stack
						.getTopElement())) {
					
					//Check if the current element is open paranthesis, if so push it into the stack since it has highest rank
					if (operatorPrec.isOpenParanthesis(current_element))
					{
						stack.push(current_element);
						continue;
					}
					
					//If it is closed paranthesis, pop all the elements until we encounter an open paranthesis
					else if (operatorPrec.isCloseParanthesis(current_element)) {
						//Pop the element from the stack if not empty
						if (!stack.isEmpty())
							elem = stack.pop();
						else
							throw new UnsupportedOperationException();
						//Pop the elements until we find a closed paranthesis
						while (elem != '[' && elem != '{' && elem != '(') {
							if (!stack.isEmpty())
							{
								output[output_index++] = elem;
								elem = stack.pop();
							}
							else
								break;
						}
					} else
					{
						
						//If the current element has same precedence pop and add the element from the stack to o/p array and add current element to stack.
						if(operatorPrec.getRankOfOperator(current_element) == operatorPrec.getRankOfOperator(stack.getTopElement()))
							output[output_index++] = stack.pop();
						stack.push(current_element);
					}
				} else {
					//If current element has less precedence the top element in stack or if the stack is empty we get to this block.
					//If stack and it is an open paranthesis add operator to stack.
					if (!stack.isEmpty()) {
						if(operatorPrec.isOpenParanthesis(stack.getTopElement()))
						{
							stack.push(current_element);
							continue;
						}
						//If stack is not empty and rank of current element is less than the rank of top element of the stack, pop the element from the stack and push it to output array
						while (!stack.isEmpty()
								&& operatorPrec
										.getRankOfOperator(current_element) <= operatorPrec
										.getRankOfOperator(stack
												.getTopElement()))
							output[output_index++] = stack.pop();
						stack.push(current_element);

					}
					else
						stack.push(current_element);
				}

			} else {
					//If operand push element to the output array
					output[output_index++] = current_element;
			}
		}
		//Check if the stack is empty, and then pop all the elements from the stack and push it to the output array
		while(!stack.isEmpty())
			output[output_index++]=stack.pop();
		return output;
	}
}

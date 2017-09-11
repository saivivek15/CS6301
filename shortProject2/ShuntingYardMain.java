/**
 * 
 */
package cs6301.g33.shortProject2;

/**
 * @author Sushma
 * @author Sai Vivek Kanaparthy
 */
public class ShuntingYardMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Provide the infix expression
		//Sample expressions: (A *B) + (C * d), (A + B+ C+ D), (a/b)+(c/d), (A!) + B, A^B+c
		String infix_notation = "A^B+x";
		
		//Output array to store the postfix expression
		char[] output = new char[infix_notation.length()];
		
		//Shunting yard algorithm to get the postfix notation for the expression
		output = ShuntingYard.getPostFixNotation(infix_notation, output);
		
		//Print the postifix expression
		System.out.print(output);
	}

}

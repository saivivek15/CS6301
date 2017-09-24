/**
 * 
 */
package cs6301.g33.longProject1;

import java.math.BigInteger;

/**
 * @author Sushma
 *
 */
public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigInteger number1 = new BigInteger("12344565789089063543525245464576578978763453455657687456345345");
		BigInteger number2 = new BigInteger("34246887686534325676546745636654656744875867763452343");
		boolean isnegative = false;
		
		if(number1.compareTo(BigInteger.ZERO) < 0)
			isnegative = true;
		
		
		if(number1.compareTo(number2) > 0)
		{
			
		}
		else if(number1.compareTo(number2) == 0)
			System.out.println(1);
		else
			System.out.println(0);
			
	}

}

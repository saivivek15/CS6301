// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g33.longProject1;

public class LP1L1 {
    public static void main(String[] args) {
	Num x = new Num(54321);
	Num y = new Num(4321);
	Num z = Num.add(x, y);
	System.out.println(x);
	System.out.println(y);
	System.out.println(z);
	
	//Num a = Num.power(x, 8);
	//System.out.println(z);
	z.printList();
    }
}

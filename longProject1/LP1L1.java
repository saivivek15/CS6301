// Sample code for Level 1 driver for lp1

// Change following line to your group number
package cs6301.g33.longProject1;

public class LP1L1 {
    public static void main(String[] args) {
	Num x = new Num(99999999);
	Num y = new Num(99);
	Num z = Num.add(x, y);
	Num w = Num.subtract(x, y);
	Num u = Num.product(x, y);
	Num v = Num.karatsuba(x, y);
	Num a = Num.power(x, 2);
	System.out.println("x: "+x);
	System.out.println("y: "+y);
	System.out.println("z: "+z);
	System.out.println("w: "+w);
	System.out.println("u: "+u);
	System.out.println("a: "+a);
	System.out.println("v: "+v);
	w.printList();
    }
}

// Starter code for lp1.

// Change following line to your group number
// Changed type of base to long: 1:15 PM, 2017-09-08.
package cs6301.g33.longProject1;

import java.util.Iterator;
import java.util.LinkedList;

public class Num  implements Comparable<Num> {

    static long defaultBase = 10;  // This can be changed to what you want it to be.
    static long base = defaultBase;  // Change as needed
    LinkedList<Long> numObj = new LinkedList<>(); 
    /* Start of Level 1 */
    Num(String s) {
    	int size = s.length();
    	for(int i=0;i<size;i++){
    		this.numObj.add((long) (s.charAt(size-i-1)-'0'));
    	}

    }

    Num(long x) {
    	long temp =x;
    	while(temp>0){
    		this.numObj.add(temp % base);
    		temp = temp/base;
    	}

    }

    static Num add(Num a, Num b) {
    	long carry=0;
    	Iterator<Long> it1 = a.numObj.iterator();
    	Iterator<Long> it2 = b.numObj.iterator();
    	LinkedList<Long> numObj = new LinkedList<>(); 
    	while(it1.hasNext() && it2.hasNext()){
    		long temp=0;
    		temp = it1.next()+it2.next()+carry;
    		numObj.add(temp % base);
    		carry = temp/base;
    		
    	}
    	while(it1.hasNext()){
    		long temp =0;
    		temp = it1.next()+carry;
    		numObj.add(temp % base);
    		carry = temp/base;
    	}
    	while(it2.hasNext()){
    		long temp =0;
    		temp = it2.next()+carry;
    		numObj.add(temp % base);
    		carry = temp/base;
    	}
    	if(carry ==1){
    		numObj.add(carry);
    	}
    	else{
    		numObj.add((long) 0);
    	}
    Num sum = new Num(0);
    sum.numObj=numObj;
	return sum;
    }

    static Num subtract(Num a, Num b) {
    	
	return null;
    }

    // Implement Karatsuba algorithm for excellence credit
    static Num product(Num a, Num b) {
	return null;
    }

    // Use divide and conquer
    static Num power(Num a, long n) {
	return null;
    }
    /* End of Level 1 */

    /* Start of Level 2 */
    static Num divide(Num a, Num b) {
	return null;
    }

    static Num mod(Num a, Num b) {
	return null;
    }

    // Use divide and conquer
    static Num power(Num a, Num n) {
    
	return null;
    }

    static Num squareRoot(Num a) {
	return null;
    }
    /* End of Level 2 */


    // Utility functions
    // compare "this" to "other": return +1 if this is greater, 0 if equal, -1 otherwise
    public int compareTo(Num other) {
    	long size = this.numObj.size();
    	long otherSize = other.numObj.size();
    	if(size < otherSize){
    		return -1;
    	}
    	else if( size > otherSize){
    		return 1;
    	}
    	else{
    		Iterator<Long> it1 = this.numObj.descendingIterator();
    		Iterator<Long> it2 = other.numObj.descendingIterator();
    		while(it1.hasNext() && it2.hasNext()){
    			long thisNext = it1.next();
    			long otherNext = it2.next();
    			if(thisNext >  otherNext){
    				return 1;
    			} else if(thisNext <  otherNext){
    				return -1;
    			}
    		}
    	}
	return 0;
    }
    
    // Output using the format "base: elements of list ..."
    // For example, if base=100, and the number stored corresponds to 10965,
    // then the output is "100: 65 9 1"
    void printList() {
    	Iterator<Long> it = this.numObj.iterator();
    	String lst = base + " : ";
    	while(it.hasNext()){
    		lst += it.next();
    		lst += " ";
    	}
    	System.out.println(lst);
    }
    
    // Return number to a string in base 10
    public String toString() {
    	String number = "";
    	Iterator<Long> it = this.numObj.descendingIterator();
    	if(this.numObj.size()==0){
    		return number;
    	}
    	long last = it.next();
    	if(last!=0){
    		number += last;
    	}
    	while(it.hasNext())
    		number += it.next();
	return number;
    }

    public long base() { return base; }
}

/**
 * 
 */
package cs6301.g33.longProject1;

/**
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 */

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
    	long carry=0;
    	Iterator<Long> it1 = a.numObj.iterator();
    	Iterator<Long> it2 = b.numObj.iterator();
    	LinkedList<Long> numObj = new LinkedList<>(); 
    	int cmp = a.compareTo(b);
    	if(cmp == 0){
    		return new Num(0);
    	}
    	else if(cmp > 0){
    		while(it1.hasNext() && it2.hasNext()){
        		long temp=0;
        		temp = it1.next()-it2.next()-carry;
        		carry =0;
        		if (temp <0){
        			temp = temp+base;
        			carry =1;
        		}
        		numObj.add(temp);
    		}
    		while(it1.hasNext()){
        		long temp =0;
        		temp = it1.next()-carry;
        		carry = 0;
        		if(temp<0){
        			temp = temp+base;
        			carry=1;
        		}
        		numObj.add(temp);
        	}
    		Num sub = new Num(0);
    	    sub.numObj=numObj;
    		return sub;
    	}
    	else{
    		while(it1.hasNext() && it2.hasNext()){
        		long temp=0;
        		temp = it2.next()-it1.next()-carry;
        		carry =0;
        		if (temp <0){
        			temp = temp+base;
        			carry =1;
        		}
        		numObj.add(temp);
    		}
    		while(it2.hasNext()){
        		long temp =0;
        		temp = it2.next()-carry;
        		carry = 0;
        		if(temp<0){
        			temp = temp+base;
        			carry=1;
        		}
        		numObj.add(temp);
        	}
    		Num sub = new Num(0);
    	    sub.numObj=numObj;
    		return sub;
    	}
    }
    
    
    static Num product(Num a, Num b){
    	long finalSize = a.numObj.size()+b.numObj.size()+1;
    	Num result = new Num(0);
    	for(int i=0;i<=finalSize;i++){
        	result.numObj.add((long)0);
    	}

    	for(int i=0;i<a.numObj.size();i++){
    		long carry=0;
    		LinkedList<Long> tmpProduct = new LinkedList<>();
        	for(int each=0;each<=finalSize;each++){
            	tmpProduct.add((long)0);
        	}
    		for(int j=0;j<b.numObj.size();j++){
    			long temp=a.numObj.get(i)*b.numObj.get(j)+carry;
    			carry = temp/base;
    			tmpProduct.set(j,temp % base);
    		}
    		long carryResult=0;
    		int j=0;
    		for(j=0;j<b.numObj.size()+1;j++){
    			long temp = result.numObj.get(i+j)+tmpProduct.get(j)+carryResult;
    			carryResult = temp/base;
    			result.numObj.set(i+j, temp % base);
    		}
    		if(carry !=0){
    			long temp = result.numObj.get(i+j-1);
    			result.numObj.set(i+j-1,temp+carry);
    		}
    	}

    	return result;
    	
    }
    // Implement Karatsuba algorithm for excellence credit
    static Num karatsuba(Num a, Num b) {
    	long baseSize;
    	int cmp = a.compareTo(b);
    	if(cmp >1){
    		baseSize = a.numObj.size();
    	}else{
    		baseSize = b.numObj.size();
    	}
    	if(baseSize<3){
    		return product(a, b);
    	}
    	baseSize = (baseSize/2);
    	baseSize += (baseSize % 2);
    	Num aLow = new Num(0);
    	Num aHigh = new Num(0);
    	Num bLow = new Num(0);
    	Num bHigh = new Num(0);
    	long count =0;
    	for(Long ita:a.numObj){
    		count +=1;
    		if(count>=baseSize){
    			aHigh.numObj.add(ita);
    		}
    		aLow.numObj.add(ita);
    	}
    	count=0;
    	for(Long itb:b.numObj){
    		count +=1;
    		if(count>=baseSize){
    			bHigh.numObj.add(itb);
    		}
    		bLow.numObj.add(itb);
    	}
    	Num aLowbLow = karatsuba(aLow,bLow);
    	Num aHighbHigh = karatsuba(aHigh, bHigh);
    	Num ab = karatsuba(add(aLow, bHigh), add(aHigh,bLow));
    	for(long i=0;i<2*baseSize;i++){
    		aHighbHigh.numObj.add((long) 0);
    	}
    	for(long i=0;i<baseSize;i++){
    		ab.numObj.add((long) 0);
    	}
    	Num result = Num.add(aLowbLow, ab);
    	result = Num.add(aHighbHigh, result);
	return result;
    }

    // Use divide and conquer
    static Num power(Num a, long n) {
    	if(n ==0){
    		return new Num(1); 
    	}else if(n==1){
    		return a;
    	}else{
    		Num S = power(a,n/2);
    		if(n%2==0){
    			return product(S,S);
    		}else{
    			S = product(S, S);
    			return product(S,a);
    		}
    	}
    }
    /* End of Level 1 */

    /* Start of Level 2 */
    static Num divide(Num a, Num b) {
	return null;
    }

    static Num mod(Num a, Num b) {
    	Num tmp = divide(a, b);
    	tmp = product(tmp, b);
    	tmp = subtract(a, tmp);
	return tmp;
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
    	while(it.hasNext()){
    		number += it.next();
    	}
    	number = number.replaceFirst("^0+","");
	return number;
    }

    public long base() { return base; }
}
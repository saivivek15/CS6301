package cs6301.g33.longProject1;

import java.util.Arrays;

/**
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
    	if(x == 0){
    		this.numObj.add((long) 0);
    	}
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
    sum.numObj=sum.NoZero();
    //System.out.println("sum: "+ sum.numObj);
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
    	if(a.compareTo(result)==0 || b.compareTo(result)==0){
    		return result;
    	}
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
//    // Implement Karatsuba algorithm for excellence credit
//    static Num karatsuba(Num a, Num b) {
//    	long k;
//    	int cmp = a.compareTo(b);
//    	if(cmp >0){
//    		k = a.sizeNoZero();
//    		
//    	}else{
//    		k = b.sizeNoZero();
//    	}
//    	System.out.println("BS: "+k+ " , "+a+ ":"+ a.sizeNoZero() + " , "+b+" : " + b.sizeNoZero());
//    	if(k< 2){
//    		return product(a, b);
//    	}
//    	k = (k/2);
//        //k += (k % 2);
//    	Num aLow = new Num(0);
//    	Num aHigh = new Num(0);
//    	Num bLow = new Num(0);
//    	Num bHigh = new Num(0);
//    	long count =0;
//    	for(Long ita:a.numObj){
//    		count +=1;
//    		if(count>=k){
//    			aLow.numObj.add(ita);
//    		}
//    		aHigh.numObj.add(ita);
//    	}
//    	count=0;
//    	for(Long itb:b.numObj){
//    		count +=1;
//    		if(count>=k){
//    			bLow.numObj.add(itb);
//    		}
//    		bHigh.numObj.add(itb);
//    	}
////    	Num aLowbLow = karatsuba(aLow,bLow);
////    	Num aHighbHigh = karatsuba(aHigh, bHigh);
////    	Num ab = karatsuba(add(aLow, aHigh), add(bHigh,bLow));
////    	Num temp = add(aHighbHigh, aLowbLow);
////    	ab = subtract(ab,temp);
////    	for(long i=0;i<2*k;i++){
////    		aHighbHigh.numObj.addFirst((long) 0);
////    	}
////    	for(long i=0;i<k;i++){
////    		ab.numObj.addFirst((long) 0);
////    	}
////    	Num result = Num.add(aLowbLow, ab);
////    	result = Num.add(aHighbHigh, result);
//	return null;
//    }

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
    static double divid(int a,int b){
    	int max = a;
    	if(b==0){
    		return max;
    	}
    	double low=0;
    	double high = max;
    	while(true){
    		double mid = low+ (high-low)/2;
    		System.out.println("L: "+low+" H: "+high);
    		System.out.println("mid: "+mid);
    		if(Math.abs(b*mid -a)<= 0.0 && Math.abs((b+1)*mid -a)>0.0){
    			System.out.println("## "+mid);
    			return mid;
    		}
    		if(b*mid<a){
    			low=mid;
    		}else{
    			high=mid;
    		}
    	}
    }
    static Num middle(Num c){
    	Iterator<Long> it = c.numObj.descendingIterator();
    	LinkedList<Long> midList = new LinkedList<>();
    	long tmp = 0;
    	while(it.hasNext()){
    		long nxt = tmp*base+ it.next();
    		midList.addFirst(nxt / 2);
    		tmp = nxt%2;
    	}
    	Num mid = new Num(0);
    	mid.numObj=midList;
  
    	return mid;
    }
    
    static Num divide(Num a, Num b) {
    	if(b == new Num(0)){
    		return null;
    	}
    	Num low = new Num(0);
    	Num high = new Num(24);
    	boolean bool =true;
    	int count=0;
    	while(bool){
    		count +=1;
    		if(count==10)
    			bool=false;
    		Num mid = middle(add(low, high));
    		System.out.println("H: "+high+" L: "+low);
    		System.out.println("mid: "+mid);
    		Num prev=product(mid,b);
    		prev.numObj=prev.NoZero();
    		Num nxt = product(mid,add(b,new Num(1)));
    		nxt.numObj=nxt.NoZero();
    		System.out.println("prev: "+prev+" "+prev.numObj+" :size: "+ prev.numObj.size());
    		System.out.println("next: "+nxt+" "+nxt.numObj+" :next: "+ nxt.numObj.size());
    		System.out.println((prev.compareTo(a)+ " : "+ nxt.compareTo(a)));
    		if(prev.compareTo(a)<1 && nxt.compareTo(a)>0){
    			System.out.println("## "+mid);
    			return mid;
    		}
    		if(prev.compareTo(a)<1){
    			low=mid;
    		}else{
    			high=mid;
    		}
    		}
  
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
    	//System.out.println("Size:"+ this.numObj+" "+size+" Other Size:"+other.numObj+" "+otherSize);
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
    public LinkedList<Long> NoZero(){
    	Iterator<Long> it = this.numObj.descendingIterator();
    	int i = this.numObj.size();
    	while (it.hasNext() && it.next() == 0) {
    	  i--;
    	}
    	LinkedList<Long> res = new LinkedList<>();
    	for(Long each:this.numObj.subList(0,i)){
    		res.add(each);
    	}
    	return res;
    
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
    	if(number=="0"){
    		return number;
    	}
    	number = number.replaceFirst("^0+", "");
    	return number;
    }

    public long base() { return base; }
}

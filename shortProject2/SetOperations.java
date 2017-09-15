package cs6301.g33.shortProject2;

/**
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SetOperations {
	/**
	 * 
	 * @param l1: linked list implementing sorted set
	 * @param l2: linked list implementing sorted set
	 * @param outList: intersection of sorted sets
	 */
    public static<T extends Comparable<? super T>> void intersect(List<T> l1, List<T> l2, List<T> outList){
    	if(l1.size()==0 || l2.size()==0){
    		System.out.println(outList);
    		return;
    	}
    	Iterator<T> it1 = l1.iterator();
    	Iterator<T> it2 = l2.iterator();
    	T x1 = it1.next();
    	T x2 = it2.next();
    	while(x1 != null && x2!=null){
    		if(x1.compareTo(x2)<0){
    			x1=next(it1);
    		}
    		else if(x1.compareTo(x2)>0){
    			x2=next(it2);
    		}
    		else{
    			outList.add(x1);
    			x1=next(it1);
    			x2=next(it2);
    		}
    	}
    	System.out.println(outList);
    }
    
    /**
     * 
     * @param it iterator
     * @return returns the next element of the iterator if exists else returns null
     */
    public static<T extends Comparable<? super T>> T next(Iterator<T> it){
    	return it.hasNext() ? it.next():null;
    }
    
	/**
	 * 
	 * @param l1: linked list implementing sorted set
	 * @param l2: linked list implementing sorted set
	 * @param outList: union of sorted sets
	 */
    public static<T extends Comparable<? super T>> void union(List<T> l1, List<T> l2, List<T> outList){
    	if(l1.size()==0 && l2.size()==0){
    		System.out.println(outList);
    		return;
    	}
    	else if(l1.size()==0){
    		outList=l2;
    		System.out.println(outList);
    		return;
    	}else if(l2.size()==0){
    		outList=l1;
    		System.out.println(outList);
    		return;
    	}
    	Iterator<T> it1 = l1.iterator();
    	Iterator<T> it2 = l2.iterator();
    	T x1 = it1.next();
    	T x2 = it2.next();
    	while(x1 != null && x2!=null){
    		if(x1.compareTo(x2)<0){
    			outList.add(x1);
    			x1=next(it1);
    		}
    		else if(x1.compareTo(x2)>0){
    			outList.add(x2);
    			x2=next(it2);
    		}
    		else{
    			outList.add(x1);
    			x1=next(it1);
    			x2=next(it2);
    		}
    	}
    	while(x1 != null){
    		outList.add(x1);
    		x1=next(it1);
    	}
    	while(x2 != null){
    		outList.add(x2);
    		x2=next(it2);
    	}
    	System.out.println(outList);
    }
    
	/**
	 * 
	 * @param l1: linked list implementing sorted set
	 * @param l2: linked list implementing sorted set
	 * @param outList: difference of sorted sets, l1 - l2
	 */
    public static<T extends Comparable<? super T>> void difference(List<T> l1, List<T> l2, List<T> outList) {
    	if(l1.size()==0){
    		System.out.println(outList);
    		return;
    	}
    	else if(l2.size()==0){
    		outList=l1;
    		System.out.println(outList);
    		return;
    	}
    	Iterator<T> it1 = l1.iterator();
    	Iterator<T> it2 = l2.iterator();
    	T x1 = it1.next();
    	T x2 = it2.next();
    	while(x1 != null && x2!=null){
    		if(x1.compareTo(x2)<0){
    			outList.add(x1);
    			x1=next(it1);
    		}
    		else if(x1.compareTo(x2)>0){
    			x2=next(it2);
    		}
    		else{
    			x1=next(it1);
    			x2=next(it2);
    		}
    	}
    	while(x1 != null){
    		outList.add(x1);
    		x1=next(it1);
    	}
    	System.out.println(outList);
    }
    public static<T extends Comparable<? super T>> boolean checkEmpty(List<T> l1, List<T> l2, List<T> outList){
    	if(l1.size()==0 && l2.size()==0){
    		return true;
    	}
    	else if(l1.size()==0){
    		outList=l2;
    		System.out.println(outList);
    		return true;
    	}else if(l2.size()==0){
    		outList=l1;
    		System.out.println(outList);
    		return true;
    	}
    	return false;
    }
    public static void main(String[] args){
    	List<Integer> l1 = new LinkedList<>();
    	List<Integer> l2 = new LinkedList<>();
    	List<Integer> outList = new LinkedList<>();
    	l2.add(3);
    	l2.add(4);
    	l2.add(5);
    	l1.add(1);
    	l1.add(2);
    	l1.add(3);
    	l1.add(4);
    	l1.add(5);
    	l1.add(6);
    	intersect(l1, l2, outList);
   	    outList = new LinkedList<>();
    	union(l1, l2, outList);
    	outList = new LinkedList<>();
    	difference(l1, l2, outList);
    }
}

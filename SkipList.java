/** 
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 **/
package cs6301.g33;

import java.util.Iterator;
import java.util.Random;


// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> {
	
	public class SkipListEntry<T> {
			
			T element;
			SkipListEntry<T>[] next;
			
			public SkipListEntry(T element, int level){
				this.element = element;
				this.next = new SkipListEntry[level];
				
			}
	
	}
	SkipListEntry<T> head, tail;
	final int maxLevel = 4;
	int size;
    
	// Constructor
    public SkipList() {
    	head = new SkipListEntry<T>(null,maxLevel);
    	tail = new SkipListEntry<T>(null,maxLevel);
    	size = 0;
		for(int i=maxLevel-1;i>=0;--i){
			head.next[i]=tail;
			tail.next[i]=null;
		}
    }
    

	public int chooseLevel(int level) {
		int j = 0;
		while (j < level - 1){
			boolean b = new Random().nextBoolean();
			if (b){
				j++;
			}
			else{
				break;
			}
		}
		return j;
	}

    
    public SkipListEntry<T>[] find(T x){
    	SkipListEntry<T> p = head;
    	SkipListEntry<T>[] prev = new SkipListEntry[maxLevel];

    	for(int i=maxLevel-1;i>=0;i--){
    		while(p.next[i].element!=null && p.next[i].element.compareTo(x) < 0){
    			p=p.next[i];
    		}
    		prev[i] =p;
    	}
    	return prev;
    }
    


    
    // Add x to list. If x already exists, replace it. Returns true if new node is added to list
    public boolean add(T x) {
    	SkipListEntry<T>[] prev = find(x);
    	if(prev[0].next[0].element != null && prev[0].next[0].element.compareTo(x) == 0){
    		prev[0].next[0].element=x;
    		return false;
    		
    	}else{
    		int lev = chooseLevel(maxLevel);
    		SkipListEntry<T> n = new SkipListEntry<T>(x,lev+1);
    		
    		for(int i=0;i<=lev;i++){
    			n.next[i] = prev[i].next[i];
    			prev[i].next[i]=n;
    		}
    		size++;
    		
    		return true;
    	}
    }
    

    // Find smallest element that is greater or equal to x
    public T ceiling(T x) {
	return null;
    }


    // Does list contain x?
	boolean contains(T x) {  
		SkipListEntry<T> [] prev = find(x);
		if (prev[0].next[0].element != null && prev[0].next[0].element.compareTo(x) == 0){
			return true;
		}
		else{
			return false;
		}
	}


    // Return first element of list
    public T first() {
	return null;
    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
	return null;
    }

    // Return element at index n of list.  First element is at index 0.
    public T get(int n) {
	return null;
    }

    // Is the list empty?
    public boolean isEmpty() {
	return false;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
	return null;
    }

    // Return last element of list
    public T last() {
	return null;
    }

    // Reorganize the elements of the list into a perfect skip list
    public void rebuild() {
	
    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) {
	return null;
    }

    // Return the number of elements in the list
    public int size() {
	return 0;
    }
    
    public void printList(){
    	SkipListEntry<T> skp = head.next[0];
    	while(skp.next[0]!=null){
    		System.out.println(skp.element);
    		skp = skp.next[0];
    	}
    }
    public static void main(String[] args){
    	SkipList<Integer> skp = new SkipList<>();
    	skp.add(5);
    	skp.add(6);
    	skp.add(7);
    	skp.printList();
    }

}

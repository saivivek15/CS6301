/** 
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 **/
package cs6301.g33;

import java.util.Iterator;
import java.util.Random;

// Skeleton for skip list implementation.

public class SkipList<T extends Comparable<? super T>> implements Iterable<T> {
	
	public class SkipListEntry<T> {
			
			public T element;
			public SkipListEntry<T>[] next;
			public SkipListEntry(T element, int level){
				this.element = element;
				this.next = new SkipListEntry[level];
			}
	
	}
	public class SkipListIterator<T> implements Iterator<T> {
		SkipListEntry<T> entry;
		public SkipListIterator(SkipListEntry<T> head){
			entry = head;
		}
		@Override
		public boolean hasNext() {
			if(entry.next[0].element!=null){
				return true;
			}
			return false;
		}

		@Override
		public T next() {
			entry =  entry.next[0];
			return entry.element;
		}

	}



	SkipListEntry<T> head, tail;
	int maxLevel = 5;
	int size;
    
	// Constructor
    public SkipList() {
    	head = new SkipListEntry<T>(null,maxLevel);
    	tail = new SkipListEntry<T>(null,maxLevel);
    	size = 0;
		for(int i=maxLevel-1;i>=0;i--){
			head.next[i]=tail;
			tail.next[i]=null;
		}
    }


	public int chooseLevel(int level) {
		int j = 0;
		while (j < level - 1){
			boolean b = new Random().nextBoolean();
			if (b)
				j++;
			else
				break;
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
    	SkipListEntry<T> [] prev = find(x);
		return prev[0].next[0].element;

    }


    // Does list contain x?
	boolean contains(T x) {  
		SkipListEntry<T> [] prev = find(x);
		if (prev[0].next[0].element != null && prev[0].next[0].element.compareTo(x) == 0){
			return true;
		}
		return false;
	}


    // Return first element of list
    public T first() {
    	return head.next[0].element;
    }

    // Find largest element that is less than or equal to x
    public T floor(T x) {
		SkipListEntry<T> [] prev = find(x);
		if (prev[0].next[0].element != null && prev[0].next[0].element.compareTo(x) == 0){
			return prev[0].next[0].element;
		}else{
			return prev[0].element;
		}
    }

    // Return element at index n of list.  First element is at index 0.
    public T get(int n) {
    	if(n>size){
    		throw new IndexOutOfBoundsException();
    	}
    	int indx =0;
    	SkipListEntry<T> skp = head.next[0];
    	while(indx<=size){
    	if(indx==n){
    		return skp.element;
    	}else{
    		skp=skp.next[0];
    	}
    	indx++;
    	}
    	return null;
    }

    // Is the list empty?
    public boolean isEmpty() {
    	if(size==0){
    		return true;
    	}
    	return false;
    }

    // Iterate through the elements of list in sorted order
    public Iterator<T> iterator() {
    	return new SkipListIterator<T>(head);
    }

    // Return last element of list
    public T last() {
    	SkipListEntry<T> skp = head.next[0];
    	T lst = skp.element;
    	while(skp.next[0]!=null){
    		lst = skp.element;
    		skp = skp.next[0];
    	}
    	return lst;
    }

    // Reorganize the elements of the list into a perfect skip list
    public void rebuild() {
    	int n = size();
	
    }

    // Remove x from list.  Removed element is returned. Return null if x not in list
    public T remove(T x) {
    	SkipListEntry<T> [] prev = find(x);
    	SkipListEntry<T> n = prev[0].next[0];
    	if(n.element.compareTo(x)!=0)
    		return null;
    	else{
			for(int i=0;i<maxLevel-1;i++){
				if(prev[i].next[i]==n)
					prev[i].next[i] = n.next[i];
			}
			size--;
			return n.element;
		}
    }

    // Return the number of elements in the list
    public int size() {
	return size;
    }
    
    public void printList(){
    	Iterator<T> it =iterator();
    	while(it.hasNext()){
    		System.out.print(it.next()+" ");
    	}
    	System.out.println();
    }
    
    public static void main(String[] args){
    	SkipList<Integer> skp = new SkipList<>();
    	skp.add(5);
    	skp.add(7);
    	skp.add(8);
    	skp.printList();
    	System.out.println("Ceiling: "+skp.ceiling(6));
    	System.out.println("Floor: "+skp.floor(6));
    	System.out.println("First: "+skp.first());
    	System.out.println("Last: "+skp.last());
    	System.out.println("isEmpty: "+skp.isEmpty());
    	System.out.println("Size: "+skp.size());
    	System.out.println("Remove: "+skp.remove(7));
    	skp.printList();
    	skp.add(7);
    	skp.add(9);
    	System.out.println("Get: "+skp.get(3));
    	skp.printList();
    }

}

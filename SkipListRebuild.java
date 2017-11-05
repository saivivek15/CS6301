/** 
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 **/
package cs6301.practice.code;

import java.util.Iterator;
import java.util.Random;

// Skeleton for skip list implementation.

public class SkipListRebuild<T extends Comparable<? super T>> implements Iterable<T> {
	
	public class SkipListEntry<T> {
			
			public T element;
			public SkipListEntry<T>[] next;
			public int[] diff;
			
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
			System.out.println("element: "+entry.element);
			for(int i=0;i<entry.next.length;i++){
			System.out.println(" "+entry.next[i].element+" ");
			}
			return entry.element;
		}
	}

	SkipListEntry<T> head, tail, newHead;
	int maxLevel = 4;
	int size;
	
    
	// Constructor
    public SkipListRebuild() {
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
    

    public void rebuildSkipList(int level)
    {
    	if(level > (Math.log(size)/Math.log(2)))
    		return;
    	else
    	{
    		int k=((int) Math.pow(2,level));
    		for(T x: this)
    		{
    			if(k > 1)
    			{
	    			while(k>0)
	    			{
	    				k--;
	    				continue;
	    			}
	    			k = ((int) Math.pow(2,level)) - 1;
    			}
    			add(x,level);
    		}
    	}
    	rebuildSkipList(level+1);
    }
    
    // Add x to list. If x already exists, replace it. Returns true if new node is added to list
    public boolean add(T x,int l) {
    	SkipListEntry<T>[] prev = find(x);
    	if(prev[0].next[0].element != null && prev[0].next[0].element.compareTo(x) == 0){
    		prev[0].next[0].element=x;
    		return false;
    		
    	}else{
    		int lev;
    		if(l == -1)
    			lev = chooseLevel(maxLevel);
    		else
    			lev = l;
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
		else{
			return false;
		}
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
    	}else{
    		return false;
    	}
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
    public void rebuild(int n) {
    	SkipListEntry<T>[] elements = new SkipListEntry[size];
    	rebuild(elements,0,size-1,n/2);
    }
    //Reorganize the elements of a list to perfect skip list.
    public void rebuild(SkipListEntry<T>[] elements, int start,int end, int offset)
    {
    	if(start<=end)
    	{
    		if(offset == 0 )
    		{
    			for(int i=start;start<end;i++)
    				elements[i] = new SkipListEntry<T>(null, 0);
    		}
    		else
    		{
    			int mid = (start+end)/2;
    			elements[mid] = new SkipListEntry<T>(null, offset);
    			rebuild(elements,start,mid-1,offset-1);
    			rebuild(elements,mid+1,end,offset-1);
    		}
    	}
    	int i=0;
    	this.maxLevel = maxLevel+1;
    	SkipListEntry<T> list = new SkipListEntry(null, maxLevel+1);
    	for(T ele : this)
    	{
    		elements[i].element = ele;
    		SkipListEntry<T>[] entry = find(ele);
    		for(int temp =0;temp<maxLevel;temp= temp+((int) Math.pow(2,temp)))
    		{
    			entry[temp].next[temp] = elements[i];
    		}
    		i++;
    	}
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
    		//System.out.print(it.next()+" ");
    		it.next();
    	}
    	System.out.println();
    }
    public static void main(String[] args){
    	SkipListRebuild<Integer> skp = new SkipListRebuild<>();
    	skp.add(5,-1);
    	skp.add(7,-1);
    	skp.add(8,-1);
    	skp.printList();
    	System.out.println("Ceiling: "+skp.ceiling(6));
    	System.out.println("Floor: "+skp.floor(6));
    	System.out.println("First: "+skp.first());
    	System.out.println("Last: "+skp.last());
    	System.out.println("isEmpty: "+skp.isEmpty());
    	System.out.println("Size: "+skp.size());
    	System.out.println("Remove: "+skp.remove(7));
    	skp.add(7,-1);
    	skp.add(9,-1);
    	System.out.println("Get: "+skp.get(3));
    	skp.printList();
    	System.out.println("Rebuild:");
    	skp.rebuildSkipList(0);
    	skp.printList();
    }
}
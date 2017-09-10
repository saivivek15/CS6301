package cs6301.g33.shortProject2;

/**
 * @author Sai Vivek Kanaparthy
 * @author Sushma Eati
 */
import java.lang.reflect.Array;
import java.util.Arrays;

public class QueueImplArray<T>{
	T[] arr; // array to store the queue
	static int head =-1; // head pointing the queue
	static int tail =-1; // tail pointing the queue
	int size; // size of the queue
	
	QueueImplArray(Class<T> t,int s){
		this.size =0;
		if(s>=16){
			this.arr = (T[] ) Array.newInstance(t,s);
		}else{
			throw new IllegalArgumentException("Minimum size of Queue is 16!");
		}
	}
	/**
	 * Procedure to add element 'entry' to the queue
	 * @param entry: element to be added to queue
	 * @return: false if the array is full, true if it gets added to the array
	 */
	boolean offer(T entry){
		if(size ==arr.length){
			return false;
		}
		
		if(head+1 == arr.length){
			head=0;
		}else{
			head = head + 1;
		}
		arr[head] = entry;
		size++;
		
		if(tail==-1){
			tail=head;
		}
		return true;
	}
	/**
	 * Procedure to return the first element in the queue and removes it
	 * @return element first in the queue
	 */
	T poll(){
		if(isEmpty()){
			return null;
		}else{
			T entry = arr[tail];
			arr[tail]=null;
			size--;
			if(tail+1 == arr.length){
				tail=0;
			}else{
				tail = tail + 1;
			}
			return entry;
		}
	}
	/**
	 * Procedure to return the first element in the queue but does not remove it
	 * @return
	 */
	T peek(){
		if(isEmpty()){
			return null;
		}else{
			return arr[tail];
		}
	}
	
	/**
	 * Procedure to check whether the queue is empty
	 * @return: true if queue is empty else false
	 */
	boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	/**
	 * Procedure to resize the queue if the size of the queue is more than 90% then doubles the size 
	 * if the size of the queue is less than 25% then halves the size
	 */
	void reSize(){
		int len=arr.length;
		if(size*100/len>90){
			T[] temp = (T[] ) new Object[2*len];
			for(int i=0;i<size;i++){
				temp[i] = arr[(i+tail) % len];
			}
			arr = temp;
			head =size-1;
			tail=0;
		}
		else if(size*100/len<25) {
			T[] temp = (T[] ) new Object[len/2+1];
			for(int i=0;i<size;i++){
				temp[i] = arr[(i+tail) % len];
			}
			arr = temp;
			head =size-1;
			tail=0;
		}
	}
	
	
	/**
	 * Print the elements of the queue
	 */
	public String toString() {
		return Arrays.toString(this.arr);
	}
	/**
	 * Driver Class to create a queue and perform operations on it 
	 * 
	 */
	public static void main(String[] args){
		QueueImplArray<Integer> sample = new QueueImplArray<Integer>(Integer.class,16);
		sample.offer(9);
		sample.offer(8);
		sample.offer(7);
		sample.offer(6);
		sample.offer(4);
		sample.offer(3);
		sample.offer(91);
		sample.offer(81);
		sample.offer(71);
		sample.offer(61);
		sample.offer(41);
		sample.offer(31);
		sample.offer(92);
		sample.offer(82);
		sample.offer(83);
		System.out.println(sample.peek());
		sample.poll();
		System.out.println(sample);
		sample.offer(5);
		sample.reSize();
		sample.offer(5);
		System.out.println(sample);
	}
}
	
	

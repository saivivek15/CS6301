/**
 * 
 */
package cs6301.practice.code;

/**
 * @author Sushma
 *
 */
public class BinarySearchGenerics implements Comparable<BinarySearchGenerics> {
	/**
	 * @param args
	 */
	int  element;
	
	public BinarySearchGenerics(int element) {
		super();
		this.element = element;
	}
	
	public static void main(String[] args) {
		final int n = 10;
		BinarySearchGenerics[] input = new BinarySearchGenerics[n];
		
		for(int i=0;i<n;i++){
			input[i] = new BinarySearchGenerics(i);
		}
		BinarySearchGenerics item = new BinarySearchGenerics(15);
		System.out.print(BinarySearch.binarySearch(input, 0, n-1, item));
	}

	@Override
	public int compareTo(BinarySearchGenerics object) {
		if(this.element < object.element)
			return -1;
		else if(this.element > object.element)
			return 1;
		return 0;
	}

}

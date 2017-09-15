/**
 * 
 */
package cs6301.practice.code;

/**
 * @author Sushma
 *
 */
public class BinarySearch {	
	//input should be sorted for binary search to work
	public static<T extends Comparable<? super T>> boolean binarySearch(T[] input,  int start_index, int end_index, T element)
	{
		while(start_index<=end_index)
		{
			int middle_index = (start_index+end_index)>>>1;
			int result = input[middle_index].compareTo(element);
			//Check if the result of comparison is positive, negative or 0 and set start and end indices accordingly
			if(result == 0)
				return true;
			else if(result < 0)
			{
				start_index = middle_index+1;
			}
			else
			{
				end_index = middle_index-1;
			}
		}
		return false;
	}
}

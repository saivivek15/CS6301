/**
 * 
 */
package cs6301.g33.utils;

/**
 * @author Sushma
 *
 */
public class BinarySearch {	
	//input should be sorted for binary search to work
/**
 * 
 * @param input: Input Array
 * @param start_index
 * @param end_index
 * @param element: Element that has to searched
 * @return
 */
	public static<T extends Comparable<? super T>> int binarySearch(T[] input,  int start_index, int end_index, T element)
	{
		int largest_index=0;
		int result =0;
		while(start_index<=end_index)
		{
			int middle_index = (start_index+end_index)>>>1;
			result = input[middle_index].compareTo(element);
			//Check if the result of comparison is positive, negative or 0 and set start and end indices accordingly
			if(result == 0)
			{
				return middle_index;
			}
			else if(result < 0)
			{
				start_index = middle_index+1;
				largest_index = middle_index+1;
			}
			else
			{
				end_index = middle_index-1;
				largest_index =middle_index-1;
			}
		}
		if(result < 0)
			return largest_index-1;
		else
			return largest_index;
	}
}

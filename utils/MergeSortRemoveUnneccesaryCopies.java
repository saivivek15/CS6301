/**
 * 
 */
package cs6301.g33.utils;

/**
 * @author Sushma, Sai Vivek Kanaparthy
 *
 */
public class MergeSortRemoveUnneccesaryCopies {
	//State to maintain to check which array to be merged to which array.
	static int state =1 ;
	static int THRESHOLD =20;
	/**
	 * 
	 * @param input: Input Array
	 * @param temp_array: Temporary Array
	 * @param start_index: Start index for the array
	 * @param end_index: End index of the array.
	 */
	public static void mergeSort(int[] input,int[] temp_array, int start_index, int end_index)
	{	
			//If size is less than the THRESHOLD value then call insertion sort.
			if(end_index<THRESHOLD)
			{
				InsertionSort.insertionSort(input, end_index);
				return;
			}
			//If start index and end index are equal just return  from recursive call
			 if(start_index == end_index)
				 return;
			 //Calculate mid index and apply merge on 
			 int middle_index = (start_index+end_index)>>>1;	 
			 mergeSort(input,temp_array,start_index, middle_index);
			 mergeSort(input,temp_array,middle_index+1, end_index);
			 //If state is odd,merge from temp array to input array
			 if(state == 1)
			 {
				 state=0;
				 merge(temp_array,input,start_index, end_index, middle_index);
			 }
			 //If state is even merge from input to temp_array
			 else
			 {
				 state=1;
				 merge(input,temp_array,start_index, end_index, middle_index);
			 }	 
	}
	/**
	 * 
	 * @param input: Input Array
	 * @param temp_array: Temp Array which has the same values as Input aaray
	 * @param start_index: Start index of the input array
	 * @param end_index: End index of the input array
	 * @param middle_index: Middle Index
	 */
	public static void merge(int[] input,int[] temp_array, int start_index,int end_index, int middle_index)
	{
		int temp_start = start_index;
		int temp_index = 0;
		int temp_middle_index = middle_index+1;

		//Check 1st element of left array and 1st element of right array, assign smallest elem to the temp array and increase index accordingly
		while(temp_start <= middle_index && temp_middle_index <= end_index)
		{
			if(input[temp_start] <input[temp_middle_index])
			{
				temp_array[temp_index] = input[temp_start];
				temp_start++;
			}
			
			else
			{
				temp_array[temp_index] = input[temp_middle_index];
				temp_middle_index++;
			}
			temp_index++;
		}
		
		//Add remaining elements of the left array to the temp array if any
		while(temp_start<= middle_index)
		{
			temp_array[temp_index] = input[temp_start];
			temp_index++;
			temp_start++;
		}
		//Add remaining elements of the right array to the temp array if any
		while(temp_middle_index<=end_index)
		{
			temp_array[temp_index] = input[temp_middle_index];
			temp_index++;
			temp_middle_index++;
		}	
	}

}

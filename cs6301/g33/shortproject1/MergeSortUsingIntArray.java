/**
 * 
 */
package cs6301.g33.shortproject1;

/**
 * @author Sushma
 *
 */
public class MergeSortUsingIntArray {
	
	public static void mergeSort(int[] input,int[] temp_array, int start_index, int end_index)
	{
		//If start index and end index are equal just return  from recursive call
			 if(start_index == end_index)
				 return;
			 //Calculate mid index and apply merge on 
			 int middle_index = (start_index+end_index)>>>1;
			 mergeSort(input,temp_array,start_index, middle_index);
			 mergeSort(input,temp_array,middle_index+1, end_index);
			 mergeArrays(input,temp_array,start_index, end_index, middle_index);
		 
	}
	
	public static void mergeArrays(int[] input,int[] temp_array, int start_index,int end_index, int middle_index)
	{
		int size_of_array = (end_index - start_index)+1;
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
		
		//Add 
		for(int index =0 ; index <  size_of_array ; index++)
		{
			input[index+start_index] = temp_array[index];
		}
	}


}

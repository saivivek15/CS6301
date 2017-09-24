package cs6301.g33.utils;

public class InsertionSort {
	
	public static void nSquareSort(int[] input_array,int length){
		for(int start_index = 1; start_index <= length - 1; start_index++){
			int item  = input_array[start_index];
			int temp_index = start_index - 1;
			while(temp_index >= 0 && item< input_array[temp_index]){
				input_array[temp_index+1] = input_array[temp_index];
				temp_index -= 1;
			}
			input_array[temp_index + 1] = item;
		}
	}
}

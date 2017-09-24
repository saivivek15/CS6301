package cs6301.g33.shortproject1;

public class GenericNSquareSort {
	
	public static<T extends Comparable<? super T>> void nSquareSort(T[] input_array,int length){
		for(int start_index = 1; start_index <= length - 1; start_index++){
			T item  = input_array[start_index];
			int temp_index = start_index - 1;
			while(temp_index >= 0 && item.compareTo(input_array[temp_index]) < 0){
				input_array[temp_index+1] = input_array[temp_index];
				temp_index -= 1;
			}
			input_array[temp_index + 1] = item;
		}
	}
}

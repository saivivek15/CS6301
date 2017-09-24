///**
// * 
// */
//package cs6301.g33.utils;
//
///**
// * @author Sushma
// *
// */
//import java.util.Scanner;
//import java.io.FileNotFoundException;
//import java.io.File;
//
//public class InputOutput<T> {
//	// Use file name from command line arg (if given). Otherwise read from stdin
//	// (console)
//	// If reading from Console up to end of input, type
//	// Ctrl-D (Unix, apple) or Ctril-Z (Windows) to signal EOF.
//
//	public static int[] readInput(String filePath) throws FileNotFoundException {
//		Scanner in;
//		int n = 20000000;
//		int[] input;
//		if (filePath.length() == 0) {
//			File inputFile = new File(filePath);
//			in = new Scanner(inputFile);
//		} else {
//			in = new Scanner(System.in);
//		}
//		while(in.hasNext())
//		{
//			
//		}
//	}
//}
//John McQuown
//CS 0445 Assignment 4

import java.util.*;
import java.io.*;
import java.lang.*;

public class Assig4 {
	int arraySize;
	//Initialize arrays
	Integer [] randomArray;
	Integer[] reverseArray;	
	Integer [] sortedArray;
	
	String fileName;
	
	public static void main(String[] args) {
		new Assig4();
	}
	public Assig4() {
		int trials, fixedTrials;
		Scanner inScan = new Scanner(System.in);
		
		
		
		System.out.println("Please enter the size of the array: ");
		arraySize = inScan.nextInt();
		
		System.out.println("Please enter the number of trials: ");
		trials = inScan.nextInt();
		fixedTrials = trials * 15;	//Necessary to multiply the number of trials by 15
									//Each algorithm runs 3 times, with 5 algorithms
		
		System.out.println("Please enter the name of the file: ");
		fileName = inScan.next();
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			
		}
		
		setupArray(arraySize);
		
		simpleQuick(randomArray, "Random", arraySize, trials);
		medianOfThree(randomArray, "Random", arraySize, 5, trials);
		medianOfThree(randomArray, "Random", arraySize, 10, trials);
		medianOfThree(randomArray, "Random", arraySize, 20, trials);
		randomPivot(randomArray, "Random", arraySize, trials);
		
		simpleQuick(sortedArray, "Sorted", arraySize, trials);
		medianOfThree(sortedArray, "Sorted", arraySize, 5, trials);
		medianOfThree(sortedArray, "Sorted", arraySize, 10, trials);
		medianOfThree(sortedArray, "Sorted", arraySize, 20, trials);
		randomPivot(sortedArray, "Sorted", arraySize, trials);
		
		simpleQuick(reverseArray, "Reverse", arraySize, trials);
		medianOfThree(reverseArray, "Reverse", arraySize, 5, trials);
		medianOfThree(reverseArray, "Reverse", arraySize, 10, trials);
		medianOfThree(reverseArray, "Reverse", arraySize, 20, trials);
		randomPivot(reverseArray, "Reverse", arraySize, trials);
	}
	
	//Method that sets up the each array and stores them into a global array variable
	public void setupArray(int sz) {
		randomArray = new Integer [sz];
		reverseArray = new Integer [sz];	
		sortedArray = new Integer [sz];
		
		//Generates an array with random Integers
		Integer [] tempArray = new Integer [sz];
		Random rand = new Random();
		for (int i = 0; i < sz; i++) {
			Integer randomInteger = rand.nextInt((sz - 0) + 1) + 0;
			tempArray[i] = randomInteger;
		}
		
		//I found online that using arraycopy() is better than clone() for large arrays
		System.arraycopy(tempArray, 0, randomArray, 0, tempArray.length);
		Arrays.fill(tempArray, null);	//Clear the temp array
		
		//Generates an array that is already sorted from 1 to arraySize
		for (int i = 1; i <= sz; i++) 
			tempArray[i-1] = i;
		System.arraycopy(tempArray, 0, sortedArray, 0, sz);
		Arrays.fill(tempArray, null);
		
		//Generates an array that is sorted from arraySize to 1
		for (int i = sz; i > 0; i--)
			tempArray[sz-i] = i;
		System.arraycopy(tempArray, 0, reverseArray, 0, sz);
		Arrays.fill(tempArray, null);
		
	}

	//Method that will execute each type of QuickSort for the array that is passed in
	public void simpleQuick(Integer [] theArray, String type, int size, int trials) {
		if (size >= 200000)
			return;
		
		long sum = 0;
		Integer [] tempArray = new Integer[size];
		for (int i = 0; i < trials; i++) {
			Arrays.fill(tempArray, null);
			//Create a copy of the array that is passed in
			//This is so that the original array that is passed in is not changed
			System.arraycopy(theArray, 0, tempArray, 0, theArray.length);
			
			long start = System.nanoTime();		//Start time
			
			//Execute the simple QuickSort
			Quick.quickSort(tempArray, size);
			
			long finish = System.nanoTime();	//End time
			
			long delta = finish - start;		//Subtract the difference
			sum = sum + delta;
			
			if (size <= 20) {
				System.out.println("Algorithm: Simple QuickSort");
				System.out.println("Array Size: " + size);
				System.out.println("Order: " + type);
				System.out.println("Before: " + Arrays.toString(theArray));
				System.out.println("After: " + Arrays.toString(tempArray));
				System.out.println("Time: " + delta);
				System.out.println();
			}
			
		}
		
		double average = sum / trials;
		average = average / 1000000000;		//Divide by 1 billion in order to get the time in seconds
		
		//Write the appropriate data to the text file
		try {
			PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			fileWriter.println("Algorithm: Simple QuickSort");
			fileWriter.println("Array Size: " + size);
			fileWriter.println("Order: " + type);
			fileWriter.println("Number of Trials: " + trials);
			fileWriter.println("Average Time: " + average);
			fileWriter.println();
			
			fileWriter.close();
		}
		catch(IOException e) {
			
		}
	}
	
	public void medianOfThree(Integer [] theArray, String type, int size, int min, int trials) {
		
		double sum = 0;
		Integer [] tempArray = new Integer[size];
		for (int i = 0; i < trials; i++) {
			Arrays.fill(tempArray, null);
			System.arraycopy(theArray, 0, tempArray, 0, randomArray.length);
			
			long start = System.nanoTime();
			
			//Execute the Median of Three QuickSort
			TextMergeQuick.quickSort(tempArray, tempArray.length, min);
			
			long finish = System.nanoTime();	//End time
			
			long delta = finish - start;		//Subtract the difference
			sum = sum + delta;
			
			if (size <= 20) {
				System.out.println("Algorithm: Median Of Three (" + min + ")");
				System.out.println("Array Size: " + size);
				System.out.println("Order: " + type);
				System.out.println("Before: " + Arrays.toString(theArray));
				System.out.println("After: " + Arrays.toString(tempArray));
				System.out.println("Time: " + delta);
				System.out.println();
			}
		}
		
		double average = sum / trials;
		average = average / 1000000000;		//Divide by 1 billion in order to get the time in seconds
		
		try {
			PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			fileWriter.println("Algorithm: Median Of Three (" + min + ")");
			fileWriter.println("Array Size: " + size);
			fileWriter.println("Order: " + type);
			fileWriter.println("Number of Trials: " + trials);
			fileWriter.println("Average Time: " + average);
			fileWriter.println();
			
			fileWriter.close();
		}
		catch(IOException e) {
			
		}
	}
	
	public void randomPivot(Integer [] theArray, String type, int size, int trials) {
		long sum = 0;
		Integer [] tempArray = new Integer[size];
		for (int i = 0; i < trials; i++) {
			
			Arrays.fill(tempArray, null);
			System.arraycopy(theArray, 0, tempArray, 0, theArray.length);
			
			long start = System.nanoTime();		//Start time
			
			//Execute the RandomPivot QuickSort
			RandomPivot.quickSort(tempArray, size);
			
			long finish = System.nanoTime();	//End time
			
			long delta = finish - start;		//Subtract the difference
			sum = sum + delta;
			
			if (size <= 20) {
				System.out.println("Algorithm: RandomPivot");
				System.out.println("Array Size: " + size);
				System.out.println("Order: " + type);
				System.out.println("Before: " + Arrays.toString(theArray));
				System.out.println("After: " + Arrays.toString(tempArray));
				System.out.println("Time: " + delta);
				System.out.println();
			}
			
		}
		
		double average = sum / trials;
		average = average / 1000000000;		//Divide by 1 billion in order to get the time in seconds
		
		//Write the appropriate data to the text file
		try {
			PrintWriter fileWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
			fileWriter.println("Algorithm: RandomPivot");
			fileWriter.println("Array Size: " + size);
			fileWriter.println("Order: " + type);
			fileWriter.println("Number of Trials: " + trials);
			fileWriter.println("Average Time: " + average);
			fileWriter.println();
			
			fileWriter.close();
		}
		catch(IOException e) {
			
		}
	}
}

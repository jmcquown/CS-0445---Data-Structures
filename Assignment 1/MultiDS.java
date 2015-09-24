import java.util.*;
//John McQuown CS 0445
//January 17, 2015
public class MultiDS<T> implements Reorder, PrimQ<T>{
	private T[] bag;
	private int numOfItems;
	private static final int defaultSize = 52;
	
	//If no size if specified the array is given a default size of 52
	public MultiDS() {
		this(defaultSize);
		numOfItems = 0;
	}
	//If a size is specified an array of type T is created with the specified size
	//Pretty much took direct inspiration from ArrayBag.java
	public MultiDS(int desiredSize) {
		T[] tempBag = (T[]) new Object[desiredSize];
		bag = tempBag;
		numOfItems = 0;
	}
	//Check to see if the array is full, if it is then return false
	//If the array is not full add the parameter to the last spot in the array
	public boolean addItem(T item) {
		if (full() == true) {
			return false;
		}
		else {
			bag[numOfItems] = item;
			numOfItems++;
			return true;
		}
	}
	//Check if the array is empty, if it is then return null
	//If the array is not empty remove the first element of the array
	 public T removeItem() {

		 if (!empty()) {
			 T result = bag[0];
			 bag[0] = null;
			 for (int i = 0; i < numOfItems - 1; i++) 
				 bag[i] = bag[i + 1];
			 bag[numOfItems - 1] = null;
			 numOfItems--;
			 return result;
		 }
		 else 
			 return null;
	 }
	 //If the logical size of the array is greater than or equal to the physical size, then the array is full
	 public boolean full() {
		 if (numOfItems >= bag.length)
			 return true;
		 else
			 return false;
	 }
	 
	 //If the logical size is equal to zero, then the array is empty
	 public boolean empty() {
		 if (numOfItems == 0)
			 return true;
		 else
			 return false;
	 }
	 //Returns the logical size of the array
	 public int size() {
		 return numOfItems;
	 }
	 //While loop that that calls the removeItem method as long as the array has an element in it
	 public void clear() {
		 while (!empty()) 
			 removeItem();
		 
	 }
	 //For loop that reverse the elements in the array that are not null
	 public void reverse() {
		 for (int i = 0; i < size()/2; i++) {
			 T temp = bag[i];
			 bag[i] = bag[size()-1-i];
			 bag[size()-1-i] = temp;
		 }
	 }
	 //Puts the last element in the array at the beginning
	 public void shiftRight() {
		 T last = bag[numOfItems - 1];
		 for (int i = numOfItems-2; i >= 0; i--)
			 bag[i+1] = bag[i];
		 bag[0]=last;
		 
	 }
	 //Puts the first element in the array at the end
	 public void shiftLeft() {
		 T first = bag[0];
		 for (int i = 0; i < numOfItems; i++)
			 bag[i] = bag[i+1];
		 bag[numOfItems - 1] = first;
	 }
	 //Shuffles the elements in the array
	 public void shuffle() {
		 Random rand = new Random();
		 for (int i = 0; i < size(); i++) {
			 int index = rand.nextInt(i + 1);
			 T temp = bag[index];
			 bag[index] = bag[i];
			 bag[i] = temp;
		 }
	 }
	 //Make a StringBuffer and append to it every element in the array that is not null using a for loop
	 //Instead of using the physical array length in the for loop, I instead use the logical size
	 //This makes sure it does not include any nulls in my string
	 public String toString() {
		 StringBuffer result = new StringBuffer();
		 for (int i = 0; i < size(); i++) {
			 result.append(bag[i] + " ");
		 }
		 //Set a string equal to the toString of the StringBuffer and return it
		String bagString = result.toString();
		return bagString;
	 }
}

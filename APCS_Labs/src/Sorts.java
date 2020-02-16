// Kristine McLaughlin, Period 6, 1/30/19
import java.util.*;

/**
 *  Description of the Class
 *
 * @author     Kristine McLaughlin
 * @created    January 17, 2019
 */
public class Sorts{
	
  private long steps;

  /**
   *  This method sets the number of steps to 0.
   */
  public Sorts(){
    steps = 0;
  }

  /**
   *  This method uses bubble sort to arrange an ArrayList of Comparable items in ascending order
   *  by bubbling the smallest items to the left.
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void bubbleSort(ArrayList <Comparable> list){
	steps = 4; // initialize numPass(1) call size(1) compare with <(1) math operation (1)
	for(int numPass = 0; numPass < list.size()-1; numPass++) {
		if(numPass != 0) steps++; // increment numPass (1)
		
		for(int index = list.size()-1; index > numPass; index--) {
			steps += 3; // for decrementing index, also counts initializing index on round 1(1) call size(1) math operation(1)
			steps += 4; // call get (2) and compareTo (1) math operation (1)
			if(list.get(index).compareTo(list.get(index-1)) < 0) {
				Comparable current = list.get(index);
				list.set(index, list.get(index-1));
				list.set(index-1, current);
				steps += 7; // initialize current (1) call get method (2) call set method (2) math operation (2)
			}
		}
	}
  }

  /**
   *  This method uses selection sort to arrange an ArrayList of Comparable items in ascending order
   *  by bubbling the smallest items to the left.
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void selectionSort(ArrayList <Comparable> list){
	  steps = 4; // initialize numPass(1) call size(1) comparison with <(1), but steps=2 instead of 3 math operation (1)
	  for(int numPass = 0; numPass < list.size()-1; numPass++) {
		    if(numPass != 0) steps++; // increment numPass (1) call size() (1)
		    
			int lowest = numPass;
			steps++; // initialize lowest
			for(int index = numPass; index < list.size(); index++) {
				steps += 2; // initialize index (1) call get (2) call compareTo (1)
				if(list.get(index).compareTo(list.get(lowest)) < 0) {
					lowest = index;
					steps++; // change value of lowest
				}
				
			}
			
			// switch the lowest element and the left-most element of the pass
			Comparable first = list.get(numPass);
			list.set(numPass, list.get(lowest));
			list.set(lowest, first);
			steps += 5; // initialize first (1) call get(2) call set(2)
		}
  }

  /**
   *  This method uses insertion sort to arrange an ArrayList of Comparable items in ascending order
   *  by bubbling the smallest items to the left.
   *
   * @param  list  reference to an array of integers to be sorted
   */
  public void insertionSort(ArrayList <Comparable> list){
	  steps = 3; // initialize numPass(1) call size(1) compare with <(1)
	  for(int numPass = 1; numPass < list.size(); numPass++) {
		    if(numPass != 1) steps++; // increment numPass(1)
			Comparable current = list.get(numPass);
			steps += 2; // initialize current(1) call get(1)
			
			steps += 4; // call compareTo(1) call get(1) comparison with <(1) math operation (1)
			if(current.compareTo(list.get(numPass-1)) < 0) {
				int correctIndex = numPass-1;
				steps+=2; // set value of correctIndex(1) math operation (1)
				
				steps += 5; // comparison >= < &&(3) call compareTo(1) call get(1)
				while(correctIndex >= 0 && current.compareTo(list.get(correctIndex)) < 0) {
					list.set(correctIndex+1, list.get(correctIndex));
					correctIndex--;
					steps += 3; // set(1) get(1) math operation (1)
				}
				correctIndex++;
				steps++; // call increment(1)
				
				// insert the original number, current, into index correctIndex
				list.set(correctIndex, current);
				steps++; // call set(1)
			}
		}
  }


 /**
   *  Takes in entire vector, but will merge the following sections
   *  together:  Left sublist from a[first]..a[mid], right sublist from
   *  a[mid+1]..a[last].  Precondition:  each sublist is already in
   *  ascending order
   *
   * @param  a      reference to an array of integers to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  mid    midpoint index of range of values to be sorted
   * @param  last   last index of range of values to be sorted
   */
  private void merge(ArrayList <Comparable> a, int first, int mid, int last){
	  ArrayList<Comparable> sorted = new ArrayList<>();
	  int aIndex = first;
	  int bIndex = mid + 1;
	  steps += 6; // initialize variables(4) math operation(1) size(1)
	  for(int index = 0; index < a.size(); index++) {
		  if(aIndex > mid) { // continue adding from the second half
			  if(bIndex <= last) {
				  sorted.add(a.get(bIndex));
				  bIndex++;
				  steps += 3; // add(1) get(1) increment variable(1)
			  }
		  } else if(bIndex >= a.size()) { // continue adding from the first half
			  if(aIndex < a.size()) {
				  sorted.add(a.get(aIndex));
				  aIndex++;
				  steps+=3; // add(1) get(1) increment variable(1)
			  }
		  } else if(a.get(aIndex).compareTo(a.get(bIndex)) <= 0) {
			  steps += 3; // get(2) compareTo(1)
			  // add whichever item at the front of a and b is smaller
			  sorted.add(a.get(aIndex));
			  aIndex++;
			  steps += 3; // add(1) get(1) increment(1)
		  } else if((a.get(aIndex).compareTo(a.get(bIndex)) > 0)){
			  steps += 3; // get(2) compareTo(1)
			  sorted.add(a.get(bIndex));
			  bIndex++;
			  steps += 3; // add(1) get(1) increment(1)
		  }
	  }
	  // transfer elements from ArrayList sorted to a
	  steps += 2; // initialize i(1) size(1)
	  for(int i = 0; i < sorted.size(); i++) {
		  a.set(first, sorted.get(i));
		  first++;
		  steps += 4; // increment i(1) set(1) get(1) increment(1)
	  }
  }

  /**
   *  Recursive mergesort of an array of integers
   *
   * @param  a      reference to an array of integers to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  last   ending index of range of values to be sorted
   */
  //Recursively divides a list in half, over and over. When the
  // sublist has one or two values, stop subdividing.
  // Sort ascending order
  public void mergeSort(ArrayList <Comparable> a, int first, int last){
	   int length = last - first;
	   steps += 2; // initialize length(1) math operation (1)
	   
	   if (length == 0){
		   // do nothing
	   } else if (length == 1){
		  steps += 4; // size(1) get(2) compareTo(1)
	      if(a.get(first).compareTo(a.get(last)) > 0) {
	    	  swap(a, first, last);
	    	  steps++; // method call(1)
	      }
	   } else{ // divide list into two halves
		   // Find midpoint of current sublist
		  int midpoint = (first + last) / 2;
		  steps += 3; // initialize variable(1) math operation(2)
	      // Call mergeSort and process left sublist
		  mergeSort(a, first, midpoint); 
		  steps++; // method call(1)
	      // Call mergeSort and process right sublist
		  mergeSort(a, midpoint + 1, last);
		  steps += 2; // method call(1) math operation(1)
	      // merge left and right sublists
		  merge(a, first, midpoint, last);
		  steps ++; // method call(1)
	   } 
  }
  
  /**
   *  Recursive quicksort of an array of integers
   *
   * @param  a      reference to an array of integers to be sorted
   * @param  first  starting index of range of values to be sorted
   * @param  last   ending index of range of values to be sorted
   */
  // ascending order
  public void quickSort(ArrayList <Comparable> a, int first, int last){
	  if(first >= last){
		  // do nothing (base case)
	  } else{
		  // set pivot to last element in array
		  Comparable pivot = a.get(last);
		  steps += 2; // initialize(1) get(1)
		  
		  // split smaller elements to the left, larger to the right
		  int lastSmall = first-1;
		  steps += 3; // initialize(2) math operation(1)
		  for(int index = first; index < last; index++) {
			  steps += 2; // get(1) compareTo(1)
			  if(a.get(index).compareTo(pivot) <= 0) {
				  lastSmall++;
				  swap(a, lastSmall, index);
				  steps += 2; // increment(1) swap(1)
			  }
			  steps++; // increment(1)
		  }
		  // put pivot in place of nextSwap + 1
		  int pivIndex = lastSmall+1;
		  swap(a, pivIndex, last);
		  steps += 3; // initialize(1) math operation(1) swap(1)
		  
		  // recursive call on the left and right sides of the pivot
		  quickSort(a, first, pivIndex-1);
		  quickSort(a, pivIndex+1, last);
		  steps += 4; // quickSort(2) math operation(2)
	  }
  }

 
  /**
   *  Accessor method to return the current value of steps
   *
   */
  public long getStepCount(){
    return steps;
  }

  /**
   *  Modifier method to set or reset the step count. Usually called
   *  prior to invocation of a sort method.
   *
   * @param  stepCount   value assigned to steps
   */
  public void setStepCount(long stepCount){
    steps = stepCount;
  }
  
   /**
   *  Interchanges two elements in an ArrayList
   *
   * @param  list  reference to an array of integers
   * @param  a     index of integer to be swapped
   * @param  b     index of integer to be swapped
   */
  public void swap(ArrayList <Comparable> list, int a, int b){
	  Comparable temp = list.get(a);
	  list.set(a, list.get(b));
	  list.set(b, temp);
	  steps += 5; // initialize(1) get(2) set(2)
  }
}

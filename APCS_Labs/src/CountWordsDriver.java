// Kristine McLaughlin, Period 6 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CountWordsDriver {

	public static void main(String[] args) {
		P6_McLaughlin_Kristine_CountWords lincoln = new P6_McLaughlin_Kristine_CountWords("lincoln.txt");
		/* To run this program on your own text file, pass the name of the file in quotes instead of lincoln.txt, and
		 * make sure the file is located in the same folder/directory as this program.
		 */
		lincoln.printTop30();
		System.out.println("\nNumber of unique words used = " + lincoln.numUniqueWords());
		System.out.print("Total # of words = " + lincoln.getNumWords());
	}

}

class P6_McLaughlin_Kristine_CountWords {
	private Scanner s;
	private ArrayList<String> allWords = new ArrayList<>();
	private ArrayList<Word> sortedWords = new ArrayList<>();
	
	public P6_McLaughlin_Kristine_CountWords(String txt) {
		try{
		     s = new Scanner(new File(txt));
		     while(s.hasNext()) {
		    	 String word = s.next();
		    	 wordsToArray(word);
		     }
		     
		     sortedWords = findFrequencies(allWords);
		     sortByFrequency(sortedWords);
		} catch(FileNotFoundException e){
		     System.out.println("Error: " + e.getMessage());
		}
	}
	
	public int getNumWords() {
		return allWords.size();
	}
	
	private void wordsToArray(String str) {
		ArrayList<String> temp = new ArrayList<>();
		boolean letter = false;
		String word = "";
		
		// separate the string passed in to words and put them in array temp
		for(int i = 0; i < str.length(); i++) {
			if(Character.isLetter(str.charAt(i))) {
				letter = true;
				word += Character.toLowerCase(str.charAt(i));
			}
			
			if(i + 1 >= str.length() || !Character.isLetter(str.charAt(i))) {
				if(letter) {
					if(str.charAt(i) == '-' && Character.isLetter(str.charAt(i-1)) && i+1 < str.length() && Character.isLetter(str.charAt(i+1))) {
						// the word-word = 1 word case
						word += str.charAt(i);
					} else if(str.charAt(i) == '\''){
						// apostrophe case, ex can't
						word += str.charAt(i);
						// if the ' is at the end of the word
						if(i+1 >= str.length()) {
							temp.add(word);
							letter = false;
							word = "";
						}
					} else {
						temp.add(word);
						letter = false;
						word = "";
					}
					
				} else if(str.charAt(i) == '\''){
					// taking care of cases where ' is at the beginning of the word (ex: 'tis)
					word += '\'';
				}
				
			}
		}
		
		// add elements from temp to allWords
		for(int i = 0; i < temp.size(); i++) {
			allWords.add(temp.get(i));
		}
	}
	
	private ArrayList<Word> findFrequencies(ArrayList<String> allWords) {
		// copy words to a new ArrayList so I can preserve the original list
		ArrayList<String> temp = new ArrayList<>();
		for(int i = 0; i < allWords.size(); i++) {
			temp.add(allWords.get(i));
		}
		
		ArrayList<Word> frequencies = new ArrayList<>();
		int i = 0;
		while(i < temp.size()) {
			String current = temp.get(0);
			// look through the list to see how many times the word appears and remove each occurrence
			int count = 0;
			for(int j = 0; j < temp.size(); j++) {
				if(current.compareTo(temp.get(j)) == 0) {
					count++;
					temp.remove(j);
					j--;
				}
			}
			frequencies.add(new Word(current, count));
		}
		return frequencies;
	}
	
	private void sortByFrequency(ArrayList<Word> words) {
		// using selection sort, descending order
		for(int numPass = 0; numPass < words.size()-1; numPass++) {
			int highest = numPass;
			for(int index = numPass+1; index < words.size(); index++) {
				if(words.get(index).compareTo(words.get(highest)) > 0) {
					highest = index;
				} 
			}
			Word temp = words.get(numPass);
			words.set(numPass, words.get(highest));
			words.set(highest, temp);
		}
	}
	
	public int numUniqueWords() {
		return sortedWords.size();
	}
	
	public void printTop30() {
		for(int i = 0; i < 30; i++) {
			System.out.printf("%4d%4d  ", i+1, sortedWords.get(i).getFrequency());
			System.out.println(sortedWords.get(i));
		}
	}
}

class Word implements Comparable<Word> {
	private String label;
	private int frequency;
	
	public Word(String name, int freq) {
		label = name;
		frequency = freq;
	}
	
	// setters and getters
	public String getLabel() {
		return label;
	}
	public int getFrequency() {
		return frequency;
	}
	public void setLabel(String newLabel) {
		label = newLabel;
	}
	public void setFrequency(int num) {
		frequency = num;
	}
	public void incrementFre() {
		frequency++;
	}
	
	public int compareTo(Word other) {
		int diff = frequency - other.getFrequency();
		if(diff == 0){
			diff = other.getLabel().compareTo(label);
		}
		return diff;
	}
	
	public String toString() {
		return label;
	}
}

package hangman;

import java.util.*;
import java.io.*;

public class DictionaryReader {
	
	/**
	 * Checks whether a given string contains a number;
	 * returns true if so, false otherwise
	 * @param x
	 * @return
	 */
	public boolean containsNumber(String x) {
		// create a character array from the string
		// and iterate through it
	    for (char c : x.toCharArray()) {
	    		// check whether any element is a digit
	        if (Character.isDigit(c)) {
	        	// if so, return true
	      	  	return true;
	        }
	    }
	    // otherwise, return false
	    return false;
	}
	
	/**
	 * Given a file, removes all lines containing characters,
	 * capital letters, spaces or numbers
	 * and returns the remaining lines as an arrayList
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public ArrayList<String> checkDictionary(File file) throws IOException {
		  // initialize a new reader
		  BufferedReader in = new BufferedReader(new FileReader(file));
		  
		  int line = 0;
		  
		  ArrayList<String> validList = new ArrayList<String>();
		  
		  // iterate through the file lines
		  for (String x = in.readLine(); x != null; x = in.readLine()) {
			  // increment the line number each iteration
			  line++;
			  // if the line contains any capitals, skip it
	          if (!x.equals(x.toLowerCase())) {
	        	  	line++;
	          }
	          // if the line contains any hyphens, skip it
	          else if (x.indexOf("-") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any periods, skip it
	          else if (x.indexOf(".") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any apostrophes, skip it
	          else if (x.indexOf("'") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any spaces, skip it
	          else if (x.indexOf(" ") >= 0) {
	        	  	line++;
	          }
	          // if the line contains any numbers, skip it
	          else if (containsNumber(x)) {
	        	  	line++;
	          }
	          // otherwise, return the line
	          else {
	        	  	validList.add(x);
	          }
	          
		  }
		  // close the reader
		  in.close();
		  // return the resulting list
		  return validList;
	}
	
	/**
	 * Given an arrayList of strings, picks a random index and
	 * and returns the value at that index
	 * @param file
	 * @return
	 */
	public String pickRandomWord(ArrayList<String> list) {
		// initialize the Random class
		Random rand = new Random();
		// pick a random index of the array list
		int randomIndex = rand.nextInt(list.size());
		// return the word at that index
		return(list.get(randomIndex));
	}
	
	/**
	 * Given an arrayList of strings, returns all strings of the specified length
	 * in a new arrayList
	 * @param validList
	 * @param length
	 * @return
	 */
	protected ArrayList<String> getWordsOfLength(ArrayList<String> validList, int length) {
		// initialize an empty arrayList
		ArrayList<String> lengthList = new ArrayList<String>();
		
		// iterate through the input list
		for (int i = 0; i < validList.size(); i++) {
			// if the length of the word is equal to the input length, append it to the initial list
			if (validList.get(i).length() == length) {
				lengthList.add(validList.get(i));
			}
		}
		// return the resulting list
		return lengthList;
	}
	
	
	/**
	 * Given a list of words and a letter, returns all words in the list that contain
	 * the specified letter
	 * @param list
	 * @param letter
	 * @return
	 */
	protected ArrayList<String> getWordsContainingLetter(ArrayList<String> list, String letter) {
		// initialize an empty arrayList
		ArrayList<String> letterList = new ArrayList<String>();
		
		// iterate through the input list
		for (int i = 0; i < list.size(); i++) {
			// check whether the letter is in each word of the list
			if (list.get(i).indexOf(letter) >= 0) {
				// if so, append it to the initial list
				letterList.add(list.get(i));
			}
		}
		// return the resulting list
		return letterList;
	}
	
	/**
	 * Given a list of words and a letter, returns all words in the list that do NOT
	 * contain the specified letter
	 * @param list
	 * @param letter
	 * @return
	 */
	protected ArrayList<String> getWordsNotContainingLetter(ArrayList<String> list, String letter) {
		// get the list of all words containing that letter
		ArrayList<String> contains = getWordsContainingLetter(list, letter);
		// create sets from the two lists
		Set<String> set = new HashSet<String>(list);
		Set<String> set2 = new HashSet<String>(contains);
		// subtract the second set from the first
		set.removeAll(set2);
		// convert the result back to an arrayList
		ArrayList<String> finalList = new ArrayList<String>(set);
		// return the resulting list
		return finalList;
	}
	
	/**
	 * Given a list of strings and a list of letters (strings), removes any words in the first list
	 * that contain any letters in the second list and returns the result
	 * as a new arrayList
	 * @param letterList
	 * @param guesses
	 * @return
	 */
	protected ArrayList<String> subtractWordsContainingLetter(ArrayList<String> letterList, ArrayList<String> guesses) {
		// initialize a list of items that we will remove from the original letterList
		ArrayList<String> removeList = new ArrayList<String>();
		// iterate through all elements of the guesses list;
		// if any of them is in the letterList, add it to the list of items we will remove
		for (int i = 0; i < guesses.size(); i++) {
			for (int j = 0; j < letterList.size(); j++) {
				if (letterList.get(j).indexOf(guesses.get(i)) >= 0) {
					removeList.add(letterList.get(j));
				}
			}
		}
		// create sets from both lists
		Set<String> set = new HashSet<String>(letterList);
		Set<String> set2 = new HashSet<String>(removeList);
		// remove all elements of set2 from the original set
		set.removeAll(set2);
		// convert the original set back to a list
		ArrayList<String> finalList = new ArrayList<String>(set);
		// return the resulting list
		return finalList;
	}
	
	/**
	 * Given a list of words, returns a hash map such that the keys are the list elements,
	 * and the values are lists containing the indices of the letter within that word.
	 * Assuming we start with a list whose elements all contain at least
	 * one occurrence of the given letter (i.e. getWordsContainingLetter has already been
	 * called on the list).
	 * @param list
	 * @param letter
	 * @return
	 */
	protected Map<String, ArrayList<Integer>> letterCountHashMap(ArrayList<String> list, String letter) {
		// initialize an empty hash map with strings as keys and integers as values
		Map<String, ArrayList<Integer>> myMap = new HashMap<String, ArrayList<Integer>>();
		// iterate through the array list of strings
		for (int i = 0; i < list.size(); i++) {
			// initialize an empty array of indices
			ArrayList<Integer> indicesList = new ArrayList<Integer>();
			// get initial index of the letter
			int index = list.get(i).indexOf(letter);
			// iterate through the list, incrementing the index each time
			while (index >= 0) {
				indicesList.add(index);
				index = list.get(i).indexOf(letter, index + 1);
			}
			// add the results to the hash map
			myMap.put(list.get(i), indicesList);
		}
		// return the resulting hash map
		return myMap;
	}
	
	/**
	 * Returns a list of lists containing words in the same "families"-
	 * that is, words with the same letter in the same index positions
	 * @param map
	 * @return
	 */
	protected ArrayList<ArrayList<String>> breakWordsIntoFamilies(Map<String, ArrayList<Integer>> map) {
		// initialize empty Map
		Map<ArrayList<Integer>, ArrayList<String>> ret = new HashMap<ArrayList<Integer>, ArrayList<String>>();
		// get the keys of the input arrayList as a new arrayList
		ArrayList<String> keys = new ArrayList<>(map.keySet());
		// get the values of the input arrayList as a new arrayList
		ArrayList<ArrayList<Integer>> values = new ArrayList<>(map.values());
		// create an arrayList of arrayLists of the keys list
		ArrayList<ArrayList<String>> keyList = new ArrayList<ArrayList<String>>();
		// iterate through the initial keys arrayList and create empty arrayLists ('temp')
		// for each key
		for (int i = 0; i < keys.size(); i++) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(keys.get(i));
			keyList.add(temp);
		}
		// then iterate through the rest of the entries
		// and if we encounter a non-empty value at that index, put it into the
		// return hash map
		for (int i = 0; i < keys.size(); i++) {
			if (ret.get(values.get(i)) == null) {
				ret.put(values.get(i), keyList.get(i));
			}
			else {
				ret.get(values.get(i)).addAll(keyList.get(i));
			}
		}
		// get a list of just the values of the return hash map - these are the word families
		ArrayList<ArrayList<String>> returnList = new ArrayList<>(ret.values());
		// return the resulting list of families
		return returnList;
	}
	
	/**
	 * Given a lists of lists, returns the largest sublist
	 * @param list
	 * @return
	 */
	protected ArrayList<String> getLargestFamily(ArrayList<ArrayList<String>> list) {
		// create an empty arrayList to begin
		ArrayList<String> maxList = new ArrayList<String>();
		// get the size of the original list
		int size = maxList.size();
		// iterate through the input list and get the size of each item;
		// if the size is greater than the size of maxList, set that item
		// to be the new maxList
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).size() > size) {
				maxList = list.get(i);
			}
		}
		// return the list with the greatest size
		return maxList;
	}
	
	/**
	 * Returns true if two strings are in the same family; false otherwise.
	 * Specifically, this function takes in a string of mixed letters and 
	 * underscores, as well as a string containing a word. 
	 * The function ignores underscores in the former string.
	 * @param underscores
	 * @param word
	 * @return
	 */
	protected boolean areInSameFamily(String underscores, String word) {
		// initialize a boolean list of length of underscores
		boolean[] boolList = new boolean[underscores.length()];
		// convert underscores and word to character arrays
		char[] underscoresList = underscores.toCharArray();
		char[] wordList = word.toCharArray();
		// iterate through the underscores list, ignoring '_' characters.
		// if the value of underscores at that index equals the value of
		// word list at that index, return 'true' at that index in the boolean
		// list. Otherwise, return 'false'.
		for (int i = 0; i < underscoresList.length ; i++) {
			if (underscoresList[i] != '_') {
				if (underscoresList[i] == wordList[i]) {
					boolList[i] = true;
				}
				else {
					boolList[i] = false;
				}
			}
			else {
				boolList[i] = true;
			}
		}
		// now see if there are any falses in the boolList;
		// if so, return false. Otherwise return true.
		for (boolean b : boolList) {
			if (!b) { 
				return false;
			}
		}
	    return true;
 	}
	
	/**
	 * Returns true if all the words in the list are in the same word
	 * family as the string; false otherwise.
	 * @param list
	 * @param underscores
	 * @return
	 */
	protected boolean listContainsWordInFamily(ArrayList<String> list, String underscores) {
		// initialize a boolean list of length of the list
		boolean[] boolList = new boolean[list.size()];
		// iterate through the list.
		// if the value of the list at that index and the value of the list at
		// that index are in the same family, return 'true' at the corresponding
		// index in the boolean array. Otherwise return 'false'.
		for (int i = 0; i < list.size(); i++) {
			if (areInSameFamily(underscores, list.get(i))) {
				boolList[i] = true;
			}
			else {
				boolList[i] = false;
			}
		}
		// check whether the boolean list contains all true's;
		// if so, return true. Otherwise return false.
		for (boolean b : boolList) {
			if (b) { 
				return true;
			}
		}
	    return false;
	}

	/**
	 * Puts all the above functions together to form the evil hangman's play;
	 * takes in the list of words of given length, the guess, the list of incorrect
	 * guesses, and the original word and outputs a new word
	 * @param lengthList
	 * @param guess
	 * @param incorrect
	 * @param word
	 * @param underscores
	 * @return
	 */
	protected String evilHangmanPlay(ArrayList<String> lengthList, String guess, ArrayList<String> incorrect, 
			String word, String underscores) {
		// get all words containing the specified letter
		ArrayList<String> letterList = getWordsContainingLetter(lengthList, guess);
		// subtract off any words containing any of the letters in incorrect guesses,
		// i.e. letters that have already been guessed
		ArrayList<String> newLetterList = subtractWordsContainingLetter(letterList, incorrect);
		
		// select the "best word class" for the computer to choose
		// Candidate 1 is the largest family of all the words containing that letter
		Map<String, ArrayList<Integer>> map = letterCountHashMap(newLetterList, guess);
		ArrayList<ArrayList<String>> families = breakWordsIntoFamilies(map);
		ArrayList<String> candidate1 = getLargestFamily(families);
		
		// Candidate 2 is the list of words NOT containing that letter
		ArrayList<String> temp = getWordsNotContainingLetter(lengthList, guess);
		ArrayList<String> candidate2 = subtractWordsContainingLetter(temp, incorrect);
		
		// get the sizes of both candidate classes
		int size1 = candidate1.size();
		int size2 = candidate2.size();
		
		// if size1 is bigger than size2 and candidate1 doesn't contain any words in the
		// same family as underscores, just return the existing word
		if ((size1 >= size2) && (!listContainsWordInFamily(candidate1, underscores))) {
			return word;
		}
		// if size2 is bigger than size1 and candidate2 doesn't contain any words in the
		// same family as underscores, just return the existing word
		else if (((size1 < size2) && (!listContainsWordInFamily(candidate2, underscores)))) {
			return word;
		}
		// if size1 is bigger than size2 and candidate1 contains at least one word in the
		// same family as underscores, pick a new random word until we've found the word that
		// is in the same family as underscores and set word equal to that word
		else if ((size1 >= size2) && (listContainsWordInFamily(candidate1, underscores))) {
			word = pickRandomWord(candidate1);
			while (areInSameFamily(underscores, word) == false) {
				word = pickRandomWord(candidate1);
			}
		}
		// if size2 is bigger than size1 and candidate2 contains at least one word in the
		// same family as underscores, pick a new random word until we've found the word that
		// is in the same family as underscores and set word equal to that word
		else if ((size1 < size2) && (listContainsWordInFamily(candidate2, underscores))){
			word = pickRandomWord(candidate2);
			//System.out.println("Word: " + word);
			while (areInSameFamily(underscores, word) == false) {
				word = pickRandomWord(candidate2);
				System.out.println("picked a new word: " + word);
			}
		}
		// finally, return the word
		return word;
	}
	
}

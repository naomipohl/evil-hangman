package hangman;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class DictionaryReaderTest {
	
	// create a DictionaryReader object
	private DictionaryReader dict;
	
	// Set up initial DictionaryReader instance
	@Before
	public void setUp(){
		// create a dictionary reader object
		dict = new DictionaryReader();
	}
	
	// test containsNumber
	
	@Test
	public void testContainsNumberEmptyString() {
		String x = "";
		assertFalse("Empty strings don't contain numbers",
					dict.containsNumber(x));
	}
	
	@Test
	public void testContainsNumberJustLetters() {
		String x = "asfksdlfkjalsjoqiwe";
		assertFalse("Alphabetic strings don't contain numbers",
					dict.containsNumber(x));
	}
	
	@Test
	public void testContainsJustNumbers() {
		String x = "32427907";
		assertTrue("Strings with numbers should return True",
					dict.containsNumber(x));
	}
	
	@Test
	public void testContainsBothNumbersLetters() {
		String x = "3k5k2h2";
		assertTrue("Strings with numbers should return True",
					dict.containsNumber(x));
	}
	
	@Test
	public void testContainsSymbols() {
		String x = "#*(&@$@*&$";
		assertFalse("Strings with characters should return False",
					dict.containsNumber(x));
	}
	
	// test pickRandomWord
	
	@Test
	public void testPickRandomWordNotEmptyList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("goodbye");
		list.add("computer");
		
		assertFalse("This method should return a word when the list is non-empty",
				dict.pickRandomWord(list).equals(null));
	}
	
	// test getWordsOfLength
	
	@Test
	public void testGetWordsOfLength() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfj");
		list.add("iiiii");
		list.add("goodbye");
		list.add("computer");
		
		assertTrue("Returns all words of the given length",
				dict.getWordsOfLength(list, 5).contains("hello"));
		assertTrue("Returns all words of the given length",
				dict.getWordsOfLength(list, 5).contains("itsfj"));
		assertTrue("Returns all words of the given length",
				dict.getWordsOfLength(list, 5).contains("iiiii"));
		assertFalse("Returns all words of the given length",
				dict.getWordsOfLength(list, 5).contains("goodbye"));
		assertFalse("Returns all words of the given length",
				dict.getWordsOfLength(list, 5).contains("computer"));
	}
	
	@Test
	public void testGetWordsOfLengthEmptyList() {
		ArrayList<String> list = new ArrayList<String>();
		
		assertTrue("An empty list returns an empty list",
				dict.getWordsOfLength(list, 5).isEmpty());
	}
	
	@Test
	public void testGetWordsOfLengthEmptyListNoWordsMatchLength() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("iiiii");
		list.add("goodbye");
		
		assertTrue("If no words are of the given length, the list is empty",
				dict.getWordsOfLength(list, 15).isEmpty());
	}
	
	// test getWordsContainingLetter
	@Test
	public void testGetWordsContainingLetter() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfj");
		list.add("iiiii");
		list.add("goodbye");
		list.add("computer");
		
		assertTrue("Returns all words containing the given letter",
				dict.getWordsContainingLetter(list, "h").contains("hello"));
		assertFalse("Should not return words not containing the given letter",
				dict.getWordsContainingLetter(list, "h").contains("itsfj"));
		assertFalse("Should not return words not containing the given letter",
				dict.getWordsContainingLetter(list, "h").contains("iiiii"));
		assertFalse("Should not return words not containing the given letter",
				dict.getWordsContainingLetter(list, "h").contains("goodbye"));
		assertFalse("Should not return words not containing the given letter",
				dict.getWordsContainingLetter(list, "h").contains("computer"));
	}
	
	@Test
	public void testGetWordsContainingLetterEmptyList() {
		ArrayList<String> list = new ArrayList<String>();
		
		assertTrue("Returns an empty list if the input list is empty",
				dict.getWordsContainingLetter(list, "h").isEmpty());
	}
	
	@Test
	public void testGetWordsContainingLetterNoWordsContainLetter() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfj");
		list.add("iiiii");
		list.add("goodbye");
		list.add("computer");
		
		assertTrue("Should not return words not containing the given letter",
				dict.getWordsContainingLetter(list, "z").isEmpty());
	}
	
	// test getWordsNotContainingLetter
	@Test
	public void testGetWordsNotContainingLetter() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfj");
		list.add("iiiii");
		list.add("goodbye");
		list.add("computer");
		
		assertTrue("Returns all words not containing the given letter",
				dict.getWordsNotContainingLetter(list, "h").contains("itsfj"));
		assertTrue("Returns all words not containing the given letter",
				dict.getWordsNotContainingLetter(list, "h").contains("iiiii"));
		assertTrue("Returns all words not containing the given letter",
				dict.getWordsNotContainingLetter(list, "h").contains("goodbye"));
		assertTrue("Returns all words not containing the given letter",
				dict.getWordsNotContainingLetter(list, "h").contains("computer"));
		assertFalse("Returns all words not containing the given letter",
				dict.getWordsNotContainingLetter(list, "h").contains("hello"));
	}
	
	@Test
	public void testGetWordsNotContainingLetterEmptyList() {
		ArrayList<String> list = new ArrayList<String>();
		
		assertTrue("Returns an empty list if the input list is empty",
				dict.getWordsNotContainingLetter(list, "h").isEmpty());
	}
	
	@Test
	public void testGetWordsNotContainingLetterAllWordsContainLetter() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfjo");
		list.add("iiiiio");
		list.add("goodbye");
		list.add("computer");
		
		assertTrue("Should return an empty list if all words contain the letter",
				dict.getWordsNotContainingLetter(list, "o").isEmpty());
	}
	
	// test subtractWordsContainingLetter
	@Test
	public void testSubtractWordsContainingLetterOneGuess() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfjo");
		list.add("iiiiio");
		list.add("goodbye");
		list.add("computer");
		
		ArrayList<String> guesses = new ArrayList<String>();
		guesses.add("g");
		
		assertTrue("Should return list containing all words containing the one letter",
				dict.subtractWordsContainingLetter(list, guesses).contains("hello"));
		assertTrue("Should return list containing all words containing the one letter",
				dict.subtractWordsContainingLetter(list, guesses).contains("itsfjo"));
		assertTrue("Should return list containing all words containing the one letter",
				dict.subtractWordsContainingLetter(list, guesses).contains("computer"));
		assertTrue("Should return list containing all words containing the one letter",
				dict.subtractWordsContainingLetter(list, guesses).contains("iiiiio"));
		assertFalse("Should return list containing all words containing the one letter",
				dict.subtractWordsContainingLetter(list, guesses).contains("goodbye"));
	}
	
	@Test
	public void testSubtractWordsContainingLetterTwoGuesses() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfjo");
		list.add("iiiii");
		list.add("goodbye");
		list.add("computer");
		
		ArrayList<String> guesses = new ArrayList<String>();
		guesses.add("g");
		guesses.add("o");
		
		assertFalse("Should return list containing all words containing the two letters",
				dict.subtractWordsContainingLetter(list, guesses).contains("hello"));
		assertFalse("Should return list containing all words containing the two letters",
				dict.subtractWordsContainingLetter(list, guesses).contains("itsfjo"));
		assertFalse("Should return list containing all words containing the two letters",
				dict.subtractWordsContainingLetter(list, guesses).contains("computer"));
		assertTrue("Should return list containing all words containing the two letters",
				dict.subtractWordsContainingLetter(list, guesses).contains("iiiii"));
		assertFalse("Should return list containing all words containing the two letters",
				dict.subtractWordsContainingLetter(list, guesses).contains("goodbye"));
	}
	
	@Test
	public void testSubtractWordsContainingLetterNoGuesses() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("itsfjo");
		list.add("iiiii");
		list.add("goodbye");
		list.add("computer");
		
		ArrayList<String> guesses = new ArrayList<String>();
		
		assertTrue("Should return all the words",
				dict.subtractWordsContainingLetter(list, guesses).contains("hello"));
		assertTrue("Should return all the words",
				dict.subtractWordsContainingLetter(list, guesses).contains("itsfjo"));
		assertTrue("Should return all the words",
				dict.subtractWordsContainingLetter(list, guesses).contains("computer"));
		assertTrue("Should return all the words",
				dict.subtractWordsContainingLetter(list, guesses).contains("iiiii"));
		assertTrue("Should return all the words",
				dict.subtractWordsContainingLetter(list, guesses).contains("goodbye"));
	}
	
	@Test
	public void testSubtractWordsContainingLetterNoListElements() {
		ArrayList<String> list = new ArrayList<String>();
		
		ArrayList<String> guesses = new ArrayList<String>();
		guesses.add("g");
		guesses.add("o");
		
		assertTrue("Should return an empty list",
				dict.subtractWordsContainingLetter(list, guesses).isEmpty());
	}
	
	// test letterCountHashMap
	
	@Test
	public void testLetterCountHashMap() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("tsifjo");
		list.add("iiiii");
		list.add("goodbye");
		list.add("computer");
		
		ArrayList<Integer> indices = new ArrayList<Integer>();
		indices.add(0);
		ArrayList<Integer> indices2 = new ArrayList<Integer>();
		indices2.add(2);
		ArrayList<Integer> indices3 = new ArrayList<Integer>();
		indices3.add(0);
		indices3.add(1);
		indices3.add(2);
		indices3.add(3);
		indices3.add(4);
		
		assertTrue("Returns a hash map where the keys are list elements,"
				+ "and the values are counts of the given letter in the word",
				dict.letterCountHashMap(list, "h").containsKey("hello"));
		assertTrue("Returns a hash map where the keys are list elements,"
				+ "and the values are counts of the given letter in the word",
				dict.letterCountHashMap(list, "i").containsKey("tsifjo"));
		assertTrue("Returns a hash map where the keys are list elements,"
				+ "and the values are counts of the given letter in the word",
				dict.letterCountHashMap(list, "h").containsKey("iiiii"));
		assertTrue("Returns a hash map where the keys are list elements,"
				+ "and the values are counts of the given letter in the word",
				dict.letterCountHashMap(list, "h").containsValue(indices));
		assertTrue("Returns a hash map where the keys are list elements,"
				+ "and the values are counts of the given letter in the word",
				dict.letterCountHashMap(list, "i").containsValue(indices2));
		assertTrue("Returns a hash map where the keys are list elements,"
				+ "and the values are counts of the given letter in the word",
				dict.letterCountHashMap(list, "i").containsValue(indices3));
	}
	
	// test breakWordsIntoFamilies
	
	@Test
	public void testBreakWordsIntoFamilies() {
		Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(0);
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(0);
		
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(0);
		list3.add(3);
		list3.add(4);
		
		map.put("hello", list1);
		map.put("hiopp", list2);
		map.put("hiohh", list3);
		
		ArrayList<String> list4 = new ArrayList<String>();
		list4.add("hiohh");
		
		assertTrue("Returns a list of lists containing the word families",
				dict.breakWordsIntoFamilies(map).contains(list4));
	}
	
	@Test
	public void testBreakWordsIntoFamiliesTwoFamilies() {
		Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(4);
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(4);
		
		ArrayList<Integer> list3 = new ArrayList<Integer>();
		list3.add(0);
		list3.add(3);
		list3.add(4);
		
		map.put("aaaah", list1);
		map.put("bbbbh", list2);
		map.put("hiohh", list3);
		
		ArrayList<String> list4 = new ArrayList<String>();
		list4.add("hiohh");
		
		ArrayList<String> list5 = new ArrayList<String>();
		list5.add("bbbbh");
		list5.add("aaaah");
				
		assertTrue("Returns a list of lists containing the word families",
				dict.breakWordsIntoFamilies(map).contains(list4));
		assertTrue("Returns a list of lists containing the word families",
				dict.breakWordsIntoFamilies(map).contains(list5));
	}
	
	@Test
	public void testBreakWordsIntoFamiliesNoFamilies() {
		Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
				
		assertTrue("Resulting map should be empty",
				dict.breakWordsIntoFamilies(map).isEmpty());
	}
	
	// test getLargestFamily
	
	@Test
	public void testGetLargestFamilyOneList() {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> testList1 = new ArrayList<String>();
		testList1.add("hi");
		testList1.add("hello");
		testList1.add("h");
		testList1.add("goodbye");
		
		list.add(testList1);
		
		assertTrue("Should return the single list if only one list is provided",
				dict.getLargestFamily(list).equals(testList1));
	}
	
	@Test
	public void testGetLargestFamilyMoreThanOneList() {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> testList1 = new ArrayList<String>();
		testList1.add("hi");
		testList1.add("hello");
		testList1.add("h");
		testList1.add("goodbye");
		
		ArrayList<String> testList2 = new ArrayList<String>();
		testList1.add("hi");
		
		list.add(testList1);
		list.add(testList2);
		
		assertTrue("Should return the largest of the two sub-lists",
				dict.getLargestFamily(list).equals(testList1));
	}
	
	@Test
	public void testGetLargestFamilyListsOfSameSize() {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> testList1 = new ArrayList<String>();
		testList1.add("hi");
		testList1.add("hello");
		
		ArrayList<String> testList2 = new ArrayList<String>();
		testList1.add("blah");
		testList1.add("blahblah");
		
		list.add(testList1);
		list.add(testList2);
		
		assertTrue("Should return the first list encountered if there is a tie "
				+ "between list sizes",
				dict.getLargestFamily(list).equals(testList1));
	}
	
	@Test
	public void testGetLargestFamilyEmptyList() {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> testList1 = new ArrayList<String>();
		
		ArrayList<String> testList2 = new ArrayList<String>();
		
		list.add(testList1);
		list.add(testList2);
		
		assertTrue("If neither list contains any strings, should return an empty list",
				dict.getLargestFamily(list).isEmpty());
	}
	
	// test areInSameFamily
	@Test
	public void testAreInSameFamilySameWord() {
		String underscores = "blah";
		String word = "blah";
		
		assertTrue("If the underscores is the same word as the word, return true",
				dict.areInSameFamily(underscores,word));
	}
	
	@Test
	public void testAreInSameFamilyEmptyUnderscores() {
		String underscores = "____";
		String word = "blah";
		
		assertTrue("If the underscores is empty, should always return true",
				dict.areInSameFamily(underscores,word));
	}
	
	@Test
	public void testAreInSameFamilyMixedUnderscores() {
		String underscores = "b_a_";
		String word = "blah";
		
		assertTrue("If underscores is in same familiy as word, should return true",
				dict.areInSameFamily(underscores,word));
	}
	
	@Test
	public void testAreInSameFamilyNotSameFamily() {
		String underscores = "g_a_";
		String word = "blah";
		
		assertFalse("If underscores is not in same familiy as word, should return false",
				dict.areInSameFamily(underscores,word));
	}
	
	// test listContainsWordInFamily
	@Test
	public void testListContainsWordInFamilyEmptyUnderscores() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("blah");
		list.add("this");
		
		String underscores = "____";
		
		assertTrue("If underscores is empty, should always return true",
				dict.listContainsWordInFamily(list, underscores));
	}
	
	@Test
	public void testListContainsWordInFamilyNormalCase() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("blah");
		list.add("this");
		
		String underscores = "b___";
		
		assertTrue("Should return true if list contains word in same family as underscores",
				dict.listContainsWordInFamily(list, underscores));
	}
	
	@Test
	public void testListContainsWordInFamilyNoWordsInFamily() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("blah");
		list.add("this");
		
		String underscores = "g_l_";
		
		assertFalse("Should return false if list doesn't contain word in same family as underscores",
				dict.listContainsWordInFamily(list, underscores));
	}
	
	// test evilHangmanPlay
	// since this method involves random words, we can just check that it returns something
	
	@Test
	public void testEvilHangmanPlay() {
		ArrayList<String> lengthList = new ArrayList<String>();
		lengthList.add("yo");
		lengthList.add("bb");
		lengthList.add("hi");
		lengthList.add("oo");
		
		String guess = "a";
		
		ArrayList<String> incorrect = new ArrayList<String>();
		
		String word = "hi";
		
		String underscores = "__";
		
		assertFalse("This method should return a word",
				dict.evilHangmanPlay(lengthList, guess, incorrect, word,  underscores).equals(null));
	}

}

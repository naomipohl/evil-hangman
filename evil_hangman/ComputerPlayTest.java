package hangman;

import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class ComputerPlayTest {
	
	// create a ComputerPlay object
	ComputerPlay comp;
	
	// Set up initial ComputerPlay instance
	@Before
	public void setUp() {
		comp = new ComputerPlay();
	}
	
	// test showUnderscores
	
	@Test
	public void testShowUnderscores() {
		String word = "computer";
		assertTrue("Show underscores should return a string of underscores of the length of the word",
				comp.showUnderscores(word).equals("________"));
	}
	
	@Test
	public void testShowUnderscoresEmptyString() {
		String word = "";
		assertTrue("Show underscores on an empty string should return an empty string",
				comp.showUnderscores(word).equals(""));
	}
	
	// test isGameOver
	
	@Test
	public void testIsGameOverWhenGameIsOver() {
		String word = "computer";
		String underscores = "computer";
		List<String> incorrect = new ArrayList<String>();
		incorrect.add("h");
		incorrect.add("j");
		assertTrue("Should return true when user has guessed correctly",
				comp.isGameOver(word, underscores, incorrect) == true);
	}
	
	@Test
	public void testIsGameOverWhenOutofGuesses() {
		String word = "laptop";
		String underscores = "computer";
		List<String> incorrect = new ArrayList<String>();
		incorrect.add("h");
		incorrect.add("j");
		incorrect.add("k");
		incorrect.add("l");
		incorrect.add("m");
		incorrect.add("n");
		assertTrue("Should return true when user is out of guesses",
				comp.isGameOver(word, underscores, incorrect) == true);
	}
	
	@Test
	public void testIsGameOverWhenGameIsNotOver() {
		String word = "laptop";
		String underscores = "computer";
		List<String> incorrect = new ArrayList<String>();
		incorrect.add("h");
		incorrect.add("j");
		incorrect.add("k");
		incorrect.add("l");
		incorrect.add("m");
		assertTrue("Should return false when game is not over",
				comp.isGameOver(word, underscores, incorrect) == false);
	}
	
	// test printSpacesBetweenLetters
	
	@Test
	public void testPrintSpacesBetweenLetters() {
		String word = "computer";
		assertTrue("Should print spaces between letters of a string",
				comp.printSpacesBetweenLetters(word).equals(" c o m p u t e r "));
	}
	
	@Test
	public void testPrintSpacesBetweenUnderscores() {
		String word = "_______";
		assertTrue("Should print spaces between characters of a string (such as underscores)",
				comp.printSpacesBetweenLetters(word).equals(" _ _ _ _ _ _ _ "));
	}
	
	@Test
	public void testPrintSpacesBetweenLettersEmpty() {
		String word = "";
		assertTrue("Should return one space if string is empty",
				comp.printSpacesBetweenLetters(word).equals(" "));
	}
	
	// test isInList
	
	@Test
	public void testIsInListOneWord() {
		String word = "hello";
		List<String> list = new ArrayList<String>();
		list.add("hello");
		assertTrue("If a word is in the list, this should return true",
				comp.isInList(word, list) == true);
	}
	
	@Test
	public void testIsInListMoreThanOneWord() {
		String word = "hello";
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("goodbye");
		list.add("another string");
		assertTrue("If a word is in the list, this should return true",
				comp.isInList(word, list) == true);
	}
	
	@Test
	public void testIsInListWordNotInList() {
		String word = "computer";
		List<String> list = new ArrayList<String>();
		list.add("hello");
		list.add("goodbye");
		list.add("another string");
		assertTrue("If a word is in the list, this should return true",
				comp.isInList(word, list) == false);
	}
	
	@Test
	public void testIsInListEmptyString() {
		String word = "";
		List<String> list = new ArrayList<String>();
		assertTrue("If the list is empty, this should return false",
				comp.isInList(word, list) == false);
	}

}

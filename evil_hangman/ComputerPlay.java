package hangman;

import java.util.*;

public class ComputerPlay {
	
	/**
	 * Returns the word as a series of underscores
	 * @param word
	 * @return
	 */
	protected String showUnderscores(String word) {
		// initialize an empty string
		String underscores = "";
		// iterate through the word and add an underscore
		// to the empty string for every letter in the word
		for (int i = 0; i < word.length(); i++) {
			underscores = underscores + "_";
		}
		// return the resulting string
		return underscores;
	}
	
	/**
	 * Checks whether the game is over and returns true or false
	 * @param word
	 * @param underscores
	 * @return
	 */
	protected boolean isGameOver(String word, String underscores, List<String> incorrect) {
		// first check whether the user has guessed wrong 6 times
		if (incorrect.size() == 6) {
			// if so, the game is over
			System.out.println("You're out of guesses- Game over!");
			return true;
		}
		// then check whether the word equals the word with underscores
		else if (word.equals(underscores)) {
			// if so, the game is over
			System.out.println("You won! Game over!");
			return true;
		}
		// if not, the game is not over
		return false;
	}
	
	/**
	 * Prints spaces between letters of a word
	 * @param word
	 * @return
	 */
	protected String printSpacesBetweenLetters(String word) {
		// replace no spaces with spaces in the word
		String returnString = word.replace(""," ");
		// return the resulting string
		return returnString;
	}
	
	/**
	 * Checks whether the given word is in the given list;
	 * returns true if so, false otherwise
	 * @param word
	 * @param list
	 * @return
	 */
	protected boolean isInList(String word, List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).equals(word)) {
				return true;
			}
		}
		return false;
	}

}

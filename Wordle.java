package Wordle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Wordle {
	/**
	 * This method converts any text file to a string array
	 * @param filename - Text file
	 * @return A string array of the words found in a text file
	 * @throws FileNotFoundException
	 */
	public static String[] wordsFromFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner wordReader = new Scanner(file);

		int arraySize = wordReader.nextInt();
		String[] words = new String[arraySize];

		for (int i = 0; i < arraySize; i++)
			words[i] = wordReader.next();

		return words;
	}
	/**
	 * Returns a random word from a string array
	 * @param array - String array of words
	 * @return A random word from that array
	 */
	public static String pickRandomWord(String[] array) {
		String randomWord = (array[new Random().nextInt(array.length)]);

		return randomWord;
	}
	/**
	 * Returns a boolean statement if a word is inside of a string array
	 * @param word - Word to see if in array
	 * @param array - Array of words to match
	 * @return True or false depending if the word is in the array of words
	 */
	public static boolean wordInArray(String word, String[] array) {
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(word))
				return true;
		}
		return false;
	}
	/**
	 * User inputs a word into the console and has to be a five letter word in the array
	 * @param array - String array of words
	 * @return The word picked by the user from the array
	 */
	public static String getUserGuess(String[] array) {
		Scanner userInput = new Scanner(System.in);
		String userGuess;
		System.out.println("Write a 5 letter word");
		do {
			userGuess = userInput.next();
			if (userGuess.length() != 5 || !wordInArray(userGuess, array)) {
				System.out.println("Inputted word has to be 5 letters or in the array");
			}
		} while (userGuess.length() != 5 || !wordInArray(userGuess, array));
		return userGuess;
	}
	/**
	 * Tests to see if a single character is inside a word
	 * @param character - Character to see if in string word
	 * @param string - A single word that's a string 
	 * @return True or false depending if the character is in the word
	 */
	public static boolean letterInWord(char character, String string) {
		for (int i = 0; i < string.length(); i++) {
			if (character == string.charAt(i))
				return true;
		}
		return false;
	}
	/**
	 * Displays a partial word comparing to the guessed word
	 * @param guess - Word that is guessed
	 * @param realWord - The word being compared to
	 */
	public static void displayMatching(String guess, String realWord) {
		String displayWord = "";
		for (int i = 0; i < realWord.length(); i++) {
			if (guess.charAt(i) == realWord.charAt(i)) {
				displayWord = displayWord + Character.toUpperCase(realWord.charAt(i));
			} else if (letterInWord(guess.charAt(i), realWord)) {
				displayWord = displayWord + Character.toLowerCase(guess.charAt(i));
			} else
				displayWord = displayWord + "-";
		}
		System.out.println(displayWord);
	}
	/**
	 * Plays the game
	 * @param args
	 */
	public static void main(String[] args) {
		String[] words;
		try {
			words = wordsFromFile("words.txt");
			String secretWord = (pickRandomWord(words));
			for (int i = 0; i < 6; i++) {
				String guess = getUserGuess(words);
				displayMatching(guess, secretWord);
				if (guess.equals(secretWord)) {
					System.out.println("You Win!");
					break;
				} else if (i == 5) {
					System.out.println("You Lose! The secret word was " + secretWord);
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

	}

}

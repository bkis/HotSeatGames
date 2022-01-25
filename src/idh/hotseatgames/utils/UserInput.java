package idh.hotseatgames.utils;

import java.util.Scanner;

/**
 * A wrapper class for easily prompting for user input and
 * validating it based on various constraints.
 * 
 * @author bkis
 * 
 */
public class UserInput {
	
	private static UserInput instance;
	private Scanner scanner;
	
	/*
	 * A private constructor (can only be called from within this class!)
	 */
	private UserInput() {
		this.scanner = new Scanner(System.in);
	}
	
	/**
	 * Returns the one and only instance of this class. If this method is
	 * called multiple times from different places, it will still return
	 * this one, same instance if this class.
	 * @return UserInput instance
	 */
	public static UserInput instance() {
		if (instance == null) {
			instance = new UserInput();
		}
		return instance;
	}
	
	/**
	 * Prompts the user for arbitrary input using the passed string.
	 * @param prompt The prompt text
	 * @return String containing the user input
	 */
	public String prompt(String prompt) {
		System.out.print(prompt.trim() + " ");
		return this.scanner.nextLine();
	}
	
	/**
	 * Prompts for user input using the passed string. If the input doesn't
	 * match the passed Regular Expression, the user will be prompted again.
	 * @param prompt The prompt text
	 * @param pattern The pattern to match the input with
	 * @return String containing the user input
	 */
	public String prompt(String prompt, String pattern) {
		String input;
		do {
			input = prompt(prompt);
		} while (!input.matches(pattern));
		return input;
	}
	
	/**
	 * Prompts the user for a numeric (integer) value using the passed string.
	 * If the input doesn't match the given constraints (is not within the given range),
	 * the user will be prompted again.
	 * @param prompt The prompt text
	 * @param min the smallest allowed number
	 * @param max the biggest allowed number
	 * @return the number the user has put in
	 */
	public int prompt(String prompt, int min, int max) {
		int input;
		do {
			input = Integer.parseInt(prompt(prompt, "\\d+"));
		} while (input < min || input > max);
		return input;
	}
	
	/**
	 * Cleans up the resources used by this instance
	 */
	public void close() {
		this.scanner.close();
	}
	
}

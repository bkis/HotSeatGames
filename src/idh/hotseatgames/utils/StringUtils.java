package idh.hotseatgames.utils;

import java.util.Scanner;

/**
 * Some string- and CLI-related utility methods 
 * for the HotSeatGames minigame engine
 * 
 * @author bkis
 *
 */
public class StringUtils {
	
	/**
	 * Appends an "'" to a string (name) if it
	 * ends with a "s" or "'s" if it doesn't. 
	 * @param name the name to append to
	 * @return the resulting string
	 */
	public static String apostropheS(String name) {
		return name + (name.matches("(?i).*?s$") ? "'" : "'s");
	}
	
	/**
	 * Prints the given to the console line by line, each one delayed by
	 * the passed amount of milliseconds
	 * @param s the text to print
	 * @param msDelay milliseconds to delay the printing of each line
	 * @param leftPad milliseconds to delay the printing of each line
	 */
	public static void slideInText(String s, int msDelay, int leftPad) {
		if (s == null) return;
		String padString = padString(leftPad);
		for (String line : s.split("\n")) {
			System.out.println(padString + line);
			Delay.now(150);
		}
	}
	
	/**
	 * Prints some line breaks
	 * @param amount
	 */
	public static void printLineBreaks(int amount) {
		for (int i = 0; i < amount; i++) {
			System.out.println();
		}
	}
	
	/**
	 * Prints a lot of newlines to "clear" the console from content
	 */
	public static void cls() {
		printLineBreaks(100);
	}
	
	/**
	 * Indents and wraps the input text at (sequences of) whitespaces so that each line
	 * contains a maximum of {@code charsPerLine} characters. Tokens that are alone longer than
	 * this maximum will NOT be taken apart but will instead be used in a new line as they are,
	 * so this might lead to exceeding {@code charsPerLine} in case of a very low value.
	 * @param text The input text to indent and wrap
	 * @param charsPerLine Maximum number of characters per line in the resulting text
	 * @param pad The padding width
	 * @return The resulting text
	 */
	public static String layout(String text, int charsPerLine, int pad) {
		if (text == null || text.length() == 0) return "";
		
		text = text.replaceAll("\n", " ").replaceAll("\\s+", " ").trim(); // override line breaks
		Scanner scan = new Scanner(text); // stream text
		scan.useDelimiter(" ");
		StringBuilder sbLine = new StringBuilder(); // line buffer
		StringBuilder sbText = new StringBuilder(); // final text
		String padString = padString(pad);
		
		// iterate tokens
		while (scan.hasNext()) {
			String token = scan.next();
			// empty line? add pad.
			if (sbLine.length() == 0) {
				sbLine.append(padString);
			}
			// not enough space for next token + pad? finish this line!
			if ((sbLine.length() + token.length() + pad) > charsPerLine) {
				sbText.append(sbLine.toString() + "\n"); // append line to text
				sbLine.setLength(0); // reset
				sbLine.append(padString);
			}
			// append a (optional) whitespace and the token
			sbLine.append((sbLine.length() > 0 + pad ? " " : "") + token);
		}
		
		scan.close();
		sbText.append(sbLine.toString()); // append last line to text
		return sbText.toString(); // return layouted text	
	}
	
	/**
	 * Returns a sequence of blank spaces of the given length {@code width}.
	 * @param width The padding width / length of blank spaces sequence (must be greater than 0)
	 * @return The resulting blank spaces sequence
	 */
	public static String padString(int width) {
		if (width <= 0)
			throw new IllegalArgumentException("Padding width must be > 0.");
		return width > 0 ? String.format("%" + width + "s", "") : "";
	}
	
}


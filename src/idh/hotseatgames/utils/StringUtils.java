package idh.hotseatgames.utils;

/**
 * Some string- and cli-related utility methods 
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
	 */
	public static void slideInText(String s, int msDelay) {
		if (s == null) return;
		for (String line : s.split("\n")) {
			System.out.println(line);
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

}

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
	
	/**
	 * 
	 * @param text
	 * @param width
	 * @param pad
	 * @return
	 */
	public static String layout(
			String text,
			int width,
			int pad) {
		
		text = text.replaceAll("\n", " "); // replace line breaks w/ whitespaces
		String[] tokens = text.split("\\s+"); // tokenize by whitespaces
		StringBuilder sbLine = new StringBuilder(); // line buffer
		StringBuilder sbText = new StringBuilder(); // final text
		String padString = pad > 0 ? String.format("%" + pad + "s", "") : "";
		
		// iterate tokens
		for (String token : tokens) {
			// empty line? add pad.
			if (sbLine.length() == 0) {
				sbLine.append(padString);
			}
			// not enough space for next token + whitespace + pad? finish line!
			if ((sbLine.length() + token.length() + pad) > width) {
				sbText.append(sbLine.toString()); // append line to text
				sbText.append("\n"); // append line break to text
				sbLine = new StringBuilder(padString); // new, empty line
			}
			// append token and a whitespace
			sbLine.append(token + " ");
		}
		
		sbText.deleteCharAt(sbText.length() - 1); // remove last line break
		return sbText.toString(); // return layouted text
	}
	
	public static void main(String[] args) {
		String text = "Ein Raabe geht im Feld spazieren.\nDa fällt der Weizen"
				+ " um. Ja, so geht mein Lied. Es ergibt keinen Sinn!!!\nAber"
				+ " was soll ich tun? Immerhin brauche ich einen Text zum "
				+ "Testen dieser coolen Methode!\nAlles wird gut....";
		System.out.println(layout(text, 80, 0));
	}

}

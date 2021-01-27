package idh.hotseatgames.utils;

/**
 * Utility to delay program execution
 * 
 * @author bkis
 *
 */
public class Delay {
	
	/**
	 * Delays program execution for a given number of milliseconds.
	 * @param milliseconds int value specifying 
	 * milliseconds to delay program for
	 */
	public static void now(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {}
	}

}

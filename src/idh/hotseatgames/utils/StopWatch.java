package idh.hotseatgames.utils;

/**
 * A very simple stop watch class
 * 
 * @author bkis
 * 
 */
public class StopWatch {
	
	private long startTime;
	
	private StopWatch(){
		startTime = -1;
	}
	
	/**
	 * Returns a new StopWatch instance
	 * @return A new StopWatch instance
	 */
	public static StopWatch instance() {
		return new StopWatch();
	}
	
	/**
	 * Starts this StopWatch instance
	 */
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * Stops this StopWatch instance and returns the time passed since the
	 * call to {@code start()} in milliseconds.
	 * @return The passed time in milliseconds
	 */
	public long stop(){
		long now = System.currentTimeMillis();
		if (startTime < 0)
			throw new IllegalStateException(
					"StopWatch instance was never started.");
		return now - startTime;
	}

}

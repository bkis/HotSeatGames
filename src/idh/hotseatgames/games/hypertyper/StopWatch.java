package idh.hotseatgames.games.hypertyper;

/**
 * A very simple stop watch class
 * 
 * @author bkis
 * 
 */
public class StopWatch {
	
	private long startTime;
	
	public StopWatch(){
		startTime = 0;
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public long stop(){
		return System.currentTimeMillis() - startTime;
	}

}

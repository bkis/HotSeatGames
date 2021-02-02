package idh.hotseatgames.games.oneplusone;

public class Timer {
	
	private long startTime;
	
	public Timer(){
		startTime = 0;
	}
	
	public void start(){
		startTime = System.currentTimeMillis();
	}
	
	public long stop(){
		return System.currentTimeMillis() - startTime;
	}

}

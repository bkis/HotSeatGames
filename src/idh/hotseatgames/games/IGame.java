package idh.hotseatgames.games;

/**
 * This interface defines a minigame to be used with the Hot Seat Games
 * minigame engine.
 * 
 * @author bkis
 * 
 */
public interface IGame {
	
	/**
	 * Starts a session of this game for one player
	 * and returns the points made.
	 * @return int value of the points gained by the player
	 */
	public int startRound(String playerName);
	
	/**
	 * Returns a help/instructional text explaining how this game works
	 */
	public String getInstructions();
	
	/**
	 * Returns a (possibly multi-line) intro banner string for this game
	 */
	public String getIntroBanner();
	
}

package idh.hotseatgames.games;


/**
 * This interface defines a minigame to be used with the Hot Seat Games minigame engine.
 * 
 * @author bkis
 * 
 */
public interface IGame {
	
	/**
	 * Starts a session of this game for one player
	 * and returns the points made. A game should be designed in a way that
	 * the points that can be made don't (drastically) exceed 10 points.
	 * So either make it impossible to get more than 10 points
	 * (e.g. by playing only three rounds worth one point each), or make it very, very hard
	 * to gain more than 10 points!
	 * @return int value of the points gained by the player
	 */
	public int startRound(String playerName);
	
	/**
	 * Returns a help/instructional text explaining how this game works.
	 * The string should be formatted to match a maximum of 80 characters per line!
	 * The preferred (and easiest!) way of implementing this method is to create a text file
	 * {@code instructions.txt} inside the same package as the class implementing IGame
	 * and use the {@code ResourceReader} from the {@code idh.hotseatgames.utils} package:
	 * {@code return ResourceReader.readResource("instructions.txt", getClass());}
	 * @return String containing the instructional text
	 */
	public String getInstructions();
	
	/**
	 * Returns a (possibly multi-line) intro banner string for this game.
	 * The string should be formatted to match a maximum of 80 characters per line!
	 * The preferred (and easiest!) way of implementing this method is to create a text file
	 * {@code intro_banner.ascii} inside the same package as the class implementing IGame
	 * and use the {@code ResourceReader} from the {@code idh.hotseatgames.utils} package:
	 * {@code return ResourceReader.readResource("intro_banner.ascii", getClass());}
	 * @return String containing the intro banner ASCII art
	 */
	public String getIntroBanner();
	
}

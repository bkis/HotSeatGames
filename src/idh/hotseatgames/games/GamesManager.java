package idh.hotseatgames.games;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * This class holds an array of available games to load and provides the engine
 * with the required number of randomly chosen IGame instances at runtime.
 * 
 * @author bkis
 * 
 */
public class GamesManager {
	
	/*
	 * Quite a hacky way of registering the different games:
	 * Just add the games packages to the GAMES_PACKAGE package
	 * and add the package and class (IGame implementation) name
	 * to the static GAMES_NAMES array...
	 */
	private static final String GAMES_PACKAGE = "idh.hotseatgames.games";
	private static final String[] GAMES_SUB_PATHS = {
			"fiftyfifty.FiftyFifty",
			"hypertyper.HyperTyper",
			"rockpaperscissors.RockPaperScissors"
			"blackjack.BlackJack",
	};
	
	private List<Class<?>> randomGames;
	private Random rnd;
	private ClassLoader classLoader;
	private int numberOfGames;
	
	/**
	 * Constructor that takes the number of games to play in this session
	 * @param numberOfGames number of games to play in this session
	 */
	public GamesManager(int numberOfGames) {
		this.numberOfGames = numberOfGames;
		this.classLoader = getClass().getClassLoader();
		this.rnd = new Random(System.currentTimeMillis());
		this.randomGames = new ArrayList<Class<?>>();
		List<String> temp = null;
		String[] gamesClasses = Arrays.stream(GAMES_SUB_PATHS)
				.map(g -> GAMES_PACKAGE + "." + g).toArray(String[]::new);
		
		for (int i = 0; i < numberOfGames; i++) {
			// create temporary games list
			if (temp == null || temp.size() == 0) {
				temp = new ArrayList<String>(Arrays.asList(gamesClasses));
			}
			// load games classes
			try {
				randomGames.add(
						classLoader.loadClass(
								temp.remove(rnd.nextInt(temp.size()))));
			} catch (ClassNotFoundException e) {
				System.err.println("Game class could not be found!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Returns the next game to play in this session
	 * @return IGame instance of next game to play
	 */
	public IGame nextGame() {
		if (randomGames.size() > 0) {
			try {
				return (IGame) randomGames.remove(0)
						.getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Returns the total number of games for this games session
	 * @return
	 */
	public int totalGames() {
		return this.numberOfGames;
	}
	
	/**
	 * Returns the number of games left to play
	 * @return number of games left
	 */
	public int gamesLeft() {
		return randomGames.size();
	}
	
	/**
	 * Returns the number of this game, e.g. 1 for the first game etc.
	 * @return number of this game
	 */
	public int currentGameNumber() {
		return totalGames() - gamesLeft() + 1;
	}

}

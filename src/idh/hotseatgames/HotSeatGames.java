package idh.hotseatgames;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

import idh.hotseatgames.games.GamesManager;
import idh.hotseatgames.games.IGame;
import idh.hotseatgames.utils.Delay;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StringUtils;
import idh.hotseatgames.utils.UserInput;

/**
 * Hot Seat Games - a collection of small, simple, randomly selected CLI
 * minigames that can be played by two or more alternating players at the 
 * same computer.
 * 
 * @author bkis
 *
 */
public class HotSeatGames {
	
	private UserInput input;
	private Player[] players;
	private GamesManager games;
	
	/*
	 * The main method creates an instance of HotSeatGames,
	 * automatically initializing a new playing session.
	 */
	public static void main(String[] args) {
		new HotSeatGames(); // create game session
	}

	private HotSeatGames() {
		StringUtils.cls(); // clear screen
		// print "HOT SEAT GAMES" intro banner
		StringUtils.slideInText(ResourceReader.readResource(
				"intro_banner.ascii", getClass()), // identify resource
				150); // ms delay
		StringUtils.printLineBreaks(1);
		this.input = UserInput.instance(); // create user input wrapper instance
		this.players = createPlayers(); // create players
		this.games = createGames(); // create list of games to play
		this.startGames(); // start games
		this.input.close(); // close user input resources
	}
	
	private void startGames() {
		// iterate over every game planned for this session
		while(games.gamesLeft() > 0) {
			System.out.println("\n\nGAME " + games.currentGameNumber()
					+ " of " + games.totalGames());
			IGame game = games.nextGame(); // get next game
			shufflePlayers(); // shuffle players
			System.out.println("\n");
			StringUtils.slideInText(game.getIntroBanner(), 150); // game intro
			Delay.now(500); // delay
			System.out.println();
			StringUtils.slideInText(game.getInstructions(), 150); // instruct.
			Delay.now(500); // delay
			System.out.println();
			// start game for each player in succession, add won points
			for (Player player : players) {
				input.prompt("> " + player.getName() 
					+ ", press [ENTER] when you're ready!");
				int points = game.startRound(player.getName());
				Delay.now(500);
				StringUtils.printLineBreaks(1);
				System.out.println(player.getName() + 
						" won " + points + " points in total!");
				StringUtils.printLineBreaks(1);
				player.addPoints(points);
				Delay.now(500);
			}
			printPoints(false); // print current points table
			// prompt for ENTER to start next game
			if (games.gamesLeft() > 0) {
				input.prompt("Press [ENTER] to continue with game " 
						+ games.currentGameNumber() + " of " 
						+ games.totalGames() + " ...");
			}
		}
	}
	
	/*
	 * Prints current points table and win message if games are finished
	 */
	private void printPoints(boolean end) {
		sortPlayersByPoints();
		// print points table
		System.out.println("\n============================================");
		System.out.println("RANK\tPLAYER\n");
		for (int i = 0; i < players.length; i++) {
			System.out.print((i + 1) + "\t");
			System.out.println(players[i]);
		}
		System.out.println("============================================\n\n");
		// end of games session?
		if (games.gamesLeft() > 0)
			return;
		// who won?
		// filter winners
		Player[] winners = Arrays.asList(players)
			.stream()
			.filter(p -> p.getPoints() == players[0].getPoints())
			.collect(Collectors.toList()).toArray(Player[]::new);
		// print CONGRATS banner
		StringUtils.slideInText(ResourceReader.readResource(
				"winner_screen.ascii", getClass()), 150);
		// print winner(s)
		StringUtils.printLineBreaks(1);
		System.out.println("   THE WINNER" 
				+ (winners.length == 1 ? " IS" : "S ARE") + ": " 
				+ String.join(", ", Arrays.stream(winners)
						.map(w -> w.toString()).toArray(String[]::new)));
		StringUtils.printLineBreaks(1);
	}
	
	/*
	 * Fills the players array with new player objects by
	 * asking for number of players and player names
	 */
	private Player[] createPlayers() {
		// ask for number of players
		int playerCount = input.prompt(
				"> Number of players [2-?]: ", 2, 999);
		// create players array
		Player[] p = new Player[playerCount]; 
		// create individual players
		for (int i = 0; i < p.length; i++) {
			p[i] = createPlayer(i + 1);
		}
		return p;
	}
	
	/*
	 * Creates new player object with passed player number
	 */
	private Player createPlayer(int playerNumber) {
		// ask for player name
		String name = input.prompt(
				"> Player #" + playerNumber + " name:",
				"[\\wüöäÜÖÄ ]+");
		// create and return new player object
		return new Player(name, playerNumber);
	}
	
	/*
	 * Creates games manager instance for this session (provides random games)
	 */
	private GamesManager createGames() {
		// ask for number of games to play
		int gamesCount = input.prompt(
				"> Number of games to play [1-?]: ", 1, 999);
		// create and return new games manager instance
		return new GamesManager(gamesCount);
	}
	
	/*
	 * (Pseudo-)randomly shuffles players array
	 */
	private void shufflePlayers() {
		Collections.shuffle(Arrays.asList(this.players));
	}
	
	/*
	 * Sorts players array by points
	 */
	private void sortPlayersByPoints() {
		Collections.sort(Arrays.asList(players), new Comparator<Player>() {
			@Override
			public int compare(Player o1, Player o2) {
				return o2.getPoints() - o1.getPoints();
			}
			
		});
	}

}

package idh.hotseatgames.games.fiftyfifty;

import idh.hotseatgames.games.IGame;
import idh.hotseatgames.utils.Delay;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.UserInput;

/**
 * Just a primitive placeholder game for testing purposes
 * 
 * @author bkis
 *
 */
public class FiftyFifty implements IGame {
	
	private UserInput input;

	@Override
	public String getInstructions() {
		return "The player chooses between 0 and 1.\n"
				+ "The game does, too.\n"
				+ "If both pick the same, the player gets a point.\n"
				+ "Each player has 3 turns in a row.";
	}

	@Override
	public int startRound(String playerName) {
		input = UserInput.instance();
		int points = 0;
		
		for (int i = 1; i <= 3; i++) {
			System.out.println("\n[ ROUND " + i 
					+ " for player " + playerName + " ]");
			int playerPick = input.prompt("> Choose between 0 and 1:", 0, 1);
			int gamePick = (int) Math.round(Math.random());
			if (playerPick == gamePick) {
				points++;
				System.out.println("CORRECT! You got 1 point :)");
			} else {
				System.out.println("WRONG! No point :(");
			}
			Delay.now(1000);
		}
		
		return points;
	}

	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("intro_banner.ascii", getClass());
	}

}

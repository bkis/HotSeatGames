package idh.hotseatgames.games.binory;

import java.util.Arrays;
import java.util.Random;

import idh.hotseatgames.IGame;
import idh.hotseatgames.utils.Delay;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StringUtils;
import idh.hotseatgames.utils.UserInput;

/**
 * A Minigame about memorizing binary sequences of increasing length
 * 
 * @author bkis
 *
 */
public class Binory implements IGame {
	
	@Override
	public int startRound(String playerName) {
		UserInput input = UserInput.instance();
		Random rnd = new Random(System.currentTimeMillis()); // RNG
		int initSeqLength = 5; // initial sequence length
		int roundNo = 1; // current round number
		int points = 0; // player's initial points
		
		while (true) {
			StringUtils.printLineBreaks(1);
			input.prompt(" > " + playerName + ", press [ENTER] to memorize "
					+ "sequence number " + roundNo 
					+ " (you've got 5 seconds) ...");
			StringUtils.printLineBreaks(1);
			// generate binary sequence
			String seq = String.join("",
					Arrays.stream(new int[initSeqLength + roundNo - 1])
					.map(n -> rnd.nextInt(2)).mapToObj(String::valueOf)
					.toArray(String[]::new));
			StringUtils.printLineBreaks(1);
			// display sequence
			System.out.println("[ " + seq + " ]");
			Delay.now(5000); // wait for 5 seconds
			StringUtils.cls(); // (fake-) clear console
			// prompt for the sequence
			String guess = input.prompt("Enter the sequence now: ", "[01]+");
			StringUtils.printLineBreaks(1);
			// result
			if (!guess.equals(seq)) {
				System.out.println("INCORRECT SEQUENCE!");
				System.out.println("original: " + seq);
				System.out.println("entered:  " + guess);
				break;
			} else {
				System.out.println("CORRECT SEQUENCE!");
				System.out.println(playerName + " gets 1 point!");
				points++;
			}
			roundNo++;
		}
		
		return points;
	}

	@Override
	public String getInstructions() {
		return ResourceReader.readResource("instructions.txt", getClass());
	}

	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("intro_banner.ascii", getClass());
	}

}

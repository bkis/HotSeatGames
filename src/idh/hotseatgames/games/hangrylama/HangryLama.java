package idh.hotseatgames.games.hangrylama;

import java.util.Random;

import idh.hotseatgames.games.IGame;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StopWatch;
import idh.hotseatgames.utils.UserInput;

public class HangryLama implements IGame {

	private static int MILLISECONDS_TO_FEED = 3000;
	private StopWatch timer;

	@Override
	public String getInstructions() {
		return ResourceReader.readResource("instructions.txt", getClass());
	}

	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("asciiart.txt", getClass());
	}

	@Override
	public int startRound(String playerName) {
		int score = 0;
		UserInput input = UserInput.instance();
		this.timer = StopWatch.instance();
		int round = 1;

		String[] letters = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
				"S", "T", "U", "V", "W", "X", "Y", "Z" };
		int[] letterValue = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24 };
		String chosenLetter = "";

		int points = 0;
		Random randomLetter = new Random();

		System.out.println();
		System.out.println(playerName + ", feed the lama with letters! "
				+ "(case doesn't matter)\nThe Lama is hangry so feed it with the letters within a maxumum of "
				+ (MILLISECONDS_TO_FEED / 1000) + " seconds!");
		input.prompt("Round " + round + ": Press [ENTER] to start!");
		System.out.println();
		
		for (int i = 0; i <= 7; i++) {

		int indexLetter = randomLetter.nextInt(24);
		chosenLetter += letters[indexLetter];
		points = points + letterValue[indexLetter];

			System.out.println("The Lama is hangry and wants: " + chosenLetter);

			String playerInput;
			double time;

			timer.start();
			System.out.println();
			playerInput = input.prompt("Your Input: ");
			System.out.println();
			time = timer.stop();

			boolean correct = chosenLetter.equalsIgnoreCase(playerInput);
			boolean inTime = time <= MILLISECONDS_TO_FEED;

			if (correct && inTime) {
				System.out.println("You fed the Lama within " + time + " seconds! You got 1 point :)");
				points++;
				score++;
				round++;
			} else {
				System.out.println("You got bitten by the lama! No point :(");
				round++;
				i = i + 7;
			}
		}
		return score;
	}
}

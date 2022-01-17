package idh.hotseatgames.games.hangryllama;

import java.util.Random;

import idh.hotseatgames.IGame;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StopWatch;
import idh.hotseatgames.utils.UserInput;


public class HangryLlama implements IGame {

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

		char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String chosenLetters = "";

		Random rnd = new Random();

		System.out.println();
		System.out.println(playerName + ", feed the llama with letters! (case doesn't matter)\n"
				+ "The llama is hangry so feed it with the letters within a maximum of "
				+ (MILLISECONDS_TO_FEED / 1000) + " seconds!");
		input.prompt("Press [ENTER] to start!");
		System.out.println();
		
		for (int i = 0; i <= 7; i++) {
			chosenLetters += letters[rnd.nextInt(24)];
			System.out.println(chosenLetters + " is what the hangry llama wants!\n");

			timer.start();
			String playerInput = input.prompt("Feed the llama: ");
			double time = timer.stop();
			
			System.out.println();

			if (chosenLetters.equalsIgnoreCase(playerInput) && time <= MILLISECONDS_TO_FEED) {
				System.out.println("You fed the llama within " + time + " seconds! You got 1 point :)");
				score++;
			} else {
				System.out.println("You got bitten by the llama! No point :(");
				break;
			}
		}
		
		return score;
	}
}

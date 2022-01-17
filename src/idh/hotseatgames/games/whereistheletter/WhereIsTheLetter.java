package idh.hotseatgames.games.whereistheletter;

import java.util.Random;

import idh.hotseatgames.games.IGame;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StopWatch;
import idh.hotseatgames.utils.StringUtils;
import idh.hotseatgames.utils.UserInput;

/**
 * A minigame about finding a letter that hides among random symbols
 * 
 * @author github.com/MilchStrassenBoi
 *
 */
public class WhereIsTheLetter implements IGame {

	private char[] letterCollection = "ABCDEFGHJKLMNOPQRSTUVWXYZ".toCharArray();
	private char[] symbolCollection = "!#%&/?=)({}|<>1234567890=*+-:".toCharArray();
	private StopWatch timer;

	@Override
	public int startRound(String playerName) {

		int rows = 3;
		int lenghtOfRows = 40;
		int round = 1;
		int points = 0;
		long neededTime;
		String resultText;
		Random rnd = new Random();

		this.timer = StopWatch.instance();
		UserInput input = UserInput.instance();

		// Text Intro
		StringUtils.printLineBreaks(1);
		String intro = playerName + ", find and enter the letter!!\n\n"
				+ "Search time and points: \n"
				+ "less than  5 seconds\t\t2 Points\n"
				+ "less than 15 seconds\t\t1 Point\n"
				+ "more than 15 sec\t\t0 Points\n\n";

		StringUtils.slideInText(intro, 50, 0);
		StringUtils.printLineBreaks(1);
		input.prompt("Press [ENTER] to start!");
		StringUtils.cls();
		StringUtils.printLineBreaks(1);

		// the actual Game
		while (round <= 5) {
			System.out.println("Round " + round + " of 5:");

			// get a random letter from the collection
			char hiddenLetter = letterCollection[rnd.nextInt(letterCollection.length)];
			int hidingSpot = (int) (Math.random() * rows * lenghtOfRows);

			for (int i = 0; i < rows * lenghtOfRows; i++) {

				if (i % lenghtOfRows == 0) {
					System.out.println();
				}
				
				if (i == hidingSpot) {
					System.out.print(hiddenLetter);
				} else {
					System.out.print(symbolCollection[(int) (Math.random() * (symbolCollection.length - 1))]);
				}
			}

			StringUtils.printLineBreaks(2);

			timer.start();
			String guess = input.prompt("Enter the hidden letter:").replaceAll("\\s+", "");
			neededTime = timer.stop() / 1000l;

			// check if its correct and in time
			if (guess == "" || guess.toUpperCase().charAt(0) != hiddenLetter) {
				resultText = "That is the wrong letter.";
			} else {
				resultText = "You found the right letter in " + neededTime + " secounds.\n";
				if (neededTime <= 5) {
					resultText += "You get 2 Points.";
					points += 2;
				} else if (neededTime > 5 && neededTime <= 15) {
					resultText += "You get 1 Point.";
					points++;
				} else {
					resultText += "That was too slow - you get 0 Points.";
				}
			}

			StringUtils.slideInText(resultText, 50, 0);
			StringUtils.printLineBreaks(1);

			if (round < 5) {
				input.prompt("Press [ENTER] to start the next round!");
				StringUtils.cls();
			}
			
			round++;
			rows++;
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

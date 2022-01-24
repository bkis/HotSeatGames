package idh.hotseatgames.games.roulette;

import java.util.Random;

import idh.hotseatgames.IGame;
import idh.hotseatgames.utils.Delay;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StringUtils;
import idh.hotseatgames.utils.UserInput;


public class Roulette implements IGame {
	
	private static final NumberGroup[] NUMBER_GROUPS = {
			new NumberGroup("Black",
					2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35),
			new NumberGroup("Red",
					1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36),
			new NumberGroup("Evens",
					2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36),
			new NumberGroup("Odds",
					1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35),
			new NumberGroup("Manque",
					1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18),
			new NumberGroup("Passe",
					19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36),
	};
	
	private Random rnd;
	private String betsOverview;
	
	
	@Override
	public int startRound(String playerName) {
		this.rnd = new Random();
		this.betsOverview = getBetsOverview();
		int[] input = getBet();
		return evaluateBets(input);
	}
	
	
	private int[] getBet() {
		UserInput input = UserInput.instance();
		StringUtils.printLineBreaks(1);
		StringUtils.slideInText(betsOverview, 100, 4);
		StringUtils.printLineBreaks(1);
		System.out.println("You can now bet up to five tokens.");
		StringUtils.printLineBreaks(1);
		
		int[] bet = new int[5];
		
		for (int i = 0; i < bet.length; i++) {
			bet[i] = input.prompt("Token " + (i+1) + " (0..42): ", 0, 42);
		}
		
		return bet;
	}
	
	
	private NumberGroup getWinConditions(int betNumber) {
		if (betNumber <= 36) {
			return new NumberGroup(String.valueOf(betNumber), betNumber);
		} else {
			return NUMBER_GROUPS[betNumber - 36 - 1];
		}
	}
	
	
	private int evaluateBets(int[] bets) {
		int totalPoints = 0;
		int rolledNumber = rnd.nextInt(37);
		
		System.out.println(String.format("\nNumber rolled: %d\n", rolledNumber));
		
		for (int betNumber : bets) {
			Delay.now(1000);
			NumberGroup winConditions = getWinConditions(betNumber);
			boolean win = false;
			int points = 0;
			
			for (int condition : winConditions.numbers) {
				if (rolledNumber == condition) {
					win = true;
					break;
				}
			}
			
			if (win) {
				points = 36 / winConditions.numbers.length;				
			}
			
			System.out.println(String.format(
					"%s%s%s    %d points",
					winConditions.name,
					StringUtils.padString(12 - winConditions.name.length()),
					win ? "HIT!" : "    ",
					win ? points : 0));
			
			totalPoints += points;
		}

		return totalPoints;
	}
	
	
	public String getBetsOverview() {
		return ResourceReader.readResource("bets.txt", getClass());
	}


	@Override
	public String getInstructions() {
		return ResourceReader.readResource("instructions.txt", getClass());
	}

	
	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("intro_banner.ascii", getClass());
	}
	
	
	private static class NumberGroup {
		String name;
		int[] numbers;
		
		public NumberGroup(String name, int... numbers) {
			this.name = name;
			this.numbers = numbers;
		}
	}
	
}

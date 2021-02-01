package idh.hotseatgames.games.rockpaperscissors;

import idh.hotseatgames.games.IGame;
import idh.hotseatgames.utils.Delay;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StringUtils;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors implements IGame {
		
	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("intro_banner.ascii", getClass());
	}
	
	@Override
	public String getInstructions() {
		return "Rock wins against scissors.\n"
			 + "Scissors win against paper.\n"
			 + "Paper wins against rock.\n";
	}
	
	@Override
	public int startRound(String playerName) {
		
		int points = 0;
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		for (int i = 1; i <= 3; i++) {
			
			System.out.println("\n[ ROUND " + i 
					+ " for player " + playerName + " ]");
			
			String input;
			char playerPick;
			
			// check that user input is of length 1 and
			//corresponds to option R, P or S
			do {
				System.out.print("> Pick your weapon:\n\n"
						+ "  Rock:     [R]\n"
						+ "  Paper:    [P]\n"
						+ "  Scissors: [S]\n>");
				input = scanner.nextLine();
			} while ( (input.length() > 1 )
			       || (input.length() < 1 )
				   || (Character.toUpperCase(input.charAt(0)) != 'R') 
				   && (Character.toUpperCase(input.charAt(0)) != 'P')
				   && (Character.toUpperCase(input.charAt(0)) != 'S'));
		
			// convert input to uppercase char
		    playerPick = Character.toUpperCase(input.charAt(0));
			
			// generate random char
			String rps = "RPS";
			Random rand = new Random();
			char gamePick = rps.charAt(rand.nextInt(rps.length()));

			// concatenate player and game pick to generate filename of corresponding ascii image
			String filename = String.valueOf(playerPick) + String.valueOf(gamePick).concat(".ascii");
			
			// display ascii images of player and game pick
			StringUtils.slideInText(ResourceReader.readResource(filename, getClass()), 150);			
			System.out.println();
			Delay.now(500);
			
			if (playerPick == gamePick) {
				System.out.println("The game is a draw");
			} else if (    (playerPick == 'R' && gamePick == 'S') 
					    || (playerPick == 'S' && gamePick == 'P')
					    || (playerPick == 'P' && gamePick == 'R') ) {
				points++;				
				System.out.println("You win! You got 1 point :)");
			} else {
				System.out.println("You lose! No point :(");
			}
			Delay.now(1000);
		}
		return points;
	}
}

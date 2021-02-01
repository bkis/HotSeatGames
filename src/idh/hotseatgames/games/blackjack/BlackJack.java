package idh.hotseatgames.games.blackjack;

import idh.hotseatgames.games.IGame;
import idh.hotseatgames.utils.Delay;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.StringUtils;
import idh.hotseatgames.utils.UserInput;


public class BlackJack implements IGame {

	private UserInput input;
	PackOfCards packofCards = new PackOfCards();
	
	
	/**
	 * Simulates round of the player. 
	 * @param playerName
	 * @return returns scored points of the Player
	 */
	private int roundPlayer(String playerName) {

		int points = 0; //Storage for gained points
		
		System.out.println("____________________________________");
		StringUtils.printLineBreaks(2);
		System.out.println(playerName + " starts. Dealer hands out: ");
		Delay.now(1500);
		StringUtils.printLineBreaks(3);

		points = packofCards.drawCard(2); //Draws two cards and adds values to 'points'
		
		// As long as the points are under 21 the player decides wether he draws or not 
		while (points < 21) {
			
			int drawOrNot = (int) input.prompt(
					playerName + ", you got " + points + " points.\n" 
					+ "Do you want to draw another card?\n"
					+ "[1: yes, 0: no]\n > ", 0, 1);
			
			if (drawOrNot == 1) {
				Delay.now(1000);
				StringUtils.printLineBreaks(1);
				points += packofCards.drawCard(1);
				StringUtils.printLineBreaks(1);
			} else {
				packofCards.getCardsBack(); //Delete drawn cards
				return points;
			}
		}
		
		packofCards.getCardsBack();
		return points; 
	}
	
	/**
	 * Simulates round of the dealer.
	 * @return returns scored points of the dealer
	 */
	private int roundDealer( ) {
		
		int points = 0;
		
		System.out.println("____________________________________");
		StringUtils.printLineBreaks(2);
		System.out.println("The Dealer plays:");
		Delay.now(1500);
		StringUtils.printLineBreaks(3);
		
		points += packofCards.drawCard(2); //Draws 2 cards
		
		//has to draw more cards while he got less than 16 points 
		while (points <= 16) {
			System.out.println("The Dealer has to draw another card.\n");
			Delay.now(1000);
			points += packofCards.drawCard(1);
		}
		
		packofCards.getCardsBack();
		return points; 
	}
	
	
	@Override
	public String getInstructions() {
		return "The player plays Black Jack against the dealer. "
				+ "The player goes first.\n"
				+ "They get 2 open cards and have to decide whether they draw or not.\n"
				+ "The goal is to get as close as possible to a total of 21 points,\n"
				+ "but if they get more than 21 points, they instantly lose.\n"
				+ "Ace is 11 points, King, Queen and Jack are 10 points.\n"
				+ "The other cards' values are as indicated on the card.\n"
				+ "A win is worth 5 points, a draw is worth 3 points!";
	}

	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("intro_banner.ascii", getClass());
	}
	
	@Override
	public int startRound(String playerName) {
		
		input = UserInput.instance();

		//Simulation of the players round
		int pointsPlayer = roundPlayer(playerName); 
		System.out.println(playerName + " scored " + pointsPlayer + " points.");
		
		//If the player scored more than 21 points, he loses instantly 
		if (pointsPlayer > 21)  {
			System.out.println("That's over 21! The dealer wins!");
			return 0; 
			
		} else {
			
			//Simulation of the dealers round 
			int pointsDealer = roundDealer();
			System.out.println("The Dealer scored " + pointsDealer + " points.");
			
			//Conditions to determine the outcome of the round
			if (pointsDealer > 21) {
				System.out.println("Thats over 21! " + playerName + " wins!");
				return 5; 
			}
			if (pointsPlayer == 21 && pointsDealer != 21) {
				System.out.println("Only " + playerName + " scored 21 points and wins the round!");
				return 5; 
			}
			if (pointsPlayer == 21 && pointsDealer == 21) {
				System.out.println("Both scored 21 points. It's a draw!");
				return 3; 
			}
			if (pointsPlayer != 21 && pointsDealer == 21) {
				System.out.println("Only the dealer scored 21 points and wins the round!");
				return 0; 
			}
			if (pointsDealer < 21 && pointsPlayer < 21 && pointsDealer > pointsPlayer) {
				System.out.println("The dealer is closer to 21 points and wins the round!");
				return 0; 
			}
			if (pointsDealer < 21 && pointsPlayer < 21 && pointsDealer < pointsPlayer) {
				System.out.println(playerName + " is closer to 21 points and wins the round!");
				return 5; 
			} else {
				System.out.println("Both got " + pointsPlayer + ". It's a draw!");
				return 3;
			}
		}
	}

	
}

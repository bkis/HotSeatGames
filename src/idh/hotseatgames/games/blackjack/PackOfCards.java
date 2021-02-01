package idh.hotseatgames.games.blackjack;

import java.util.Random;

public class PackOfCards {

	//Arrays für Darstellung und Werte der einzelnen Karten
	private String[] packOfCards = {"|2|", "|3|", "|4|", "|5|", "|6|", "|7|", "|8|", "|9|", "|10|", "|J|", "|Q|", "|K|", "|A|"};
	private int[] valueOfCards = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
	private String drawnCards = ""; 
	
	
	/**
	 * Draws random cards. 
	 * @param quantity of drawn cards 
	 * @return Returns total points of drawn cards
	 */
	public int drawCard(int quantity) {

		int points = 0; 
		Random randomCard = new Random();
		
		for (int i = quantity; i > 0; i--) {
			int indexCard = randomCard.nextInt(13);
			drawnCards += packOfCards[indexCard] + "   ";
			points = points + valueOfCards[indexCard];
		}
		System.out.println(drawnCards + "\n");
		
		return points;
	}
	
	/**
	 * Deletes the drawn cards. 
	 */
	public void getCardsBack() {
		drawnCards = "";
	}
}

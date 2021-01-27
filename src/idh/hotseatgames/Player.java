package idh.hotseatgames;

/**
 * Player POJO class
 * 
 * @author bkis
 *
 */
public class Player {
	
	private String name;
	private int playerNumber;
	private int points;
	
	public Player(String name, int playerNumber) {
		super();
		this.name = name;
		this.playerNumber = playerNumber;
	}

	public String getName() {
		return this.name;
	}
	
	public int getPlayerNumber() {
		return this.playerNumber;
	}

	public int getPoints() {
		return this.points;
	}
	
	public void addPoints(int points) {
		this.points += points;
	}
	
	@Override
	public String toString() {
		return this.name + " (" + points + " points)";
	}

}

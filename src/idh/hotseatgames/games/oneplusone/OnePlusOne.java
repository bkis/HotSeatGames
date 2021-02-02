package idh.hotseatgames.games.oneplusone;

import idh.hotseatgames.games.IGame;
import idh.hotseatgames.games.hypertyper.Timer;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.UserInput;

public class OnePlusOne implements IGame{
	
	private static int MILLISECONDS_PER_WORD = 5000;
	
	private int nextTaskIndex;
	private Timer timer;

	@Override
	public int startRound(String playerName) {
		this.nextTaskIndex = 0;
		this.timer = new Timer();
		UserInput input = UserInput.instance();
		double time;
		int points = 0;
		
		for(int i = nextTaskIndex; i < 10; i++) {
			int random1 = (int) (Math.random() * 10);
			int random2 = (int) (Math.random() * 10);
			String result = "";
			String typed = "";
			
			timer.start();
			if(i <= 3) {
				typed = input.prompt(random1 + " + " + random2 + " = ");
				result = random1 + random2 + "";
			} else if (i <= 7) {
				typed = input.prompt(random1 + " - " + random2 + " = ");
				result = random1 - random2 + "";
			} else if (i <= 10) {
				typed = input.prompt(random1 + " x " + random2 + " = ");
				result = random1 * random2 + "";
			} 
			
			time = timer.stop();
			boolean inTime = time <= MILLISECONDS_PER_WORD;
			
			if(result.equals(typed) && inTime) {
				points++;
				System.out.println("CORRECT! " + (time/1000) 
						+ " seconds! Points: " + points + " :)");
			} else {
				if (!result.equals(typed)) {
					System.out.println("WRONG! The answer should be " + result + " :(");
				}
				if (!inTime) {
					System.out.println("TOO SLOW! " + (time/1000) 
							+ " seconds :(");
				}
			}

		}
		return points;
	}

	@Override
	public String getInstructions() {
		return "The player has to solve the given math problem and\n" 
				+ "type in the correct answer in under 5 seconds per\n"
				+ "task. The player gets 1 point per correctly solved\n"
				+ "task.";
	}

	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("intro_banner.ascii.txt", getClass());
	}

}

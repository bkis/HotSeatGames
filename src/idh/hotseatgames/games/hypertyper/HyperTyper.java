package idh.hotseatgames.games.hypertyper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import idh.hotseatgames.games.IGame;
import idh.hotseatgames.utils.ResourceReader;
import idh.hotseatgames.utils.UserInput;

/**
 * A minigame about typing speed and precision.
 * 
 * @author bkis
 *
 */
public class HyperTyper implements IGame {
	
	private static int MILLISECONDS_PER_WORD = 5000;
	private static int MAX_NUMBER_OF_WORDS = 10;
	
	private int nextWordIndex;
	private String[] words;
	private StopWatch timer;
	
	
	@Override
	public int startRound(String playerName) {
		this.nextWordIndex = 0;
		this.timer = new StopWatch();
		this.words = getWords(MAX_NUMBER_OF_WORDS);
		UserInput input = UserInput.instance();
		double time;
		int score = 0;
		
		System.out.println();
		System.out.println(playerName + ", type the following words correctly "
				+ "(case doesn't matter)\nand finish each word in a maxumum of " 
				+ (MILLISECONDS_PER_WORD / 1000) + " seconds!");
		input.prompt("Press [ENTER] to start!");
		System.out.println();
		
		while(nextWordIndex < words.length){
			timer.start(); // start timer
			// promt to type word
			String typed = input.prompt(words[nextWordIndex] + " > ");
			time = timer.stop(); // stop timer
			// check typed word
			boolean correct = words[nextWordIndex++].equalsIgnoreCase(typed);
			boolean inTime = time <= MILLISECONDS_PER_WORD;
			if (correct && inTime){
				score++;
				System.out.println("NICE! " + (time/1000) 
						+ " seconds! Points: " + score);
			} else {
				if (!correct) {
					System.out.println("WRONG! You got a typo!");
				}
				if (!inTime) {
					System.out.println("TOO SLOW! " + (time/1000) 
							+ " seconds :(");
				}
				System.out.println("\nGAME OVER!");
				break;
			}
			System.out.println();
		}
		
		return score;
	}
	
	private String[] getWords(int nrOfWords){
		String[] randomWords = new String[nrOfWords];
		Random rnd = new Random(System.currentTimeMillis());
		String[] wordPool = ResourceReader.readResource(
				"words.txt",
				getClass())
				.toUpperCase().trim().split("\\P{L}+");
		Arrays.sort(wordPool, getWordLengthComparator());
		int min = 0, max = 0;
		
		for (int i = 0; i < randomWords.length; i++) {
			min = (wordPool.length / nrOfWords) * i;
			max = min + (wordPool.length / nrOfWords);
			randomWords[i] = wordPool[rnd.nextInt(max - min) + min];
		}
		
		Arrays.sort(randomWords, getWordLengthComparator());
		return randomWords;
	}
	
	private static Comparator<String> getWordLengthComparator(){
		return new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};
	}

	@Override
	public String getInstructions() {
		return "The player has to correctly type up to ten words shown to them\n"
				+ "in under 5 seconds per word. The words become longer and more\n"
				+ "complicated over time. The player gets one point per correctly\n"
				+ "typed word, but a mistake or an exceeding of the five seconds\n"
				+ "limit mean an immediate 'GAME OVER'!";
	}

	@Override
	public String getIntroBanner() {
		return ResourceReader.readResource("intro_banner.ascii", getClass());
	}

}

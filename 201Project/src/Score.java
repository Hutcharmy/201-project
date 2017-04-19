import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Score {
	
	//Pre-assigned number of difficulties, created in case change is needed
	private static final int NUM_OF_DIFFICULTY = 3;
	private static final int MAX_SCORE = 99;
	private static final String SAVE_PATH = "save.txt";
		
	/**
	 * Reset all scores in save file
	 */
	public static void resetScores(){
		for(int i = 0; i < 6; i++){
			writeNewScore(i, 0);
		}
	}
	
	/**
	 * Check save file exists, if not create save file
	 */
	public static void checkSaveFileExists(){
		if(!Files.exists(Paths.get(SAVE_PATH))){
			try {
				Files.createFile(Paths.get(SAVE_PATH));
				try {
					PrintWriter writer = new PrintWriter(SAVE_PATH);
					for(int i = 0; i < NUM_OF_DIFFICULTY * 2; i++){
						writer.println("0");
					}
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/** 
	 * Get array of ints containing all current scores
	 * @return the current scores in an integer array
	 */
	public static int[] scoreArray(){
		int[] returnArray = new int[NUM_OF_DIFFICULTY * 2];
		int counter = 0;
		
		for(int i = 0; i < NUM_OF_DIFFICULTY; i++){
			returnArray[counter] = getScore(i, true);
			counter++;
			returnArray[counter] = getScore(i, false);
			counter++;
		}
	
		return returnArray;
	}

	/**
	 * Increments a given score based on given difficulty and if win/loss
	 * @param difficulty the current AI difficulty
	 * @param isWin whether the user won or not, true if user win
	 */
	public static void incScore(int difficulty, boolean isWin){
		String tempScore = "";
		int counter = 0;
		
		// Check to ensure given difficulty is not out of range
		if(difficulty < 0 || difficulty > NUM_OF_DIFFICULTY){
			System.err.println("difficulty not in range!");
			System.err.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
			return;
		}
		
		for(int i = 0; i < NUM_OF_DIFFICULTY; i++){
			if(difficulty == i && isWin){
				tempScore = readFromFile(counter);
				// Check if score has reached MAX_SCORE
				if(Integer.parseInt(tempScore) >= MAX_SCORE){
					System.err.println("Max score reached!");
					return;
				}
				writeNewScore(counter, Integer.parseInt(tempScore)+1);
			}
			counter++;
			
			if(difficulty == i && !isWin){
				tempScore = readFromFile(counter);
				// Check if score has reached MAX_SCORE
				if(Integer.parseInt(tempScore) >= MAX_SCORE){
					System.err.println("Max score reached!");
					return;
				}
				writeNewScore(counter, Integer.parseInt(tempScore)+1);
			}
			counter++;
		}
		
	}
	
	/**
	 * Gets a score based on given difficulty and if win/loss
	 * @param difficulty the current AI Difficulty
	 * @param isWin whether we want a user score or an AI score
	 * @return the score for the given line
	 */
	public static int getScore(int difficulty, boolean isWin){
		int toReturn = -1;
		int counter = 0;
		
		if(difficulty < 0 || difficulty > NUM_OF_DIFFICULTY){
			System.err.println("difficulty not in range!");
			System.err.println(Thread.currentThread().getStackTrace()[2].getLineNumber());
			return toReturn;
		}
		
		for(int i = 0; i < NUM_OF_DIFFICULTY; i++){
			if(difficulty == i && isWin){
				toReturn = Integer.parseInt(readFromFile(counter));
			}
			counter++;
			if(difficulty == i && !isWin){
				toReturn = Integer.parseInt(readFromFile(counter));
			}
			counter++;
		}

		return toReturn;
	}

	/**
	 * Private access directly read from file
	 * @param lineNumber the line to read from
	 * @return the text on the line indicated by lineNumber
	 */
	private static String readFromFile(int lineNumber){
		String numToReturn = "";
		try (Stream<String> lines = Files.lines(Paths.get(SAVE_PATH))) {
		    numToReturn = lines.skip(lineNumber).findFirst().get();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		return numToReturn;
	}
	
	/**
	 * Private access to write directly from file
	 * @param lineNumber the line to write to
	 * @param newScore the score to write on the given line
	 */
	private static void writeNewScore(int lineNumber, int newScore){
		int[] scoreArray = new int[6];
		
		for(int i = 0; i <6; i++){
			scoreArray[i] = Integer.parseInt(readFromFile(i));
		}
		scoreArray[lineNumber] = newScore;
		
		try {
			PrintWriter writer = new PrintWriter(SAVE_PATH);
			for(int i = 0; i < 6; i++){
				writer.println(scoreArray[i]);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		

	}	
}

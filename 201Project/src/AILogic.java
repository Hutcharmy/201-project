import java.util.Arrays;
import java.util.Random;

/**
 * Makes moves for the AI based on the difficulty selected by the user and the current state of the board
 */
public class AILogic {
	private enum AIDifficulties {
		EASY, MEDIUM, HARD
	}

	private AIDifficulties diff;

	/**
	 * Creates the instance of the AI and sets difficulty
	 * @param difficulty the current difficulty the user will play against
	 */
	public AILogic(int difficulty) {
		// Sets difficulty of AI
		if (difficulty == 0) {
			diff = AIDifficulties.EASY;
		} else if (difficulty == 1) {
			diff = AIDifficulties.MEDIUM;
		} else if (difficulty == 2) {
			diff = AIDifficulties.HARD;
		} else {
			throw new ConnectFourException("Invalid difficulty");
		}
	}

	/**
	 * Gets the possible moves, then orders them and finds the move to return to the game given the best moves and
	 * what difficulty we are on
	 * @param grid the current board state
	 * @return the piece to be added to the board by the AI
	 */
	public Piece makeAMove(Board grid) {
		// create rng for difficulty settings
		Random r = new Random();
		double actualRNG = r.nextDouble();
		//Constants developed to determine how likely AI is to pick good moves
		final double easyC = .4, mediumC = .7, hardC = .98;
		grid.updateBottomRow();
		// create the fake piece that will be returned.
		Piece p = new Piece(0, 0, false);
		// get the board so the AI can be aware of what is around it.
		Piece[][] allPieces = grid.getBoard();
		// get the coordinates of all the possible moves.
		int possX[] = grid.getBottomRows();
		// this checks to make sure the limit of the row is not exceeded.
		for (int x = 0; x < 6; x++) {
			if (allPieces[x] != null && allPieces[x].length == 6) {
				possX[x] = -1;
			}
		}
		// create all the hypothetical moves that are allowable on the board.
		Piece[] possibleMoves = new Piece[7];
		for (int x = 0; x < 7; x++) {
			if (possX[x] != -1 && possX[x] < 6) {
				possibleMoves[x] = new Piece(x, possX[x], false);
			} else {
				possibleMoves[x] = null;
			}
		}
		// now order the moves in the correct order so that the AI knows which
		// is best.
		Piece bestMoves[] = orderBestMoves(possibleMoves, allPieces);
		boolean placed=false;
		//iterates in case move selection is going to result in user win
		while(!placed){
			//Easy difficulty
			if (diff == AIDifficulties.EASY) {
				if (bestMoves.length > 1) {//More than one move
					if (actualRNG < easyC) {//Determines if ai should make a good move
						if (!(bestMoves[0].getPres() == 5 || bestMoves[0].getPres() == 10)) {
							p = bestMoves[0];
						} else {//case that all AI moves won't build towards win or block
							p = bestMoves[(int) (r.nextDouble() * bestMoves.length)];
						}
					} else {//Randomly generated bad move
						p = bestMoves[1];
						
					}
				} else {//AI has only 1 possible move
					p = bestMoves[0];
					
				}
				placed=true;//Easy doesn't check if it is giving the user a win
			}
			//Medium difficulty
			else if (diff == AIDifficulties.MEDIUM) {
				if (bestMoves.length > 1) {//More than one move
					if (actualRNG < mediumC) {//Determines if ai should make a good move
						if (!(bestMoves[0].getPres() == 5 || bestMoves[0].getPres() == 10)) {
							p = bestMoves[0];
							
						} else {//case that all AI moves won't build towards win or block
							p = bestMoves[(int) (r.nextDouble() * bestMoves.length)];
						}
						if(p.getY()+1<6){//checks range to avoid indexing error
							//Checks that next AI placement won't allow user to win by building on top of it
							if(!possibleBlock(new Piece(p.getX(),p.getY()+1,false),allPieces)){
								placed=true;
							}
							else{
								bestMoves=Arrays.copyOfRange(bestMoves, 1, bestMoves.length);
							}
						}
						else{
							placed=true;
						}
					} else {//Randomly generated bad move
						p = bestMoves[1];
						placed=true;
					}
				} else {//AI has only 1 possible move
					p = bestMoves[0];
					placed=true;
				}
				
			}
			//Hard difficulty
			else if (diff == AIDifficulties.HARD) {
				if (bestMoves.length > 1) {//More than one move
					if (actualRNG < hardC) {
						if (!(bestMoves[0].getPres() == 5 || bestMoves[0].getPres() == 10)) {
							p = bestMoves[0];
						} else {//Case that all AI moves don't advance towards win or block
							p = bestMoves[(int) (r.nextDouble() * bestMoves.length)];
						}
						if(p.getY()+1<6){//checks range to avoid indexing error
							//Checks that next AI placement won't allow user to win by building on top of it
							if(!possibleBlock(new Piece(p.getX(),p.getY()+1,false),allPieces)){
								placed=true;
							}
							else{
								//Deletes the bad move and loops again
								bestMoves=Arrays.copyOfRange(bestMoves, 1, bestMoves.length);
							}
						}
						else{
							placed=true;
						}
					} else {//Randomly generated bad move
						p = bestMoves[1];
						placed=true;
					}
				} else {//AI has only 1 possible move
					p = bestMoves[0];
					placed=true;
				}
				
			}
			
		}

		// update bottom row of board
		grid.updateBottomRow();
		return p;
	}

	/**
	 * Orders the moves by priority according to AI's particular logic chain
	 * First looks for winning moves, then looks for blocking user wins, then looks to advance towards a win,
	 * then tries to block the user's moves towards wins, and finally makes moves with no need for logic
	 * @param possibleMoves All the possible grid positions for a move
	 * @param wholeBoard the board state when the makeAMove method was called
	 * @return all of the possible moves sorted by their priority
	 */
	private Piece[] orderBestMoves(Piece[] possibleMoves, Piece[][] wholeBoard) {
		// create a new dummy array to later sort.

		int[] pres = new int[possibleMoves.length];
		int lengthCounter = 0;//Used to make final array
		for (int x = 0; x < possibleMoves.length; x++) {
			//looks at each move, determines how important the move is
			if (possibleMoves[x] != null) {
				if (possibleWin(possibleMoves[x], wholeBoard)) {
					pres[x] = 1;
				} else if (possibleBlock(possibleMoves[x], wholeBoard)) {
					pres[x] = 2;
				} else if (candidateWin(possibleMoves[x], wholeBoard)) {
					pres[x] = 3;
				} else if (candidateBlock(possibleMoves[x], wholeBoard)) {
					pres[x] = 4;
				} else // nothing found
				{
					pres[x] = 5;
				}
				lengthCounter++;
			}
		}
		Piece[] bestMoves = new Piece[lengthCounter];
		int indexOfMove = 0;
		//Checks each piece, makes a bestMoves array the size of all the non null moves
		for (int x = 0; x < possibleMoves.length; x++) {
			if (possibleMoves[x] != null) {
				bestMoves[indexOfMove] = possibleMoves[x];
				bestMoves[indexOfMove].pres = pres[x];
				indexOfMove++;
			}
		}
		//Sort array by pres, the important moves are first
		Arrays.sort(bestMoves);
		return bestMoves;
	}

	/**
	 *Looks for places where the computer could place a third piece in a row
	 * @param p the current position that is being considered
	 * @param wholeBoard the board state at this time
	 * @return true if the placement would result in3 in a row, false if not
	 */
	private boolean candidateWin(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, true, 2) >= 2) {
			return true;
		}
		return false;
	}
	/**
	 *Looks for places where the computer could place a fourth piece in a row and win
	 * @param p the current position that is being considered
	 * @param wholeBoard the board state at this time
	 * @return true if the placement would result in 3 in a row, false if not
	 */
	private boolean possibleWin(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, true, 3) >= 3) {
			return true;
		}
		return false;
	}
	/**
	 *Looks for places where the computer could block a user win
	 * @param p the current position that is being considered
	 * @param wholeBoard the board state at this time
	 * @return true if the placement would result in3 in a row, false if not
	 */
	private boolean possibleBlock(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, false, 3) >= 3) {
			return true;
		}
		return false;
	}
	/**
	 *Looks for places where the computer could block a third piece in a row for the user
	 * @param p the current position that is being considered
	 * @param wholeBoard the board state at this time
	 * @return true if the placement would result in3 in a row, false if not
	 */
	private boolean candidateBlock(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, false, 2) >= 2) {
			return true;
		}
		return false;
	}

	/**
	 * We are still debugging this method, atm it does nothing
	 * @param p
	 *            the piece wanted to be checked.
	 * @param amt
	 *            the amount of spaces the piece needs
	 * @return
	 * 			  whether there is a wall to the right
	 */
	private boolean findWallRight(Piece p, int amt) {
		if (p.getX() + amt > 6) {
			return false;
		}
		return true;
	}

	private boolean findWallLeft(Piece p, int amt) {
		if (p.getX() - amt < 0) {
			return false;
		}
		return true;
	}

	private boolean findWallVertical(Piece p, int amt) {
		if (p.getY() + amt > 5) {
			return false;
		}
		return true;
	}

	/**
	 * Finds the highest amount of pieces in a row near the current piece
	 * @param p The intended placement
	 * @param wholeBoard The board state when the makeAMove method was called
	 * @param isPlayer If true, tries to find winning moves, otherwise finds blocking moves
	 * @param amt Amount of pieces in a row needed to win
	 * @return the highest number of pieces in a row near this location.
	 */
	private int findMaxNieghbors(Piece p, Piece[][] wholeBoard, boolean isPlayer, int amt) {
		// for blocks is player should be false
		// for wins is player should be true
		int[] lengths = new int[8];
		int temp = 0;
		boolean broke = false;//Just adds an early end to for loops
		if (isPlayer) {
			//goes down
			for (int x = p.getY(); x > 0 && !broke; x--) {
				if (x > 0) {
					if (wholeBoard[x - 1][p.getX()] != null && !wholeBoard[x - 1][p.getX()].isPlayerPiece()) {
						temp++;
					} else {
						broke = true;
					}
				} else {
					broke = true;
				}
			}
			if (findWallVertical(p, amt - temp))
				lengths[0] = temp;
			else
				lengths[0] = 0;
			lengths[1] = 0;
			broke = false;
			temp = 0;
			//goes right
			for (int y = p.getX(); y <= 5 && !broke; y++) {
				if (y < 6) {
					if (wholeBoard[p.getY()][y + 1] != null && !wholeBoard[p.getY()][y + 1].isPlayerPiece()) {
						temp++;
					} else {
						broke = true;
					}
				} else {
					broke = true;
				}
			}

			if (findWallRight(p, amt - temp))
				lengths[2] = temp;
			else
				lengths[2] = 0;
			broke = false;
			temp = 0;
			//goes left
			for (int y = p.getX(); y >= 0 && !broke; y--) {
				if (y > 0) {
					if (wholeBoard[p.getY()][y - 1] != null && !wholeBoard[p.getY()][y - 1].isPlayerPiece()) {
						temp++;
					} else {
						broke = true;
					}
				} else {
					broke = true;
				}

			}
			if (findWallLeft(p, amt - temp))
				lengths[3] = temp;
			else
				lengths[3] = 0;
			lengths[1] = lengths[3] + lengths[2];//adds left and right, used if piece in between others
			broke = false;
			temp = 0;
			//diagonal down left
			for (int x = p.getX()-1, y = p.getY()-1; y >= 0 && !broke && x >= 0; y--, x--) {
				if (y >= 0 && x >= 0 && wholeBoard[y][x]!=null &&
						!wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[4]=temp;
			temp=0;
			broke =false;
			//down right
			for (int x = p.getX()+1, y = p.getY()-1; y >= 0 && !broke && x < 7; y--, x++) {
				if (y >= 0 && x < 7 && wholeBoard[y][x]!=null &&
						!wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[5]=temp;
			temp=0;
			broke =false;
			//up right
			for (int x = p.getX()+1, y = p.getY()+1; y < 7 && !broke && x < 7; y++, x++) {
				if (y < 6 && x < 7  && wholeBoard[y][x]!=null &&
						!wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[6]=temp;
			temp=0;
			broke =false;
			//up left
			for (int x = p.getX()-1, y = p.getY()+1; y < 7 && !broke && x >= 0; y++, x--) {
				if (y < 6 && x >= 0 && wholeBoard[y][x]!=null &&
						!wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[7]=temp;
			//add same plane diagonals, used if piece in between 2 pieces
			lengths[4]=lengths[4]+lengths[6];
			lengths[5]=lengths[5]+lengths[7];
		} else {
			//goes down
			for (int x = p.getY(); x > 0 && !broke; x--) {
				if (x > 0) {
					if (wholeBoard[x - 1][p.getX()] != null && wholeBoard[x - 1][p.getX()].isPlayerPiece()) {
						temp++;
					} else {
						broke = true;
					}
				} else {
					broke = true;
				}
			}
			if (findWallVertical(p, amt - temp))
				lengths[0] = temp;
			else
				lengths[0] = 0;
			broke = false;
			temp = 0;
			lengths[1] = temp;
			//right
			for (int y = p.getX(); y <= 5 && !broke; y++) {
				if (y < 6) {
					if (wholeBoard[p.getY()][y + 1] != null && wholeBoard[p.getY()][y + 1].isPlayerPiece()) {
						temp++;
					} else {
						broke = true;
					}
				} else {
					broke = true;
				}
			}
			if (findWallRight(p, amt - temp))
				lengths[2] = temp;
			else
				lengths[2] = temp;
			broke = false;
			temp = 0;
			//left
			for (int y = p.getX(); y >= 0 && !broke; y--) {
				if (y > 0) {
					if (wholeBoard[p.getY()][y - 1] != null && wholeBoard[p.getY()][y - 1].isPlayerPiece()) {
						temp++;
					} else {
						broke = true;
					}
				} else {
					broke = true;
				}

			}
			if (findWallLeft(p, amt - temp))
				lengths[3] = temp;
			else
				lengths[3] = 0;
			broke = false;
			temp = 0;
			lengths[1] = lengths[3] + lengths[2];//Add left and right
			//diagonal down left
			for (int x = p.getX()-1, y = p.getY()-1; y >= 0 && !broke && x >= 0; y--, x--) {
				if (y >= 0 && x >= 0 && wholeBoard[y][x]!=null &&
						wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[4]=temp;
			temp=0;
			broke =false;
			//down right
			for (int x = p.getX()+1, y = p.getY()-1; y >= 0 && !broke && x < 7; y--, x++) {
				if (y >= 0 && x < 7 && wholeBoard[y][x]!=null &&
						wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[5]=temp;
			temp=0;
			broke =false;
			//up right
			for (int x = p.getX()+1, y = p.getY()+1; y < 7 && !broke && x < 7; y++, x++) {
				if (y < 6 && x < 7  && wholeBoard[y][x]!=null &&
						wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[6]=temp;
			temp=0;
			broke =false;
			//up left
			for (int x = p.getX()-1, y = p.getY()+1; y < 6 && !broke && x >= 0; y++, x--) {
				if (y < 6 && x >= 0 && wholeBoard[y][x]!=null &&
						wholeBoard[y][x].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[7]=temp;
			//Adds diagonals in same plane
			lengths[4]=lengths[4]+lengths[6];
			lengths[5]=lengths[5]+lengths[7];
		}
		int temps = -1;
		for (int x = 0; x < lengths.length; x++) {
			if (lengths[x] > temps) {
				temps = lengths[x];
			}
		}
		return temps;
	}
}

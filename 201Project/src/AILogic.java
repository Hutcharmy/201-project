import java.util.Arrays;
import java.util.Random;

public class AILogic {
	private enum AIDifficulties {
		EASY, MEDIUM, HARD
	}

	AIDifficulties diff;

	public AILogic(int difficulty) {
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

	public Piece makeAMove(Board grid) {
		// create rng for difficulty settings
		Random r = new Random();
		double actualRNG = r.nextDouble();
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
		if (diff == AIDifficulties.EASY) {
			if (bestMoves.length > 1) {
				if (actualRNG < easyC) {
					if (!(bestMoves[0].getPres() == 5 || bestMoves[0].getPres() == 10)) {
						p = bestMoves[0];
					} else {
						p = bestMoves[(int) (r.nextDouble() * bestMoves.length)];
					}
				} else {
					p = bestMoves[1];
				}
			} else {
				p = bestMoves[0];
			}
		} else if (diff == AIDifficulties.MEDIUM) {
			if (bestMoves.length > 1) {
				if (actualRNG < mediumC) {
					if (!(bestMoves[0].getPres() == 5 || bestMoves[0].getPres() == 10)) {
						p = bestMoves[0];
					} else {
						p = bestMoves[(int) (r.nextDouble() * bestMoves.length)];
					}
				} else {
					p = bestMoves[1];
				}
			} else {
				p = bestMoves[0];
			}
		} else if (diff == AIDifficulties.HARD) {
			if (bestMoves.length > 1) {
				if (actualRNG < hardC) {
					if (!(bestMoves[0].getPres() == 5 || bestMoves[0].getPres() == 10)) {
						p = bestMoves[0];
					} else {
						p = bestMoves[(int) (r.nextDouble() * bestMoves.length)];
					}
				} else {
					p = bestMoves[1];
				}
			} else {
				p = bestMoves[0];
			}
		}
		// update bottom row of board
		grid.updateBottomRow();
		// System.out.println("This is the piece being returned "+p);
		return p;
	}

	private Piece[] orderBestMoves(Piece[] possibleMoves, Piece[][] wholeBoard) {
		// create a new dummy array to later sort.

		int[] pres = new int[possibleMoves.length];
		int lengthCounter = 0;
		for (int x = 0; x < possibleMoves.length; x++) {
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
		for (int x = 0; x < possibleMoves.length; x++) {
			if (possibleMoves[x] != null) {
				bestMoves[indexOfMove] = possibleMoves[x];
				bestMoves[indexOfMove].pres = pres[x];
				indexOfMove++;
			}
		}
		Arrays.sort(bestMoves);
		return bestMoves;
	}

	private boolean candidateWin(Piece p, Piece[][] wholeBoard) {
		// need to add check here to make sure that there is no wall here.
		if (findMaxNieghbors(p, wholeBoard, false, 2) == 2) {
//			System.out.println("can win");
			return true;
		}
		return false;
	}

	private boolean possibleWin(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, false, 3) == 3) {
//			System.out.println("poss win");
			return true;
		}
		return false;
	}

	private boolean possibleBlock(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, true, 3) == 3) {
//			System.out.println("poss block");
			return true;
		}
		return false;
	}

	private boolean candidateBlock(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, true, 2) == 2) {
//			System.out.println("can block");
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param p
	 *            the piece wanted to be checked.
	 * @param amt
	 *            the amount of spaces the piece needs
	 * @return
	 */
	private boolean findWallRight(Piece p, int amt) {
		if (p.getX() + amt > 6) {
			// System.out.println("Found wall right");
			return false;
		}
		return true;
	}

	private boolean findWallLeft(Piece p, int amt) {
		if (p.getX() - amt < 0) {
			// System.out.println("Found wall left");
			return false;
		}
		return true;
	}

	private boolean findWallVertical(Piece p, int amt) {
		if (p.getY() + amt > 5) {
			// System.out.println("Found wall vertical");
			return false;
		}

		return true;
	}

	private int findMaxNieghbors(Piece p, Piece[][] wholeBoard, boolean isPlayer, int amt) {
		// for blocks is player should be false
		// for wins is player should be true
		int[] lengths = new int[5];
		int temp = 0;
		boolean broke = false;
		if (isPlayer) {
			// goes left
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
			broke = false;
			temp = 0;
			// goes up
			lengths[1] = 0;
			broke = false;
			temp = 0;

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
				lengths[2] = temp;
			broke = false;
			temp = 0;
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
			lengths[1] = lengths[3] + lengths[2];
			broke = false;
			temp = 0;
			for (int x = p.getX(), y = p.getY(); y >= 0 && !broke && x >= 0; y--, x--) {
				if (y > 0 && x > 0 && wholeBoard[Math.abs(p.getX() - x)][Math.abs(p.getY() - y)]!=null &&
						!wholeBoard[Math.abs(p.getX() - x)][Math.abs(p.getY() - y)].isPlayerPiece()) {
//					System.out.println("ran !");
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[4]=temp;
			temp=0;
			broke =false;
		} else {
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
			lengths[1] = lengths[3] + lengths[2];
			for (int x = p.getX(), y = p.getY(); y >= 0 && !broke && x >= 0; y--, x--) {
				if (y > 0 && x > 0 && wholeBoard[Math.abs(p.getX() - x)][Math.abs(p.getY() - y)]!=null &&
						wholeBoard[Math.abs(p.getX() - x)][Math.abs(p.getY() - y)].isPlayerPiece()) {
//					System.out.println("ran");
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[4]=temp;
			temp=0;
			broke =false;
		}
		int temps = -1;
		for (int x = 0; x < lengths.length; x++) {
			if (lengths[x] > temps) {
				temps = lengths[x];
			}
		}
		// System.out.println(Arrays.toString(lengths)+ p.isPlayerPiece());
		return temps;
	}
}

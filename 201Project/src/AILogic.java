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
		Random r = new Random();
		double actualRNG = r.nextDouble();
		final double easyC = .4, mediumC = .7, hardC = .9;
		Piece p = new Piece(0, 0, false);
		Piece[][] allPieces = grid.getBoard();
		int possX[] = grid.getBottomRows();
		// this checks for invalid moves.
		for (int x = 0; x < 7; x++) {
			if (allPieces[x].length == 6) {
				possX[x] = -1;
			}
		}
		Piece[] possibleMoves = new Piece[7];
		for (int x = 0; x < 7; x++) {
			if (possX[x] != -1) {
				possibleMoves[x] = allPieces[x][possX[x]];
			} else {
				possibleMoves[x] = null;
			}
		}
		Piece bestMoves[] = orderBestMoves(possibleMoves, allPieces);
		if (diff == AIDifficulties.EASY) {
			if (bestMoves.length > 1) {
				if (actualRNG < easyC) {
					p = bestMoves[0];
				} else {
					p = bestMoves[1];
				}
			} else {
				p = bestMoves[0];
			}
		} else if (diff == AIDifficulties.MEDIUM) {
			if (bestMoves.length > 1) {
				if (actualRNG < mediumC) {
					p = bestMoves[0];
				} else {
					p = bestMoves[1];
				}
			} else {
				p = bestMoves[0];
			}
		} else if (diff == AIDifficulties.HARD) {
			if (bestMoves.length > 1) {
				if (actualRNG < hardC) {
					p = bestMoves[0];
				} else {
					p = bestMoves[1];
				}
			} else {
				p = bestMoves[0];
			}
		}
		return p;
	}

	private Piece[] orderBestMoves(Piece[] possibleMoves, Piece[][] wholeBoard) {
		Piece[] bestMoves = new Piece[possibleMoves.length];
		int[] pres = new int[bestMoves.length];
		for (int x = 0; x < bestMoves.length; x++) {
			if (possibleWin(bestMoves[x], wholeBoard)) {
				pres[x] = 1;
			} else if (possibleBlock(bestMoves[x], wholeBoard)) {
				pres[x] = 2;
			} else if (candidateWin(bestMoves[x], wholeBoard)) {
				pres[x] = 3;
			} else if (candidateBlock(bestMoves[x], wholeBoard)) {
				pres[x] = 4;
			} else // nothing
			{
				pres[x] = 5;
			}
		}
		for(int x=0; x<bestMoves.length; x++){
			bestMoves[x].pres=pres[x];
		}
		Arrays.sort(bestMoves);
		return bestMoves;
	}

	private boolean candidateWin(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, false) == 2) {
			return true;
		}
		return false;
	}

	private boolean possibleWin(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, false) == 3) {
			return true;
		}
		return false;
	}

	private boolean possibleBlock(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, true) == 3) {
			return true;
		}
		return false;
	}

	private boolean candidateBlock(Piece p, Piece[][] wholeBoard) {
		if (findMaxNieghbors(p, wholeBoard, true) == 2) {
			return true;
		}
		return false;
	}

	private int findMaxNieghbors(Piece p, Piece[][] wholeBoard, boolean isPlayer) {
		// for blocks is player should be false
		// for wins isplayer should be true
		int[] lengths = new int[4];
		int temp = 0;
		boolean broke = false;
		if (isPlayer) {
			// check left
			// check right
			// check up
			// check down
			for (int x = p.getX(); x >= 0 && !broke; x--) {
				if (wholeBoard[x][p.getY()].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[0] = temp;
			broke = false;
			for (int x = p.getX(); x <= 7 && !broke; x++) {
				if (wholeBoard[x][p.getY()].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[1] = temp;
			broke = false;
			for (int y = p.getY(); y <= 6 && !broke; y++) {
				if (wholeBoard[p.getX()][y].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[2] = temp;
			broke = false;
			for (int y = p.getY(); y >= 6 && !broke; y--) {
				if (wholeBoard[p.getX()][y].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[3] = temp;
			broke = false;

		} else {
			for (int x = p.getX(); x >= 0 && !broke; x--) {
				if (!wholeBoard[x][p.getY()].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[0] = temp;
			broke = false;
			for (int x = p.getX(); x <= 7 && !broke; x++) {
				if (!wholeBoard[x][p.getY()].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[1] = temp;
			broke = false;
			for (int y = p.getY(); y <= 6 && !broke; y++) {
				if (!wholeBoard[p.getX()][y].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[2] = temp;
			broke = false;
			for (int y = p.getY(); y >= 6 && !broke; y--) {
				if (!wholeBoard[p.getX()][y].isPlayerPiece()) {
					temp++;
				} else {
					broke = true;
				}
			}
			lengths[3] = temp;
			broke = false;

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

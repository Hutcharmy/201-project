
public class Board {
	Piece[][] board;
	int[] bottomRow;
	public Board(){
		board=new Piece[6][7];
		bottomRow=new int[7];
	}
	/**
	 * Adds a piece to the board if the placement is legal, returns a boolean to tell if the piece was placed succesfully
	 * @param p the piece to be added
	 * @return true if piece is placed, false if not
	 */
	public boolean addPiece(Piece p){
		boolean placed=false;
		if(bottomRow[p.getY()]<6){
			int x=p.getX();
			int y=p.getY();
			board[x][y]=p;
			bottomRow[y]++;
			placed=true;
		}
		return placed;
	}
	public Piece getPiece(int x, int y){
		return board[x][y];
	}
	public int[] getBottomRows(){
		return bottomRow;
	}
}

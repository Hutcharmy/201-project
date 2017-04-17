
public class Board {
	private Piece[][] board;
	private int[] bottomRow;
	private String win;
	private Piece lastPlayerPiece, lastAIPiece;
	public Board(){
		board=new Piece[6][7];
		bottomRow=new int[7];
		win="None";
	}
	/**
	 * Adds a piece to the board if the placement is legal, returns a boolean to tell if the piece was placed succesfully
	 * @param p the piece to be added
	 * @return true if piece is placed, false if not
	 */
	public boolean addPiece(Piece p){
		boolean placed=false;
		if(p!=null && bottomRow[p.getY()]<6){
			int x=p.getX();
			int y=p.getY();
			board[y][x]=p;
			bottomRow[x]++;
			if(p.getType()==PieceType.PLAYER){
				this.lastPlayerPiece=p;
			}
			else{
				this.lastAIPiece=p;
			}
			placed=true;
		}
		return placed;
	}
	public Piece getPiece(int x, int y){
		return board[y][x];
	}
	public int[] getBottomRows(){
		return bottomRow;
	}
	public Piece getLastPlayerPiece(){
		return lastPlayerPiece;
	}
	public Piece getLastAIPiece(){
		return lastAIPiece;
	}
	public void setWin(String win){
		this.win=win;
	}
	public String getWin(){
		return win;
	}
	public Piece[][] getBoard(){
		return board;
	}
	public void setBottomRow(int[] newBottomRow){
		newBottomRow=bottomRow;
	}
}

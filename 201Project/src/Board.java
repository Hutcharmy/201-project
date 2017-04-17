
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
			System.out.println("this is y "+y);
			System.out.println("this is x "+x);
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
	public void updateBottomRow(){
		for (int x=0; x<6; x++){
			for (int y=6; y>=0; y--){
				if (board[x][y]!=null){
					bottomRow[y]=x;
				}
				System.out.println(bottomRow[y]+"bot y");
				if (bottomRow[y]<0){
					bottomRow[y]=0;
				}
			}
		}
	}
}

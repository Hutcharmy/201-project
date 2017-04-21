
public class Piece implements Comparable<Piece>{
	private int x, y;
	private PieceType type;
	private boolean isPlayerPiece;
	public int pres=10;
	public Piece(int x, int y, boolean isPlayerPiece){
		this.x=x;
		this.y=y;
		this.isPlayerPiece=isPlayerPiece;
		if(isPlayerPiece) this.type=PieceType.PLAYER;
		else this.type=PieceType.AI;
	}
	public boolean isPlayerPiece() {
		return isPlayerPiece;
	}
	public void setPlayerPiece(boolean isPlayerPiece) {
		this.isPlayerPiece = isPlayerPiece;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public PieceType getType() {
		return type;
	}
	@Override
	public int compareTo(Piece o) {
		if (o.pres==pres)
			return 0;
		else if (o.pres>pres){
			return -1;
		}
		else{
			return 1;
		}
	}
	public String toString(){
		return x+"  "+y;
	}
	public int getPres(){
		return pres;
	}

	
	
}

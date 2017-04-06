
public class Piece {
	int x, y;
	PieceType type;
	public Piece(int x, int y, boolean isPlayerPiece){
		this.x=x;
		this.y=y;
		if(isPlayerPiece) this.type=PieceType.PLAYER;
		else this.type=PieceType.AI;
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
	
	
}

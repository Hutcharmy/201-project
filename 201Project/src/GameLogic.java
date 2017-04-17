import java.util.Arrays;

public class GameLogic {
	private Board b;
	private String win;
	private AILogic currentAI;
	public GameLogic(AILogic a){
		b=null;
		win="None";
		currentAI=a;
	}
	/**
	 * Takes in the user's move selection, makes a legal move, checks if the player won, then calls the AILogic for a move
	 * and also checks if the AI won. Stores the win condition in the board object. Returns without placing a piece
	 * if the user move is not valid or if there is a win state achieved. 
	 * @param bo the initial board state
	 * @param col the column the user wishes to place their piece in
	 * @return the state of the board after any moves are made
	 */
	public Board getMove(Board bo, int col){
		this.b=bo;
		Piece p=null;
		b.updateBottomRow();
		int[] bottom=b.getBottomRows();
		System.out.println(Arrays.toString(bottom));
		if(bottom[col]<6){ 
			p=new Piece(bottom[col], col, true);
		}
		int[] direction={0,0};
		if(b.addPiece(p)){
			win=checkWinCondition(p, 1, direction);
			if(win.equals("None")){
				boolean placed=false;
				while(!placed){
					p=currentAI.makeAMove(this.b);
					if(b.addPiece(p)){
						placed=true;
					}
				}
				win=checkWinCondition(p, 1, direction);
				if(win.equals("winner")){
					b.setWin("AI Win");
				}
				else if(win.equals("draw")){
					b.setWin("Draw");
				}
			}
			else if(win.equals("winner")){
				b.setWin("Player Win");
			}
			else if(win.equals("draw")){
				b.setWin("Draw");
			}
		}
		return this.b;
	}
	/**
	 * Checks the state of the board to see if the current move resulted in a win
	 * If it is the first time this method is called, recursively checks each direction to see if there is a piece of the same 
	 * type in the next position(does not check upward as that is impossible). If after that, it checks to see if there is four
	 * in a row. If not, it moves in the same direction as last time and sees if the line continues. 
	 * @param p the current piece
	 * @param streak the amount of pieces in a row so far, starts at 1, exits at 4
	 * @param direction the current direction moving, first time is {0,0}, after that is assigned by inner for loop
	 * @return win condition string, either "None", "draw", or "winner"
	 */
	private String checkWinCondition(Piece p, int streak, int[] direction){
		String currentWin="None";
		if(streak==1){
			int[] fullUp={6,6,6,6,6,6,6};
			if(b.getBottomRows()==fullUp){
				return "draw";
			}
			else{
				int[][] moveConstants={{1,1},{1,-1},{0,1},{0,-1},{-1,1},{-1,0},{-1,-1}};
				for(int i=0;i<moveConstants.length;i++){
					int[] currentMove=moveConstants[i];
					int nextX=p.getX()+currentMove[1];
					int nextY=p.getY()+currentMove[0];
					if(nextX<0||nextY<0||nextX>=6||nextY>=6){
						return "None";
					}
					Piece nextPiece=b.getPiece(nextX, nextY);
					if(nextPiece!=null && nextPiece.getType()==p.getType()){
						currentWin=checkWinCondition(nextPiece, 2, currentMove);
						if(currentWin=="winner"||currentWin=="draw"){
							return currentWin;
						}
					}
				}
			}
		}
		else if(streak==4){
			return "winner";
		}
		else{
			if(p==null){
				return "None";
			}
			PieceType type=p.getType();
			int x=p.getX();
			int y=p.getY();
			int nextX=x+direction[1];
			int nextY=y+direction[0];
			if(nextX<0||nextY<0||nextX>=6||nextY>=6){
				return "None";
			}
			Piece nextPiece=b.getPiece(nextX, nextY);
			System.out.println(nextPiece+" next piece");
			if(nextPiece.getType()==type){
				currentWin=checkWinCondition(nextPiece, streak+1, direction);
				if(currentWin=="winner"||currentWin=="draw"){
					return currentWin;
				}
				else{
					return "None";
				}
			}
		}
		return "None";
	}
}

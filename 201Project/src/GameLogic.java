import java.util.Arrays;

public class GameLogic {
	private Board b;
	private int win;
	private AILogic currentAI;
	public GameLogic(AILogic a){
		b=null;
		win=-1;
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
			p=new Piece(col, bottom[col], true);
		}
		int[] direction={0,0};
		if(b.addPiece(p)){
			win=checkWinCondition(p, 1, direction);
			if(win==4){
				b.setWin("Player Win");
			}
			else if(win==-100){
				b.setWin("Draw");
			}
			else{
				boolean placed=false;
				while(!placed){
					p=currentAI.makeAMove(this.b);
					if(b.addPiece(p)){
						placed=true;
					}
				}
				win=checkWinCondition(p, 1, direction);
				if(win==4){
					b.setWin("AI Win");
				}
				else if(win==-100){
					b.setWin("Draw");
				}
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
	private int checkWinCondition(Piece p, int streak, int[] direction){
		System.out.println(streak +" is the streak");
		int currentWin;
		if(streak==1){
			currentWin=-1;
			boolean drawFlag=true;
			Piece[][] currentBoard=b.getBoard();
			for(int i=0;i<currentBoard.length;i++){
				for(int j=0;j<currentBoard[0].length;j++){
					if(b.getPiece(j,i)==null){
						drawFlag=false;
					}
				}
			}
			if(drawFlag) return -100;
			System.out.println(p);
			int[] currentMove={0,0};
			int[][] moveConstants={{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1},{-1,0}};
			for(int i=0;i<4;i++){
				streak=1;
				currentWin=1;
				currentMove=moveConstants[i*2];
				
				int nextX=p.getX()+currentMove[1];
				int nextY=p.getY()+currentMove[0];
				System.out.println("Moving to "+nextX+" , "+nextY);
				if(!(nextX<0||nextY<0||nextX>=7||nextY>=6)){
					Piece nextPiece=b.getPiece(nextX, nextY);
					
					if(nextPiece!=null && nextPiece.getType().equals(p.getType())){
						currentWin=checkWinCondition(nextPiece, streak+1, currentMove);
						if(currentWin==4){
							return currentWin;
						}
					}
				}
				
				if(i!=3){
					System.out.println(currentWin+" after last move");
					currentMove=moveConstants[i*2+1];
					
					nextX=p.getX()+currentMove[1];
					nextY=p.getY()+currentMove[0];
					System.out.println("Moving to "+nextX+" , "+nextY);
					if(!(nextX<0||nextY<0||nextX>=7||nextY>=6)){
						Piece nextPiece=b.getPiece(nextX, nextY);
						if(nextPiece!=null && nextPiece.getType().equals(p.getType())){
							currentWin=checkWinCondition(nextPiece, currentWin+1, currentMove);
							if(currentWin==4){
								return currentWin;
							}
						}
					}
					
				}
			}
		}
		
		else if(streak==4){
			return streak;
		}
		else{
			currentWin=streak;
			System.out.println("We in here");
			System.out.println(p);
			if(p==null){
				System.out.println("Hwhat");
				return streak-1;
			}
			PieceType type=p.getType();
			int x=p.getX();
			int y=p.getY();
			int nextX=x+direction[1];
			int nextY=y+direction[0];
			if(nextX<0||nextY<0||nextX>=7||nextY>=6){
				System.out.println("Hwhat the Sequel");
				return streak;
			}
			Piece nextPiece=b.getPiece(nextX, nextY);
			System.out.println(nextPiece+" next piece");
			if(nextPiece!=null) System.out.println(nextPiece.getType());
			System.out.println(type);
			if(nextPiece!=null && nextPiece.getType().equals(type)){
				System.out.println("We made it");
				currentWin=checkWinCondition(nextPiece, streak+1, direction);
				if(currentWin==4){
					return currentWin;
				}
				else{
					System.out.println(currentWin);
					return currentWin;
				}
			}
			else{
				return streak;
			}
		}
		return streak-1;
	}
}

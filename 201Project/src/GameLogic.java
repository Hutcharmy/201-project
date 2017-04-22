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
		b.updateBottomRow();//Makes sure bottom rows are accurate
		int[] bottom=b.getBottomRows();//Gets bottom rows
		//System.out.println(Arrays.toString(bottom));
		//Check to see if piece can be placed in column, if it can creates piece at bottom of column
		if(bottom[col]<6){ 
			p=new Piece(col, bottom[col], true);
		}
		int[] direction={0,0};
		//Tries to add piece to board
		if(b.addPiece(p)){
			win=checkWinCondition(p, 1, direction);//Check if user won
			if(win==4){
				b.setWin("Player Win");
			}
			else if(win==-100){
				b.setWin("Draw");
			}
			//If no win condition met, goes to AI turn
			else{
				boolean placed=false;
				while(!placed){//Tries to make a legal move over and over until it does
					p=currentAI.makeAMove(this.b);//AI Move
					if(b.addPiece(p)){
						placed=true;
					}
				}
				win=checkWinCondition(p, 1, direction);//Checks if AI won
				if(win==4){
					b.setWin("AI Win");
				}
				else if(win==-100){
					b.setWin("Draw");
				}
			}
			
		}
		return this.b;//Return board to panel
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
		//System.out.println(streak +" is the streak");
		int currentWin;//Win condition at this point
		//Initial condition
		if(streak==1){
			//Checks for condition of draw, brute force because it didn't work the nice and easy way
			boolean drawFlag=true;
			Piece[][] currentBoard=b.getBoard();
			for(int i=0;i<currentBoard.length;i++){
				for(int j=0;j<currentBoard[0].length;j++){
					if(b.getPiece(j,i)==null){
						drawFlag=false;
					}
				}
			}
			//Dramatically low value to prevent false positives
			if(drawFlag) return -100;
			
			//System.out.println(p);
			int[] currentMove={0,0};
			int[][] moveConstants={{0,1},{0,-1},{1,1},{-1,-1},{1,-1},{-1,1},{-1,0}};//All 7 directions pieces could be in
			for(int i=0;i<4;i++){//Horizontal, then both diagonals, then vertical, 1 direction then the other
				//Reset default values
				streak=1;
				currentWin=1;
				currentMove=moveConstants[i*2];
				//Finds coordinates of next piece in current direction
				int nextX=p.getX()+currentMove[1];
				int nextY=p.getY()+currentMove[0];
				System.out.println("Moving to "+nextX+" , "+nextY);
				//Checks for invalid poisitions
				if(!(nextX<0||nextY<0||nextX>=7||nextY>=6)){
					//Pulls next piece to be checked
					Piece nextPiece=b.getPiece(nextX, nextY);
					//If piece is null or other type, skips, otherwise moves to next piece and continues
					if(nextPiece!=null && nextPiece.getType().equals(p.getType())){
						currentWin=checkWinCondition(nextPiece, streak+1, currentMove);
						//Checks for a win
						if(currentWin==4){
							return currentWin;
						}
					}
				}
				//In all cases but the last, moves in opposite direction
				if(i!=3){
					System.out.println(currentWin+" after last move");
					currentMove=moveConstants[i*2+1];
					//Next position
					nextX=p.getX()+currentMove[1];
					nextY=p.getY()+currentMove[0];
					System.out.println("Moving to "+nextX+" , "+nextY);
					//Checks invalid coordinates
					if(!(nextX<0||nextY<0||nextX>=7||nextY>=6)){
						//Gets next piece
						Piece nextPiece=b.getPiece(nextX, nextY);
						//If piece is null or other type, skips, otherwise moves to next piece and continues
						if(nextPiece!=null && nextPiece.getType().equals(p.getType())){
							currentWin=checkWinCondition(nextPiece, currentWin+1, currentMove);
							//Checks for win
							if(currentWin==4){
								return currentWin;
							}
						}
					}
					
				}
			}
		}
		//Returns if 4 in a row
		else if(streak==4){
			return streak;
		}
		//All cases besides first and last
		else{
			currentWin=streak;//Sets win condition to amount of pieces visited
			System.out.println("We in here");
			System.out.println(p);
			//Looks for null piece, should be nearly impossible
			if(p==null){
				System.out.println("Hwhat");
				return streak-1;
			}
			//Gets type and position of piece
			PieceType type=p.getType();
			int x=p.getX();
			int y=p.getY();
			//Gets position of next piece
			int nextX=x+direction[1];
			int nextY=y+direction[0];
			//Checks for invalid coordinates
			if(nextX<0||nextY<0||nextX>=7||nextY>=6){
				System.out.println("Hwhat the Sequel");
				return streak;
			}
			//Gets next piece
			Piece nextPiece=b.getPiece(nextX, nextY);
			System.out.println(nextPiece+" next piece");
			if(nextPiece!=null) System.out.println(nextPiece.getType());
			System.out.println(type);
			//If piece is null or other type, skips, otherwise moves to next piece and continues
			if(nextPiece!=null && nextPiece.getType().equals(type)){
				System.out.println("We made it");
				//Moves to next step of program
				currentWin=checkWinCondition(nextPiece, streak+1, direction);
				//Checks for win
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

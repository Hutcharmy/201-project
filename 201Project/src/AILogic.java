
public class AILogic {
	private enum AIDifficulties{
		EASY, MEDIUM, HARD
	}
	AIDifficulties diff;
	public AILogic(int difficulty){
		if(difficulty==0){
			diff=AIDifficulties.EASY;
		}
		else if(difficulty==1){
			diff=AIDifficulties.MEDIUM;
		}
		else if(difficulty==2){
			diff=AIDifficulties.HARD;
		}
		else{
			throw new ConnectFourException("Invalid difficulty");
		}
	}
	public Piece makeAMove(Board grid){
		return null;
	}
}

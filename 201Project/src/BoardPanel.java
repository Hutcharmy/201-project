import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel[][] pieces;
	ImageIcon playerIcon;
	ImageIcon aiIcon;
	public BoardPanel() {
		this.setSize(700, 600);
		pieces=new JLabel[6][7];
		for(int i=0;i<pieces.length;i++){
			for(int j=0;j<pieces[0].length;j++){
				JLabel currentLabel=new JLabel("");
				currentLabel.setVerticalAlignment(SwingConstants.TOP);
				currentLabel.setBounds(j*100, i*100, 100, 100);
				currentLabel.setIcon(null);
				pieces[i][j]=currentLabel;
			}
		}
		for(int i=0;i<pieces.length;i++){
			for(int j=0;j<pieces[0].length;j++){
				add(pieces[i][j]);
			}
		}
	}
	public void addPiece(Piece p){
		int x=p.getX();
		int y=p.getY();
		JLabel currentLabel=pieces[y][x];
		PieceType type=p.getType();
		if(currentLabel.getIcon()==null){
			if(type.equals(PieceType.PLAYER)){
				currentLabel.setIcon(playerIcon);
			}
			else if(type.equals(PieceType.AI)){
				currentLabel.setIcon(aiIcon);
			}
		}
		pieces[x][y]=currentLabel;
	}
	public void setColors(String selection){
		if(selection.equals("yellow")){
			playerIcon=new ImageIcon("YellowBox.png");
			aiIcon=new ImageIcon("RedBox.png");
		}
		else if(selection.equals("red")){
			playerIcon=new ImageIcon("RedBox.png");
			aiIcon=new ImageIcon("YellowBox.png");
		}
	}

}

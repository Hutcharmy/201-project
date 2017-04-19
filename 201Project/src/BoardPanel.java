import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BoardPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	JLabel[][] pieces;
	ImageIcon playerIcon;
	ImageIcon aiIcon;
	JLayeredPane topPanel;
	public BoardPanel(JLayeredPane top) {
		topPanel=top;
		GridLayout laid=new GridLayout(6,7);
		laid.setHgap(0);
		laid.setVgap(0);
		this.setLayout(laid);
		
		this.setSize(700, 600);
		pieces=new JLabel[6][7];
		for(int i=0;i<pieces.length;i++){
			for(int j=0;j<pieces[0].length;j++){
				JLabel currentLabel=new JLabel("");
				//currentLabel.setIcon(new ImageIcon("WhiteBox.png"));
				currentLabel.setIcon(null);
				pieces[i][j]=currentLabel;
			}
		}
		for(int i=pieces.length-1;i>=0;i--){
			for(int j=0;j<pieces[0].length;j++){
				add(pieces[i][j]);
			}
		}
	}
	public void addPiece(Piece p){
		if(p==null){
			return;
		}
		int x=p.getX();
		int y=p.getY();
		PieceType type=p.getType();
		if(pieces[y][x].getIcon()==null){
			if(type.equals(PieceType.PLAYER)){
				pieces[y][x].setIcon(playerIcon);
				pieces[y][x].setSize(90, 90);
			}
			else if(type.equals(PieceType.AI)){
				pieces[y][x].setIcon(aiIcon);
				pieces[y][x].setSize(90, 90);
			}
		}
		
		
		
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

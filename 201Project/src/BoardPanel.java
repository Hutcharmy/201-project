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
				currentLabel.setBounds((j*100)+50,(i*100)+50, 100, 100);
				currentLabel.setIcon(new ImageIcon("WhiteBox.png"));
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
		if(p==null){
			return;
		}
		int x=p.getX();
		int y=p.getY();
		JLabel currentLabel=pieces[y][x];
		PieceType type=p.getType();
		if(currentLabel.getIcon().equals(new ImageIcon("WhiteBox.png"))){
			if(type.equals(PieceType.PLAYER)){
				currentLabel.setIcon(playerIcon);
			}
			else if(type.equals(PieceType.AI)){
				currentLabel.setIcon(aiIcon);
			}
		}
		currentLabel.setVisible(true);
		pieces[y][x]=currentLabel;
		this.repaint();
		this.setVisible(true);
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

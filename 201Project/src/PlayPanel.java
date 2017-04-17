import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PlayPanel extends JPanel {
	private MainFrame frame;
	private Board bo;
	private AILogic AI;
	private int AIDifficulty, playerScore, AIScore;
	private GameLogic logic;
	public PlayPanel(MainFrame frame){
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		this.frame=frame;
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("Game Slide.jpg"));
		backgroundImage.setBounds(0, 0, 825, 850);
		this.add(backgroundImage);
		
		BoardPanel panel=new BoardPanel();
		panel.setLocation(50, 115);
		panel.setColors("yellow");
		this.add(panel);
		
		bo=new Board();
		AIDifficulty=0;
		AI=new AILogic(AIDifficulty);
		logic= new GameLogic(AI);
		playerScore=Score.getScore(AIDifficulty, true);
		AIScore=Score.getScore(AIDifficulty, false);
		
		JButton backButton=new JButton("");
		backButton.setBounds(300,720,200,61);
		backButton.setOpaque(false);
		
		System.out.println("We here");
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeContentPane(new MainJPanel(frame));
				System.out.println("Shitty");
			}
		});
		
		ActionListener colCheck=new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JButton source=(JButton) e.getSource();
				System.out.println("source "+source.getText());
				bo=logic.getMove(bo, Integer.parseInt(source.getText()));
				
				panel.addPiece(bo.getLastPlayerPiece());
				String won=bo.getWin();
				if(won.equals("Player Win")){
					System.out.println("You Win!");
					Score.incScore(AIDifficulty, true);
				}
				else{
					panel.addPiece(bo.getLastAIPiece());
					if(won.equals("AI Win")){
						System.out.println("You Win!");
						Score.incScore(AIDifficulty, false);
					}
					else if(won.equals("Draw")){
						System.out.println("Draw");
					}
				}
				panel.repaint();
				repaint();
			}
		};
		
		JButton firstColButton=new JButton("");
		firstColButton.setBounds(50,115,100,600);
		firstColButton.setText("0");
		firstColButton.setOpaque(false);
		firstColButton.addActionListener(colCheck);
		
		JButton secondColButton=new JButton("");
		secondColButton.setBounds(150,115,100,600);
		secondColButton.setText("1");
		secondColButton.setOpaque(false);
		secondColButton.addActionListener(colCheck);
		
		JButton thirdColButton=new JButton("");
		thirdColButton.setBounds(250,115,100,600);
		thirdColButton.setText("2");
		thirdColButton.setOpaque(false);
		thirdColButton.addActionListener(colCheck);
		
		JButton fourthColButton=new JButton("");
		fourthColButton.setBounds(350,115,100,600);
		fourthColButton.setText("3");
		fourthColButton.setOpaque(false);
		fourthColButton.addActionListener(colCheck);
		
		JButton fifthColButton=new JButton("");
		fifthColButton.setBounds(450,115,100,600);
		fifthColButton.setText("4");
		fifthColButton.setOpaque(false);
		fifthColButton.addActionListener(colCheck);
		
		JButton sixthColButton=new JButton("");
		sixthColButton.setBounds(550,115,100,600);
		sixthColButton.setText("5");
		sixthColButton.setOpaque(false);
		sixthColButton.addActionListener(colCheck);
		
		JButton seventhColButton=new JButton("");
		seventhColButton.setBounds(650,115,100,600);
		seventhColButton.setText("6");
		seventhColButton.setOpaque(false);
		seventhColButton.addActionListener(colCheck);
		
		this.add(backButton);
		this.add(firstColButton);
		this.add(secondColButton);
		this.add(thirdColButton);
		this.add(fourthColButton);
		this.add(fifthColButton);
		this.add(sixthColButton);
		this.add(seventhColButton);
		this.setVisible(true);
	}
		
}

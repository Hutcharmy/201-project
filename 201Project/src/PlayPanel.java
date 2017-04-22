import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PlayPanel extends JLayeredPane {
	private MainFrame frame;
	private Board bo;
	private AILogic AI;
	private BoardPanel panel;
	private int AIDifficulty, playerScore, AIScore;
	private GameLogic logic;
	private boolean gameOver;
	/**
	 * Essentially handles all game graphics and routes functuality
	 * @param frame the frame this panel is stored in
	 */
	public PlayPanel(MainFrame frame){
		//setup of frame
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		this.frame=frame;
		//Sets background to our main game slide, places on 2nd layer to allow for pieces behind it
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("Game Slide.png"));
		backgroundImage.setBounds(0, 0, 825, 850);
		this.add(backgroundImage, JLayeredPane.PALETTE_LAYER);
		
		
		//Creates board of pieces, places on lowest level so background obscures edges of square graphics
		panel=new BoardPanel(this);
		panel.setLocation(50, 115);
		//set colors of pieces to user choice
		if(frame.isRed()){
			panel.setColors("red");
		}
		else{
			panel.setColors("yellow");
		}
		
		this.add(panel, JLayeredPane.DEFAULT_LAYER);
		//Basic definitions
		bo=new Board();
		AIDifficulty=frame.getAIDifficulty();
		AI=new AILogic(AIDifficulty);
		logic= new GameLogic(AI);
		playerScore=Score.getScore(AIDifficulty, true);
		AIScore=Score.getScore(AIDifficulty, false);
		
		//Print out player score with images of nums
				if(Integer.toString(playerScore).length() == 1){
					JLabel printPlayerScore = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(playerScore) + ".png"));
					printPlayerScore.setBounds(295, 20, 75, 75);
					//printPlayerScore.setOpaque(true);
					this.add(printPlayerScore, JLayeredPane.DRAG_LAYER);
				}
				else if(Integer.toString(playerScore).length() == 2){
					JLabel printPlayerScore = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(playerScore).charAt(0) + ".png"));
					printPlayerScore.setBounds(285, 20, 75, 75);
					//printPlayerScore.setOpaque(true);
					this.add(printPlayerScore, JLayeredPane.DRAG_LAYER);
					
					JLabel printPlayerScore2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(playerScore).charAt(1) + ".png"));
					printPlayerScore2.setBounds(322, 20, 75, 75);
					//printPlayerScore.setOpaque(true);
					this.add(printPlayerScore2, JLayeredPane.DRAG_LAYER);
				}
				else System.err.println("given player score not the correct length!");
			
				//Print out AI score with images of nums
				if(Integer.toString(AIScore).length() == 1){
					JLabel printAIScore = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(AIScore) + ".png"));
					printAIScore.setBounds(700, 20, 75, 75);
					//printPlayerScore.setOpaque(true);
					this.add(printAIScore, JLayeredPane.DRAG_LAYER);
				}
				else if(Integer.toString(AIScore).length() == 2){
					JLabel printAIScore = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(AIScore).charAt(0) + ".png"));
					printAIScore.setBounds(691, 20, 75, 75);
					//printPlayerScore.setOpaque(true);
					this.add(printAIScore, JLayeredPane.DRAG_LAYER);
					
					JLabel printAIScore2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(AIScore).charAt(1) + ".png"));
					printAIScore2.setBounds(728, 20, 75, 75);
					//printPlayerScore.setOpaque(true);
					this.add(printAIScore2, JLayeredPane.DRAG_LAYER);
				}
				else System.err.println("given AI score not the correct length!");
				
				
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
		//Action listener for pieces
		ActionListener colCheck=new ActionListener(){
			/** 
			 * Calls game logic to place piece for user and AI, then gets results and sees if a win was achieved 
			 * Places all pieces generated during event, then calls endgameframe if required
			 */
			public void actionPerformed(ActionEvent e){
				if(gameOver) return;
				JButton source=(JButton) e.getSource();
				bo=logic.getMove(bo, Integer.parseInt(source.getText()));
				
//				for(int j=5;j>=0;j--){
//					for(int i=0;i<7;i++){
//						System.out.print(bo.getPiece(i, j));
//					}
//					System.out.println("");
//				}
				panel.addPiece(bo.getLastPlayerPiece());
				String won=bo.getWin();
				if(won.equals("Player Win")){
					System.out.println("You Win!");
					gameOver=true;
					Score.incScore(AIDifficulty, true);
					EndgameFrame frame3=new EndgameFrame(1,frame);
					frame3.setVisible(true);
				}
				else{
					panel.addPiece(bo.getLastAIPiece());
					if(won.equals("AI Win")){
						System.out.println("You Lose");
						gameOver=true;
						Score.incScore(AIDifficulty, false);
						EndgameFrame frame3=new EndgameFrame(-1,frame);
						frame3.setVisible(true);
					}
					else if(won.equals("Draw")){
						System.out.println("Draw");
						gameOver=true;
						EndgameFrame frame3=new EndgameFrame(0,frame);
						frame3.setVisible(true);
					}
				}
				
			}
		};
		//One button for each column
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

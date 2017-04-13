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
		bo=new Board();
		AI=new AILogic(0);
		logic= new GameLogic(AI);
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
		
		JButton firstColButton=new JButton("");
		firstColButton.setBounds(50,115,100,600);
		firstColButton.setOpaque(false);
		firstColButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bo=logic.getMove(bo,0);
				//need to add piece to board, this is next step
			}
		});
		System.out.println(firstColButton.getName()+" Is created");
		JButton secondColButton=new JButton("");
		secondColButton.setBounds(150,115,100,600);
		secondColButton.setOpaque(false);
		secondColButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bo=logic.getMove(bo,1);
				//need to add piece to board, this is next step			
		}});
		JButton thirdColButton=new JButton("");
		thirdColButton.setBounds(250,115,100,600);
		thirdColButton.setOpaque(false);
		thirdColButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bo=logic.getMove(bo,2);
				//need to add piece to board, this is next step
			}
		});
		JButton fourthColButton=new JButton("");
		fourthColButton.setBounds(350,115,100,600);
		fourthColButton.setOpaque(false);
		fourthColButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bo=logic.getMove(bo,3);
				//need to add piece to board, this is next step
			}
		});
		JButton fifthColButton=new JButton("");
		fifthColButton.setBounds(450,115,100,600);
		fifthColButton.setOpaque(false);
		fifthColButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bo=logic.getMove(bo,4);
				//need to add piece to board, this is next step
			}
		});
		JButton sixthColButton=new JButton("");
		sixthColButton.setBounds(550,115,100,600);
		sixthColButton.setOpaque(false);
		sixthColButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bo=logic.getMove(bo,5);
				//need to add piece to board, this is next step
			}
		});
		JButton seventhColButton=new JButton("");
		seventhColButton.setBounds(650,115,100,600);
		seventhColButton.setOpaque(false);
		seventhColButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				bo=logic.getMove(bo,6);
				//need to add piece to board, this is next step
			}
		});
		
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

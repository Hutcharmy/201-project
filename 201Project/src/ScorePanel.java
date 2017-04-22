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

public class ScorePanel extends JLayeredPane {
	private MainFrame frame;
	public ScorePanel(MainFrame frame){
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		this.frame=frame;
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("Score menu (1).jpg"));
		backgroundImage.setBounds(0, 0, 825, 850);
		this.add(backgroundImage);
		
		int[] scoreArray = Score.scoreArray();
		
		//Easy printout -------------------------------------------------------------
		if(Integer.toString(scoreArray[0]).length() == 1){
			JLabel printEasyWins = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[0]) + ".png"));
			printEasyWins.setBounds(190, 430, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyWins, JLayeredPane.DRAG_LAYER);
		}
		else if(Integer.toString(scoreArray[0]).length() == 2){
			JLabel printEasyWins = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[0]).charAt(0) + ".png"));
			printEasyWins.setBounds(170, 430, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyWins, JLayeredPane.DRAG_LAYER);
			
			JLabel printEasyWins2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[0]).charAt(1) + ".png"));
			printEasyWins2.setBounds(205, 430, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyWins2, JLayeredPane.DRAG_LAYER);
		}
		else System.err.println("given player score not the correct length!");
		
		if(Integer.toString(scoreArray[1]).length() == 1){
			JLabel printEasyLosses = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[1]) + ".png"));
			printEasyLosses.setBounds(520, 430, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses, JLayeredPane.DRAG_LAYER);
		}
		else if(Integer.toString(scoreArray[0]).length() == 2){
			JLabel printEasyLosses = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[1]).charAt(0) + ".png"));
			printEasyLosses.setBounds(505, 430, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses, JLayeredPane.DRAG_LAYER);
			
			JLabel printEasyLosses2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[1]).charAt(1) + ".png"));
			printEasyLosses2.setBounds(540, 430, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses2, JLayeredPane.DRAG_LAYER);
		}
		else System.err.println("given player score not the correct length!");
		
		//Medium printout -------------------------------------------------------------
		if(Integer.toString(scoreArray[2]).length() == 1){
			JLabel printMedWins = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[2]) + ".png"));
			printMedWins.setBounds(190, 505, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printMedWins, JLayeredPane.DRAG_LAYER);
		}
		else if(Integer.toString(scoreArray[2]).length() == 2){
			JLabel printMedWins = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[2]).charAt(0) + ".png"));
			printMedWins.setBounds(170, 505, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printMedWins, JLayeredPane.DRAG_LAYER);
			
			JLabel printMedWins2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[2]).charAt(1) + ".png"));
			printMedWins2.setBounds(205, 505, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printMedWins2, JLayeredPane.DRAG_LAYER);
		}
		else System.err.println("given player score not the correct length!");
		
		if(Integer.toString(scoreArray[3]).length() == 1){
			JLabel printEasyLosses = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[3]) + ".png"));
			printEasyLosses.setBounds(520, 505, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses, JLayeredPane.DRAG_LAYER);
		}
		else if(Integer.toString(scoreArray[3]).length() == 2){
			JLabel printEasyLosses = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[3]).charAt(0) + ".png"));
			printEasyLosses.setBounds(505, 505, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses, JLayeredPane.DRAG_LAYER);
			
			JLabel printEasyLosses2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[3]).charAt(1) + ".png"));
			printEasyLosses2.setBounds(540, 505, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses2, JLayeredPane.DRAG_LAYER);
		}
		else System.err.println("given player score not the correct length!");
		
		//Hard printout -------------------------------------------------------------
		if(Integer.toString(scoreArray[4]).length() == 1){
			JLabel printHardWins = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[4]) + ".png"));
			printHardWins.setBounds(190, 580, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printHardWins, JLayeredPane.DRAG_LAYER);
		}
		else if(Integer.toString(scoreArray[4]).length() == 2){
			JLabel printHardWins = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[4]).charAt(0) + ".png"));
			printHardWins.setBounds(170, 580, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printHardWins, JLayeredPane.DRAG_LAYER);
			
			JLabel printHardWins2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[4]).charAt(1) + ".png"));
			printHardWins2.setBounds(205, 580, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printHardWins2, JLayeredPane.DRAG_LAYER);
		}
		else System.err.println("given player score not the correct length!");
		
		if(Integer.toString(scoreArray[5]).length() == 1){
			JLabel printEasyLosses = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[5]) + ".png"));
			printEasyLosses.setBounds(520, 580, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses, JLayeredPane.DRAG_LAYER);
		}
		else if(Integer.toString(scoreArray[5]).length() == 2){
			JLabel printEasyLosses = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[5]).charAt(0) + ".png"));
			printEasyLosses.setBounds(505, 580, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses, JLayeredPane.DRAG_LAYER);
			
			JLabel printEasyLosses2 = new JLabel(new ImageIcon("GUI_nums/" + Integer.toString(scoreArray[5]).charAt(1) + ".png"));
			printEasyLosses2.setBounds(540, 580, 75, 75);
			//printPlayerScore.setOpaque(true);
			this.add(printEasyLosses2, JLayeredPane.DRAG_LAYER);
		}
		else System.err.println("given player score not the correct length!");
		
		JButton clearButton = new JButton("");
		clearButton.setBounds(145,673,240,73);
		clearButton.setOpaque(true);
		
		clearButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Score.resetScores();
				frame.changeContentPane(new MainJPanel(frame));
				frame.changeContentPane(new ScorePanel(frame));
			}
		});
		
		this.add(clearButton);
		this.setVisible(true);
		
		JButton backButton=new JButton("");
		backButton.setBounds(453,673,208,73);
		backButton.setOpaque(false);
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeContentPane(new MainJPanel(frame));
			}
		});
		this.add(backButton);
		this.setVisible(true);

	}
}

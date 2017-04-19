import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class WinPanel extends JLayeredPane {
	MainFrame frame;
	EndgameFrame top;
	private int playerScore, AIScore;
	public WinPanel(int win, EndgameFrame frame1, MainFrame frame2){
		top=frame1;
		this.frame=frame2;
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		if(win==1){
			backgroundImage.setIcon(new ImageIcon("Win menu.jpg"));
		}
		else if(win==0){
			backgroundImage.setIcon(new ImageIcon("Draw.jpg"));
		}
		else if(win==-1){
			backgroundImage.setIcon(new ImageIcon("Loss menu.jpg"));
		}
		backgroundImage.setBounds(0, 0, 500, 500);
		this.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
		
		JButton playButton = new JButton("");
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SettingsFrame frame2=new SettingsFrame(frame);
				frame2.setVisible(true);
				top.dispose();
			}
		});
		playButton.setOpaque(false);
		playButton.setBounds(20, 420, 230, 60);
		add(playButton);
		JButton menuButton = new JButton("");
		menuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.changeContentPane(new MainJPanel(frame));
				top.dispose();
			}
		});
		menuButton.setOpaque(false);
		menuButton.setBounds(255, 420, 230, 60);
		add(menuButton);
		
		playerScore=Score.getScore(frame.getAIDifficulty(), true);
		AIScore=Score.getScore(frame.getAIDifficulty(), false);
		
		JLabel printPlayerScore = new JLabel(Integer.toString(playerScore));
		printPlayerScore.setBounds(100, 240, 75, 75);
		printPlayerScore.setFont(new Font("Serif", Font.BOLD, 70));
		printPlayerScore.setForeground(Color.BLACK);
		//printPlayerScore.setOpaque(true);
		this.add(printPlayerScore, JLayeredPane.DRAG_LAYER);
		
		JLabel printAIScore = new JLabel(Integer.toString(AIScore));
		printAIScore.setBounds(300, 240, 75, 75);
		printAIScore.setFont(new Font("Serif", Font.BOLD, 70));
		printAIScore.setForeground(Color.BLACK);
		this.add(printAIScore, JLayeredPane.DRAG_LAYER);
	}
}

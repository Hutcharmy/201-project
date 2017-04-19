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
		
		JLabel easyWins = new JLabel(Integer.toString(scoreArray[0]));
		easyWins.setBounds(200, 410, 75, 75);
		easyWins.setFont(new Font("Serif", Font.BOLD, 70));
		easyWins.setForeground(Color.BLACK);
		//printPlayerScore.setOpaque(true);
		this.add(easyWins, JLayeredPane.DRAG_LAYER);
		
		JLabel easyLosses = new JLabel(Integer.toString(scoreArray[1]));
		easyLosses.setBounds(530, 410, 75, 75);
		easyLosses.setFont(new Font("Serif", Font.BOLD, 70));
		easyLosses.setForeground(Color.BLACK);
		this.add(easyLosses, JLayeredPane.DRAG_LAYER);
		
		JLabel medWins = new JLabel(Integer.toString(scoreArray[2]));
		medWins.setBounds(200, 480, 75, 75);
		medWins.setFont(new Font("Serif", Font.BOLD, 70));
		medWins.setForeground(Color.BLACK);
		//printPlayerScore.setOpaque(true);
		this.add(medWins, JLayeredPane.DRAG_LAYER);
		
		JLabel medLosses = new JLabel(Integer.toString(scoreArray[3]));
		medLosses.setBounds(530, 480, 75, 75);
		medLosses.setFont(new Font("Serif", Font.BOLD, 70));
		medLosses.setForeground(Color.BLACK);
		this.add(medLosses, JLayeredPane.DRAG_LAYER);
		
		JLabel hardWins = new JLabel(Integer.toString(scoreArray[4]));
		hardWins.setBounds(200, 550, 75, 75);
		hardWins.setFont(new Font("Serif", Font.BOLD, 70));
		hardWins.setForeground(Color.BLACK);
		//printPlayerScore.setOpaque(true);
		this.add(hardWins, JLayeredPane.DRAG_LAYER);
		
		JLabel hardLosses = new JLabel(Integer.toString(scoreArray[5]));
		hardLosses.setBounds(530, 550, 75, 75);
		hardLosses.setFont(new Font("Serif", Font.BOLD, 70));
		hardLosses.setForeground(Color.BLACK);
		this.add(hardLosses, JLayeredPane.DRAG_LAYER);
		
		JLabel easy = new JLabel("E");
		easy.setBounds(362, 406, 75, 75);
		easy.setFont(new Font("Serif", Font.BOLD, 70));
		easy.setForeground(Color.BLACK);
		this.add(easy, JLayeredPane.DRAG_LAYER);
		
		JLabel medium = new JLabel("M");
		medium.setBounds(351, 480, 75, 75);
		medium.setFont(new Font("Serif", Font.BOLD, 70));
		medium.setForeground(Color.BLACK);
		this.add(medium, JLayeredPane.DRAG_LAYER);
		
		JLabel hard = new JLabel("H");
		hard.setBounds(357, 550, 75, 75);
		hard.setFont(new Font("Serif", Font.BOLD, 70));
		hard.setForeground(Color.BLACK);
		this.add(hard, JLayeredPane.DRAG_LAYER);
		
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

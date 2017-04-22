import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainJPanel extends JLayeredPane {
	private MainJPanel panel;
	private MainFrame frame;
	MainJPanel(MainFrame frame){
	panel=this;
	this.frame=frame;
	setBackground(Color.GRAY);
	setBorder(new EmptyBorder(5, 5, 5, 5));
	setLayout(null);
	
	JLabel backgroundImage = new JLabel("");
	backgroundImage.setVerticalAlignment(SwingConstants.TOP);
	backgroundImage.setIcon(new ImageIcon("Main Menu Mock-up 1.png"));
	backgroundImage.setBounds(0, 0, 825, 850);
	add(backgroundImage);
	
	JButton playButton = new JButton("");
	playButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			SettingsFrame frame2=new SettingsFrame(frame);
			frame2.setVisible(true);
		}
	});
	playButton.setOpaque(false);
	playButton.setBounds(290, 334, 225, 99);
	add(playButton);
	
	JButton exitButton = new JButton("");
	exitButton.setBounds(301, 642, 200, 77);
	exitButton.setOpaque(false);
	exitButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			double wins=MainFrame.wins;
			double losses=MainFrame.losses;
			if(wins==0&&losses==0){
				System.exit(0);
			}
			try{
				double percentage=wins/(wins+losses);
				NumberFormat defaultFormat = NumberFormat.getPercentInstance();
				defaultFormat.setMinimumFractionDigits(1);
				int result=JOptionPane.showConfirmDialog(frame, "Your score percentage for this round was "
													+defaultFormat.format(percentage), "Exit", JOptionPane.DEFAULT_OPTION);
				if(result==0) System.exit(0);
			}
			catch(ArithmeticException e){
				System.exit(0);
			}
		}
	});
	add(exitButton);
	
	
	JButton aboutButton= new JButton("");
	aboutButton.setOpaque(false);
	aboutButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg){
			frame.changeContentPane(frame.aboutMenuPanel);
		}
	});
	aboutButton.setBounds(250,540,300,80);
	add(aboutButton);
	
	JButton scoreButton=new JButton("");
	scoreButton.setBounds(235,440,330,80);
	scoreButton.setOpaque(false);
	scoreButton.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent arg){
			frame.changeContentPane(frame.scoresPanel);
		}
	});
	add(scoreButton);
	}
}

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
	public PlayPanel(MainFrame frame){
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		this.frame=frame;
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("Game Slide 1.jpg"));
		backgroundImage.setBounds(0, 0, 825, 850);
		this.add(backgroundImage);
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
		this.add(backButton);
		this.setVisible(true);
	}
		
}

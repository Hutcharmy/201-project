import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel {
	public ScorePanel(){
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("Score menu (1).jpg"));
		backgroundImage.setBounds(0, 0, 825, 850);
		this.add(backgroundImage);
		this.setVisible(true);
		
		JButton backButton=new JButton("");
		backButton.setBounds(440,60,660,120);
		backButton.setOpaque(false);
		backButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
	}
}

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class AboutMenu extends JLayeredPane {
	MainFrame frame;
	AboutMenu(MainFrame frame){
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		this.frame=frame;
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("About menu edit.jpg"));
		backgroundImage.setBounds(0, 0, 825, 850);
		this.add(backgroundImage);
		
		
		JButton backButton=new JButton("");
		backButton.setBounds(300,720,200,73);
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

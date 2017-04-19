import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SettingsPanel extends JLayeredPane {
	MainFrame frame;
	SettingsFrame top;
	public SettingsPanel(SettingsFrame top, MainFrame menuFrame){
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		this.frame=menuFrame;
		this.top=top;
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("Settings selection.jpg"));
		backgroundImage.setBounds(0, 0, 500, 500);
		this.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
		
		JButton playButton=new JButton("");
		playButton.setBounds(400,440,90,50);
		playButton.setOpaque(true);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeContentPane(new PlayPanel(frame));
				top.dispose();
			}
		});
		frame.setRed(false);
		frame.setAIDifficulty(0);
		JButton yellow=new JButton("");
		yellow.setActionCommand("yellow");
		yellow.setBounds(130,75,100,100);
		yellow.setOpaque(false);
		
		JButton red=new JButton("");
		red.setActionCommand("red");
		red.setBounds(285, 75, 100,100);
		red.setOpaque(false);
		
		
		ActionListener colorAction=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("yellow")){
					frame.setRed(false);
				}
				else if(e.getActionCommand().equals("red")){
					frame.setRed(true);
				}
			}
		};
		yellow.addActionListener(colorAction);
		red.addActionListener(colorAction);
		
		JButton easy=new JButton("easy");
		easy.setActionCommand("easy");
		easy.setBounds(195, 290, 120, 50);
		easy.setOpaque(false);
		JButton medium=new JButton("medium");
		medium.setActionCommand("medium");
		medium.setBounds(175, 360, 155, 50);
		medium.setOpaque(false);
		JButton hard=new JButton("hard");
		hard.setActionCommand("hard");
		hard.setBounds(195, 430, 120, 50);
		hard.setOpaque(false);
	
		ActionListener diffAction=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("easy")){
					frame.setAIDifficulty(0);
					System.out.println("Easy");
				}
				else if(e.getActionCommand().equals("medium")){
					frame.setAIDifficulty(1);
					System.out.println("Medium");
				}
				else if(e.getActionCommand().equals("hard")){
					frame.setAIDifficulty(2);
					System.out.println("Hard");
				}
			}
		};
		easy.addActionListener(diffAction);
		medium.addActionListener(diffAction);
		hard.addActionListener(diffAction);
		System.out.println("Adding");
		this.add(red, JLayeredPane.DEFAULT_LAYER);
		this.add(yellow, JLayeredPane.DEFAULT_LAYER);
		this.add(easy, JLayeredPane.DEFAULT_LAYER);
		this.add(medium, JLayeredPane.DEFAULT_LAYER);
		this.add(hard, JLayeredPane.DEFAULT_LAYER);
		System.out.println("Added");
		this.add(playButton, JLayeredPane.PALETTE_LAYER);
		this.setVisible(true);
	}
}

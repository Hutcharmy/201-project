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
/** 
 * Displays start game settings and allows user to make the game operate how they want
 * @author nick
 *
 */
public class SettingsPanel extends JLayeredPane {
	MainFrame frame;
	SettingsFrame top;
	/**
	 * Creates the panel from an image, places buttons on relevant components, and allows user to start game
	 * @param top The frame this panel is stored in, used to exit when this panel is done
	 * @param menuFrame The main frame of the program, used to go to play screen
	 */
	public SettingsPanel(SettingsFrame top, MainFrame menuFrame){
		this.frame=menuFrame;
		this.top=top;
		//Basic frame settings
		setBackground(Color.GRAY);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		//Sets background to JPG from package
		JLabel backgroundImage=new JLabel("");
		backgroundImage.setVerticalAlignment(SwingConstants.TOP);
		backgroundImage.setIcon(new ImageIcon("Settings selection.jpg"));
		backgroundImage.setBounds(0, 0, 500, 500);
		this.add(backgroundImage, JLayeredPane.DEFAULT_LAYER);
		//Adds button to start game
		
		//Default values of settings 
		frame.setRed(false);
		frame.setAIDifficulty(0);
		//Buttons for color choice, placed over icons in interface
		JButton yellow=new JButton("");
		yellow.setActionCommand("yellow");
		yellow.setBounds(130,75,100,100);
		yellow.setOpaque(false);
		
		JButton red=new JButton("");
		red.setActionCommand("red");
		red.setBounds(285, 75, 100,100);
		red.setOpaque(false);
		
		JLabel arrow=new JLabel("");
		arrow.setVerticalAlignment(SwingConstants.TOP);
		arrow.setIcon(new ImageIcon("arrow (2).png"));
		arrow.setBounds(90,115,55,55);
		
		
		//Changes the player color in the main frame based on user choice
		ActionListener colorAction=new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("yellow")){
					frame.setRed(false);
					arrow.setBounds(90,115,55,55);
				}
				else if(e.getActionCommand().equals("red")){
					frame.setRed(true);
					arrow.setBounds(245,115,55,55);
				}
			}
		};
		yellow.addActionListener(colorAction);
		red.addActionListener(colorAction);
		//Adds difficulty buttons
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
		//Changes difficulty in main frame to user choice
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
				frame.changeContentPane(new PlayPanel(frame));
				top.dispose();
			}
		};
		easy.addActionListener(diffAction);
		medium.addActionListener(diffAction);
		hard.addActionListener(diffAction);
		//Adds everything and sets to visible
		this.add(red, JLayeredPane.DEFAULT_LAYER);
		this.add(yellow, JLayeredPane.DEFAULT_LAYER);
		this.add(easy, JLayeredPane.DEFAULT_LAYER);
		this.add(medium, JLayeredPane.DEFAULT_LAYER);
		this.add(hard, JLayeredPane.DEFAULT_LAYER);
		this.add(arrow, JLayeredPane.PALETTE_LAYER);
		this.setVisible(true);
	}
}

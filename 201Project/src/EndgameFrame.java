import javax.swing.JFrame;
/**
 * A frame to store the win/loss/draw popups
 * @author nick
 *
 */
public class EndgameFrame extends JFrame {
	MainFrame frame;
	/**
	 * Creates the frame and makes it visible, creates WinPanel to store info
	 * @param win the current win condition that triggered this frame
	 * 1 is user win, 0 is draw, -1 is AI win
	 * @param frame the main frame of the client, used in panel to switch and exit at end of runtime
	 */
	public EndgameFrame(int win, MainFrame frame){
		this.frame=frame;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(new WinPanel(win, this, frame));
		setBounds(100, 100, 510, 550);
		this.setResizable(false);
		this.setVisible(true);
	}
}

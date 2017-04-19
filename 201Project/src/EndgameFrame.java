import javax.swing.JFrame;

public class EndgameFrame extends JFrame {
	MainFrame frame;
	public EndgameFrame(int win, MainFrame frame){
		this.frame=frame;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(new WinPanel(win, this, frame));
		setBounds(100, 100, 510, 550);
		this.setResizable(false);
		this.setVisible(true);
	}
}

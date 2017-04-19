import javax.swing.JFrame;

public class SettingsFrame extends JFrame {
	MainFrame frame;
	public SettingsFrame(MainFrame frame){
		this.frame=frame;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(new SettingsPanel(this, frame));
		setBounds(100, 100, 510, 550);
		this.setResizable(false);
		this.setVisible(true);
	}
}

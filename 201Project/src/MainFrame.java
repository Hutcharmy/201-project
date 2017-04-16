import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	public JPanel contentPane;
	public JPanel scoresPanel, aboutMenuPanel, playPanel, mainJPanel;
	public	MainFrame() {
		playPanel=new PlayPanel(this);
		scoresPanel=new ScorePanel(this);
		aboutMenuPanel=new AboutMenu(this);
		mainJPanel=new MainJPanel(this);
		Score.checkSaveFileExists();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(mainJPanel);
		setBounds(100, 100, 825, 850);
		this.setResizable(false);
		
	}
	public void changeContentPane(JPanel newPanel){
		System.out.println(newPanel instanceof MainJPanel);
		if (newPanel instanceof MainJPanel){
			newPanel=new MainJPanel(this);
			System.out.println("mainpanel");
		}
		else if (newPanel instanceof ScorePanel){
			newPanel=new ScorePanel(this);
			System.out.println("Try");
		}
		else if(newPanel instanceof PlayPanel){
			newPanel=new PlayPanel(this);
			System.out.println("playtime's over kiddo");
		}
		else if(newPanel instanceof AboutMenu){
			newPanel=new AboutMenu(this);
			System.out.println("aboot");
		}
		System.out.println("Ths ran");
		this.getContentPane().removeAll();
		this.setContentPane(newPanel);
		newPanel.revalidate();
		this.repaint();
		setVisible(true);
	}
}

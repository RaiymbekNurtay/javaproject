import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	GameFrame(){
		
		GamePanel panel = new GamePanel();
		this.add(panel);
		this.setTitle("SnakeXenzia"); //Title of the window
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close operation
		this.setResizable(false); //Window is resizable or not 
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null); //location of the window
	}

}

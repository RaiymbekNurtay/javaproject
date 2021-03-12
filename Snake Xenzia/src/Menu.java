import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Menu implements ActionListener{
	JFrame frame;
	JButton btn;
	JButton btn2;
	JButton btn3;
	
	public Menu(){
		
		//creating button start
		btn = new JButton("Start game");
		//positioning the button
		btn.setBounds(180, 100, 130, 70);
		//attach the actionListener to the button
		btn.addActionListener(this);
		btn.setBackground(Color.blue);
		btn.setFont(new Font("Comic Sans", Font.BOLD, 12));
		//setting the font rgb color
		btn.setForeground(new Color(250, 250, 250));
		btn.setFocusable(false);
		
		//creating button leaderboard
		btn2 = new JButton("Leaderboard");
		//positioning the button
		btn2.setBounds(180, 200, 130, 70);
		//attach the actionListener to the button
		btn2.addActionListener(this);
		btn2.setBackground(Color.blue);
		btn2.setFont(new Font("Comic Sans", Font.BOLD, 12));
		//setting the font rgb color
		btn2.setForeground(new Color(250, 250, 250));
		btn2.setFocusable(false);
		
		//creating button exit
		btn3 = new JButton("Exit");
		//positioning the button
		btn3.setBounds(180, 300, 130, 70);
		//attach the actionListener to the button
		btn3.addActionListener(this);
		btn3.setBackground(Color.blue);
		btn3.setFont(new Font("Comic Sans", Font.BOLD, 12));
		//setting the font rgb color
		btn3.setForeground(new Color(250, 250, 250));
		btn3.setFocusable(false);
		
		
		
		
		frame = new JFrame("Main menu");
		frame.setSize(new Dimension(500,500)); 
		//absolute positioning
		frame.setLayout(null);
		frame.add(btn);
		frame.add(btn2);
		frame.add(btn3);
		frame.setVisible(true);	
		frame.setLocationRelativeTo(null); //location of the window
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn) {
		new GameFrame();
		}
		
		if(e.getSource() == btn2) {
			Leaderboard board = new Leaderboard();
		}
		
		if(e.getSource() == btn3) {
		System.exit(0);
		}
	}
}
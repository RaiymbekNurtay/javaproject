import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Leaderboard extends JFrame implements ActionListener{
		JFrame frame;
		JButton menu;
		PostgreSqlConnect conn = new PostgreSqlConnect();
		
			Leaderboard(){
				frame = new JFrame("Leaderboard");
				
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        frame.add(new MyPanel());
		        frame.pack();
		        frame.setVisible(true);
		        
				frame.setSize(new Dimension(500,500));
				//absolute positioning
				frame.setLayout(null);
				frame.setVisible(true);	
				frame.setLocationRelativeTo(null); //location of the window
				conn.select();
				//creating button leaderboard
				menu = new JButton("Main Menu");
				//positioning the button
				menu.setBounds(180, 300, 130, 70);
				//attach the actionListener to the button
				menu.addActionListener(this);
				menu.setBackground(Color.blue);
				menu.setFont(new Font("Comic Sans", Font.BOLD, 12));
				//setting the font rgb color
				menu.setForeground(new Color(250, 250, 250));
				menu.setFocusable(false);
				frame.add(menu);
		}
			
			
			public void paintComponent(Graphics g) {
				draw(g);
			}
			
			
			public void draw(Graphics g) {	
				g.setColor(Color.YELLOW);
				g.setFont(new Font("Oswald", Font.BOLD, 90));
				//position of the font
				FontMetrics metrics = getFontMetrics(g.getFont());
				for(int i = 0; i < conn.scores.size(); i++) {
					System.out.println(conn.scores.get(i));
				}
				
			}
			
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == menu) {
					new Menu();
					this.setVisible(false);
					}
				repaint();
			}
	}

class MyPanel extends JPanel {

    public MyPanel() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(500,500);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       
        PostgreSqlConnect con = new PostgreSqlConnect();
    		con.select();
    		g.setColor(Color.black);
    		g.setFont(new Font("Oswald", Font.BOLD, 25));
	        // Draw Text
	    	int x = 30;
	    	int y = 10;
	        for(int i = 0; i < con.scores.size(); i++) {
	        g.drawString(i+1 + " . " + con.scores.get(i), 200 , x);
	        x+=30;
        
        }
    }  
}


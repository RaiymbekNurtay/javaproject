import java.awt.*;			//to develop window-based apps
import java.awt.event.*;	//provides interfaces and classes for dealing with different types of events
import javax.swing.*;		//graphical user interface toolkit 
import java.util.Random;	//to generate random numbers
import javax.swing.JPanel;	//creates a new panel with flow layout

public class GamePanel extends JPanel implements ActionListener{
	
	static final int Screen_width = 600; 	//width of the screen
	static final int Screen_height = 600; 	//height of the screen
	static final int Unit_size = 25; 	//each unit will have 25 pixel size
	static final int Game_units = (Screen_width * Screen_height / Unit_size);	
	static final int Delay = 75;		//speed of the snake
	final int x[] = new int[Game_units]; 	//x coordinates of the snake, game units because in cannot be bigger than screen
	final int y[] = new int[Game_units]; 	//y coordinates of the snake
	int bodyParts = 6;		//length of the snake
	int applesEaten;
	int appleX;			//x coordinate of an apple
	int appleY;			//y coordinate of an apple
	char direction = 'R'; //R means right
	boolean running = false;		//is program running
	Timer timer;
	Random random;
	
	//constructor
	GamePanel(){
		random = new Random();
		this.setPreferredSize(new Dimension(Screen_width, Screen_height));		//Size of the screen
		this.setBackground(Color.black);		//background color of the screen
		this.setFocusable(true);			//set focus
		this.addKeyListener(new MyKeyAdapter());	
		startGame();
	}
	
	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(Delay, this);
		timer.start();
	}
	
	//method for painting
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		if(running) {
				for(int i=0; i<Screen_height/Unit_size; i++) {
					g.drawLine(i*Unit_size, 0, i*Unit_size, Screen_height);  //making grid
					g.drawLine(0, i*Unit_size, Screen_width, i*Unit_size);	 //making grid
				}
				g.setColor(Color.red);		//color of an apple
				g.fillOval(appleX , appleY, Unit_size, Unit_size);		//Size of an apple
				
				for(int i=0; i<bodyParts; i++) {
					if(i == 0) {
						g.setColor(Color.green);
						g.fillRect(x[i], y[i], Unit_size, Unit_size);
					}
					else {
						g.setColor(new Color(45, 180, 0));
						g.fillRect(x[i], y[i], Unit_size, Unit_size);
					}
				}
		}
		else {
			gameOver(g);
		}
	}
	
	public void newApple() {		//generates the coordinates of new apple
		appleX = random.nextInt((int)(Screen_width/Unit_size)) * Unit_size;	//x coordinate of the apple
		appleY = random.nextInt((int)(Screen_height/Unit_size)) * Unit_size;
	}
	
	public void move() {
		for(int i = bodyParts; i>0; i--) {
			x[i] = x[i-1];
			y[i] = y[i-1];
		}
		
		switch(direction) {
		case 'U':		//up direction
			y[0] = y[0] - Unit_size;
			break;
		case 'D':		//down direction
			y[0] = y[0] + Unit_size;
			break;
		case 'L':		//left direction
			x[0] = x[0] - Unit_size;
			break;
		case 'R':		//right direction
			x[0] = x[0] + Unit_size;
			break;
		}
			
	}
	
	public void checkApple() {
		//checking if the coordinates of the head of the snake and apple are equal
		if((x[0] == appleX) && (y[0] == appleY)) {
			//if true than iincrease body parts and amount of apple eaten
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}
	
	public void checkCollisions() {
		//checking if the head of the snake touches the body
		for(int i = bodyParts; i>0; i--) {		
			if((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		//checking if the head of the snake touches the left border
		if(x[0] < 0 ) {
			running = false;
		}
		//checking if the head of the snake touches the right border
				if(x[0] > Screen_width) {
					running = false;
				}
		//checking if the head of the snake touches the top border
		if(y[0] < 0) {
			running = false;
		}
		//checking if the head of the snake touches the bottom border
				if(y[0] > Screen_height) {
					running = false;
				}
				
		if(!running) {
			timer.stop();
		}
	}
	
	public void gameOver(Graphics g) {
		//text
		g.setColor(Color.YELLOW);
		g.setFont(new Font("Oswald", Font.BOLD, 90));
		//position of the font
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (Screen_width - metrics.stringWidth("Game Over"))/2, (Screen_height/2));
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(running) {
			move();
			checkApple();
			checkCollisions();
		}
		repaint();
	}
	
	public class MyKeyAdapter extends KeyAdapter{
		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				//doesnt allow to turn 180 degree only 90
				if(direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				//doesnt allow to turn 180 degree only 90
				if(direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				//doesnt allow to turn 180 degree only 90
				if(direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				//doesnt allow to turn 180 degree only 90
				if(direction != 'U') {
					direction = 'D';
				}
				break;
			}
		}
	}

}

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play;
	private int score;
	private int cols = 8;
	private int rows = 8;
	private int totalBricks = cols*rows;
	private Map map;

	private Timer timer;
	private int delay = 8;

	private int xPaddle = 425;
	private int yPaddle = 700;

	private int xBall = 100;
	private int yBall = 400;
	private int xBallDir = -5;
	private int yBallDir = -10;

	public Gameplay() {
		map = new Map(cols, rows);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	public void paint(Graphics g) {
		//Background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 1000, 800);

		// Map draw
		map.draw((Graphics2D) g);

		// Paddle
		g.setColor(Color.GREEN);
		g.fillRect(xPaddle, yPaddle, 120, 10);

		// Ball
		g.setColor(Color.YELLOW);
		g.fillOval(xBall, yBall, 25, 25);

		g.setColor(Color.WHITE);
		g.drawString("Score= " + score, 900, 20);

		if(!play && totalBricks==0) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 75));
			g.drawString("GAME OVER! Total Score= " + score, 10, 400);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			//CHECK LOGIC

			if(new Rectangle(xBall, yBall, 25, 25).intersects(new Rectangle(xPaddle, yPaddle, 120, 10))) yBallDir = -yBallDir;



			A: for(int i=0; i<map.map.length; i++) {
				for(int j=0; j<map.map[i].length; j++) {
					if(map.map[i][j] > 0) {
						int brickX = j*map.width+150;
						int brickY = i*map.height+50;
						Rectangle ballRect = new Rectangle(xBall, yBall, 25, 25);
						Rectangle brickRect = new Rectangle(brickX, brickY, map.width, map.height);

						//CHECK LOGIC
						if(ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							score++;
							
							
							if(xBall + 24 <= brickRect.x || xBall + 1 >= brickRect.x + brickRect.width) xBallDir = -xBallDir;
							else yBallDir = -yBallDir;
							
							break A;
						}
					}
				}
			}

			xBall += xBallDir;
			yBall += yBallDir;
			if(xBall < 0) xBallDir = -xBallDir;
			if(xBall > 960) xBallDir = -xBallDir;
			if(yBall < 0) yBallDir = -yBallDir;
			if(yBall > 740) yBallDir = -yBallDir;

			if(yBall>yPaddle || totalBricks==0) {
				play = false;
				this.removeKeyListener(this);
				totalBricks = 0;
				repaint();
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		if(keyCode==KeyEvent.VK_LEFT) {
			if(xPaddle<=0) xPaddle = 0;
			else moveLeft();
		}
		else if(keyCode==KeyEvent.VK_RIGHT) {
			if(xPaddle>=880) xPaddle = 880;
			else moveRight();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	public void moveRight() {
		play = true;
		xPaddle += 50;
	}
	public void moveLeft() {
		play = true;
		xPaddle -= 50;
	}

}

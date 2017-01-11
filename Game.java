import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

public class Game extends JFrame {
	public final int WIDTH = 640;
	public final int HEIGHT = 480;
	public final int SPRITES_WIDTH = 30;

	public static void main(String[] args) {
		new Game();
	}

	public Game() {
		super("Game v0.4");
		setSize(WIDTH, HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		gameField gf = new gameField();
		getContentPane().add(gf);

		setVisible(true);
	}
}

class gameField extends JPanel implements ActionListener {
	GRectangle redBox;
	GRectangleInverted blueBox;
	Triangle enemy;
	TriangleInverted blueEnemy;
	GameScorer scorer;

	public final int BOX_WIDTH = 30;
	public Timer timer;

	boolean gameOver;

	public gameField() {
		redBox = new GRectangle(290, 200, BOX_WIDTH, BOX_WIDTH);
		addKeyListener(new KeyTransporter());

		blueBox = new GRectangleInverted(320, 230, BOX_WIDTH, BOX_WIDTH);
		addKeyListener(new KeyTransporter());

		enemy = new Triangle(710, 230, BOX_WIDTH, BOX_WIDTH);
		blueEnemy = new TriangleInverted(-30, 230, BOX_WIDTH, BOX_WIDTH);

		scorer = new GameScorer();

		setFocusable(true);
		timer = new Timer(10, this);
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (didCollide(blueBox, blueEnemy)) {
			gameOver = true;
			timer.stop();
		}
		if (didCollide(redBox, enemy)) {
			gameOver = true;
			timer.stop();
		}

		repaint();
		//System.out.println("x: " + blueBox.x);
		//System.out.println("y: " + blueBox.y);
	}

	@Override
	public void paintComponent(Graphics g) {

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 460 / 2);
		g.setColor(Color.RED);
		g.fillRect(0, 460/2, 640, 460 / 2);
		//g.clearRect(0, 0, WIDTH, HEIGHT);
		redBox.draw(g);
		blueBox.draw(g);
		enemy.draw(g);
		blueEnemy.draw(g);

		g.setColor(Color.RED);
		scorer.draw(g);



		if (gameOver) {

			g.clearRect(0, 0, 640, 480);
			scorer.saveScore();
			g.setColor(Color.BLACK);
			g.drawString("Your score: " + scorer.score, 235, 200);
			g.drawString("Highest score: " + scorer.highestScore, 235, 230);
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();

	}

	private boolean didCollide(GRectangle r, Triangle t) {
		if ((r.y + BOX_WIDTH >= t.y[1] && (r.x + BOX_WIDTH >= t.x[0] && r.x + BOX_WIDTH <= t.x[2])))
			return true;
		return false;
	}

	private boolean didCollide(GRectangleInverted r, TriangleInverted t) {
		if ((t.x[2] >= r.x && t.x[2] <= r.x + BOX_WIDTH) && t.y[1] >= r.y) 
			return true;
		return false;
	}

	private class KeyTransporter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			redBox.keyPressed(e);
			blueBox.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			redBox.keyReleased(e);
			blueBox.keyReleased(e);
		}

	}

}


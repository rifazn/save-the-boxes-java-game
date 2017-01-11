import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.*;

public class GRectangle extends Rectangle {
	// public int x, y, WIDTH, HEIGHT;
	int x, y, w, h;
	int dx, dy;
	boolean goUp;
	boolean goDown;
		int JUMP_HEIGHT = 90;
		int GROUND_LEVEL;

	public GRectangle(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		GROUND_LEVEL = y;

		dx = 2;
		dy = 3;


	}
	
	public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
		jump(key);

		/*if (key == KeyEvent.VK_LEFT) {
			System.out.println("Got left");
			x -= dx;
		}

		if (key == KeyEvent.VK_RIGHT) {
			System.out.println("Got right");
			x += dx;
		}*/

    }

	public void keyReleased(KeyEvent e) {
	}

	public void draw(Graphics g) {
		if (goUp) {
			y -= dy;
		}
		if (goDown) {
			y += dy;
		}
		//g.clearRect(0, 0, 640, 480);
		g.setColor(Color.RED);
		g.fillRect(x, y, w, h);

		if (GROUND_LEVEL - y >= JUMP_HEIGHT) {
			goUp = false;
			goDown = true;
		}

		if ( y >= GROUND_LEVEL) {
			goDown = false;
		}

	}

	void jump(int key) {


		if (!goUp && !goDown) {

			if (key == KeyEvent.VK_UP) {
				goUp = true;
			}
		}
	}
}


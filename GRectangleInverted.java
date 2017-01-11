import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.*;

public class GRectangleInverted extends Rectangle {
	// public int x, y, WIDTH, HEIGHT;
	int x, y, w, h;
	int dx, dy;
	boolean goUp;
	boolean goDown;
		int JUMP_HEIGHT = 90;
		int GROUND_LEVEL;

	public GRectangleInverted(int x, int y, int w, int h) {
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


    }

	public void keyReleased(KeyEvent e) {
	}

	public void draw(Graphics g) {
		if (goDown) {
			y += dy;
		}
		if (goUp) {
			y -= dy;
		}
		//g.clearRect(0, 0, 640, 480);
		g.setColor(Color.BLUE);
		g.fillRect(x, y, w, h);

		if (y - GROUND_LEVEL >= JUMP_HEIGHT) {
			goDown = false;
			goUp = true;
		}

		if ( y <= GROUND_LEVEL) {
			goUp = false;
		}
	}

	void jump(int key) {


		if (!goUp && !goDown) {
			if (key == KeyEvent.VK_DOWN) {
				goDown = true;
			}
		}
	}
}


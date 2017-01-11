import java.awt.Rectangle;
import java.awt.*;
import java.awt.event.*;

public class Triangle {
	int x[];
	int y[];
	int base, height;
	int dx;

	double secondsToWait, secondsWaited;

	public Triangle(int x, int y, int b, int h) {
		this.x = new int[3];
		this.y = new int[3];

		this.x[0] = x;
		this.y[0] = y;
		this.base = b;
		this.height = h;

		this.x[1] = (x + (base / 2) );
		this.x[2] = (x + base);

		this.y[1] = (y - height);
		this.y[2] = this.y[0];

		this.dx = 3;

		timeToWait();
		secondsWaited = 0.0;
	}

	public void draw(Graphics g) {

		if (secondsWaited < secondsToWait) {
			this.sitBackAndWait();
		} else {
			moveEnemy();
		}
		g.setColor(Color.RED);
		g.fillPolygon(new Polygon(x, y, 3));
	}

	private void sitBackAndWait() {
		secondsWaited+=0.010;
	}

	private void timeToWait() {

		while (secondsToWait < 0.5) {
			System.out.println("to wait: " + secondsToWait);
			secondsToWait = Math.random() * 3.0 + 1;
		}
	}

	public void moveEnemy() {
		if (this.x[0] <= 0) {
			x[0] = 640;
			x[1] = x[0] + (base / 2);
			x[2] = x[0] + base;
			timeToWait();
			secondsWaited = 0.0;
		}
		x[0]-=dx;
		x[1]-=dx;
		x[2]-=dx;
	}

}



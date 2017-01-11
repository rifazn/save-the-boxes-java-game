import java.io.*;
import java.awt.Graphics;

class GameScorer {
	int score;
	int highestScore;

	//FileOutputStream fwriter;

	FileReader freader;

	public GameScorer() {
		try {
			freader = new FileReader("highscore.sg");
			BufferedReader reader = new BufferedReader(freader);
			highestScore = Integer.parseInt(reader.readLine());

		} catch(FileNotFoundException ex) {
			highestScore = 0;
		} catch(IOException ex) {
			highestScore = 0;
		} finally {

			score = 0;
		}
	}

	private void score() {
		score += 10;
		if (score >= highestScore) {
			highestScore = score;
		}
	}

	public void saveScore() {

		try {
			FileWriter fout = new FileWriter("highscore.sg");
			BufferedWriter writer = new BufferedWriter(fout);
			writer.write(highestScore + "\n");
			writer.close();
			fout.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics g) {
		score();
		g.drawString("Score: " + score, 50, 30);
	}
}

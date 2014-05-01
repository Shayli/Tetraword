package Jeu;

import java.awt.Color;
import java.awt.Graphics;

public class Worddle extends GameMode {
	protected String word;
	private int playerId;
	private long timeLeft;
	
	public Worddle(Plateau p, int player) {
		super(p);
		word = "";
		playerId = player;
		timeLeft = 1000*60;
	}
	
	public void click(int x, int y) {
		//grille;
	}

	@Override
	public void keyPress(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyRelease(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long msecElapsed) {
		// TODO Auto-generated method stub
		timeLeft -= msecElapsed;
		if(timeLeft < 0)
			plateau.changeMode(new Tetris(plateau, playerId));
	}

	@Override
	public void draw(Graphics g) {
		grille.draw(g);
		g.drawString(word, 0, 0);
		// TODO draw letters selected
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(250+Constants.Padding, 200, 100, 80, 10, 10);
		g.setColor(Color.black);
		g.drawString("Time left", 270 + Constants.Padding, 220);
		int sec = (int)timeLeft/1000;
		g.drawString(""+sec, 270+Constants.Padding, 240);
	}
	
	
	
	
}

package Jeu;

import java.awt.Graphics;

public class Worddle extends GameMode {
	protected String word;
	public Worddle(Grille g) {
		super(g);
		word = "";
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
		
	}

	@Override
	public void draw(Graphics g) {
		grille.draw(g);
		g.drawString(word, 0, 0);
		// TODO draw letters selected
	}
	
	
	
	
}

package Jeu;

import java.awt.Graphics;

public abstract class GameMode {
	protected Grille grille;
	
	GameMode(Grille g) {
		grille = g;
	};
	
	public abstract void click(int x, int y);
	public abstract void keyPress(int keyCode);
	public abstract void keyRelease(int keyCode);
	public abstract void update(long difference);
	public abstract void draw(Graphics g);
}

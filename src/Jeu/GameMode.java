package Jeu;

import java.awt.Graphics;

public abstract class GameMode {
	protected Grille grille;
	protected Plateau plateau;
	protected int playerId;
	protected int[] commands;
	
	GameMode(Plateau p) {
		plateau = p;
		grille = p.grille;
		playerId = plateau.playerId;
		commands = Constants.Commands[playerId];
	};
	
	public abstract void click(int x, int y);
	public abstract void keyPress(int keyCode);
	public abstract void keyRelease(int keyCode);
	public abstract void update(long difference);
	public abstract void draw(Graphics g);
}

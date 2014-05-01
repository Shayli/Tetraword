package Jeu;

import java.awt.Graphics;

import Jeu.Constants.Key;

public class Tetris extends GameMode {

	private boolean fastForward;
	private long elapsed;
	
	public Tetris(Plateau p) {
		super(p);
		fastForward = false;
		elapsed = 0;
	}

	@Override
	public void click(int x, int y) {}

	@Override
	public void keyPress(int keyCode) {
		
		if(keyCode == commands[Key.ROTATE])
			grille.rotateCurrent();
		else if(keyCode == commands[Key.LEFT])
			grille.moveLeftCurrent();
		else if(keyCode == commands[Key.RIGHT])
			grille.moveRightCurrent();
		else if(keyCode == commands[Key.DOWN])
			fastForward = true;
		else if(keyCode == commands[Key.MODE])
			plateau.changeMode(new Worddle(plateau));
		
	}

	@Override
	public void keyRelease(int keyCode) {
		if(keyCode == commands[Key.DOWN])
			fastForward = false;
	}

	@Override
	public void update(long msecElapsed) {
		// TODO Auto-generated method stub
		elapsed += msecElapsed;
		if(fastForward) {
			if(elapsed > 50) {
				grille.update();
				elapsed = 0;
			}
		}
		else {
			if(elapsed > 1000) {
				grille.update();
				elapsed = 0;
				
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {
		grille.draw(g);
	}

}

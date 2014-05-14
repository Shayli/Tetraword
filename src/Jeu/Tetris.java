package Jeu;

import java.awt.Graphics;

import Jeu.Constants.Key;
import Patterns.Observer;
/**
 * Classe Tetris
 * Gère le mode Tetris
 * @author Monia, Laury & André
 * @version 1 
 */
public class Tetris extends GameMode {

	private boolean fastForward, swapAtEnd;
	private long elapsed;
	
	public Tetris(Plateau p) {
		super(p);
		fastForward = false;
		swapAtEnd = false;
		elapsed = 0;
		grille.events.add(new Observer() {

			@Override
			public void notify(String s, Object o) {
				// TODO Auto-generated method stub
				if(s == "block" && swapAtEnd && Constants.takeMouse()) {
					System.out.println("block");
					swapAtEnd = false;
					grille.events.remove(this);
					grille.events.remove(this);
					plateau.changeMode(new Worddle(plateau));
				}
			}
			
		});
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
		else if(keyCode == commands[Key.MODE]) {
			swapAtEnd = true;
			
		}
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
			if(elapsed > 1000-(10-plateau.niveau)*100) {
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

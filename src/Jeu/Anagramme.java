package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Briques.Case;
import Jeu.Constants.Key;

public class Anagramme extends GameMode {

	private String currentWord;
	private long timeLeft;
	private int currentrow; 
	private int nbLetters ;
	private boolean found; 
	private int currentRow; 
	
	public Anagramme(Plateau p, int nbrow) {
		super(p);
		currentWord = "";
		nbLetters = 0; 
		found = false; 
		currentRow = nbrow;
		
	}
	
	public void click(int x, int y) {
		//grille;
		x = (x-20)/20;
		y = y/20;
		if(y == currentRow) {
			Case c = grille.getCase(x, y);
			if(c != null) {
				currentWord += c.letter();
				nbLetters++; 
			}
			
		}
		
		
	}

	@Override
	public void keyPress(int keyCode) {
		if(nbLetters == 10)
			return;
		
		if(keyCode == commands[Key.MODE]) {
			if(Constants.wordExists(currentWord)) {				
				currentWord = "";
				found = true; 
				
			} else
				currentWord = "";
		}
	}


	@Override
	public void keyRelease(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long msecElapsed) {
		if(nbLetters == 10 || found) {
			plateau.changeMode(new Tetris(plateau));
		}
	}

	@Override
	public void draw(Graphics g) {
		grille.draw(g);
		
		// TODO draw letters selected
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(250+Constants.Padding, 200, 100, 80, 10, 10);
		g.setColor(Color.black);

	}
	
	
	
	
}

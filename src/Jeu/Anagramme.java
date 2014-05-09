package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Briques.Brique;
import Briques.Case;
import Jeu.Constants.Key;

public class Anagramme extends GameMode {

	private String currentWord;
	private String bestWord; 
	private String base; 
	private int nbLetters ;
	private boolean found; 
	private int currentRow; 
	private int difficulty; //pourcentage de lettres en commun avec ke bestword pour gagner
	private long timeLeft;
	
	public Anagramme(Plateau p, int nbrow) {
		super(p);
		this.currentWord = "";
		this.nbLetters = 0; 
		this.difficulty= 50; 
		this.found = false; 
		this.currentRow = nbrow;
		this.base = getStringLine(p,nbrow); 
		this.bestWord = Constants.findBestWord(base); 
		timeLeft = 0;
		System.out.println("Base :  " + base);
		System.out.println("Best Word : " + bestWord);
	}
	
	public static String getStringLine(Plateau p, int nbrow) {
		String tmp = "";
		for(int i=0; i<Grille.cols; i++) {
			tmp += p.grille.getCase(i,nbrow).letter(); 
		}

		return tmp; 
	}
	
	public void reset() { //recommencer tant qu'on a pas trouv�
		this.currentWord = ""; 
		this.nbLetters = 0; 
	}
	
	public boolean win() {
		//return Constants.wordExists(currentWord);
	
		boolean tmp = false; 
		if(nbLetters > bestWord.length() && Constants.wordExists(currentWord)) return true; //si on a selectionn� plus de lettre que le meilleur anagramme

		else {
				
			String wordFound = Constants.findBestWord(currentWord); 			
			if(wordFound.length() >= this.bestWord.length()*this.difficulty/100) tmp = true; 
			else tmp = false; 
		
		}
		
		return tmp; 
	}
	
	public void setDifficulty(int nb) { 
		this.difficulty = nb;
		}
	
	public void click(int x, int y) {
		//grille;
		x = (x-20)/20;
		y = y/20;
		if(nbLetters == 11) this.found = win();
		if(y == this.currentRow) {
			Case c = grille.getCase(x, y);
			if(c != null) {
				this.currentWord += c.letter();
				this.nbLetters++; 
				System.out.println(currentWord);
			}
			
			
		}
		
		
	}

	@Override
	public void keyPress(int keyCode) {
				
		if(keyCode == commands[Key.MODE]) {
			this.found = win();
			if(!this.found) reset(); 
			
			System.out.println(this.found);
		}
	}


	@Override
	public void keyRelease(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long msecElapsed) {
		timeLeft += msecElapsed;
		if(timeLeft <= 0) {
			if(win()) {
				grille.removeRow(currentRow);
			}
			plateau.changeMode(new Tetris(plateau));
			return;
		}
		if(nbLetters == grille.cols) {
			this.found = win();
			if(!this.found) reset(); 
		}
		if(this.found) {
			grille.removeRow(currentRow);
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
		g.drawString("Time left:", 270 + Constants.Padding, 220);
		int sec = (int)timeLeft/1000;
		g.drawString(""+sec, 270+Constants.Padding, 240);
		
		// TODO draw letters selected
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(250+Constants.Padding, 200, 100, 80, 10, 10);
		g.setColor(Color.black);

	}
	
	
	
	
}

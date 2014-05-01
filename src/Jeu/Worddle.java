package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Briques.Case;
import Jeu.Constants.Key;

public class Worddle extends GameMode {
	protected ArrayList<String> words;
	private String currentWord;
	private long timeLeft;
	
	public Worddle(Plateau p) {
		super(p);
		words = new ArrayList<String>();
		currentWord = "";
		timeLeft = 1000*60;
	}
	
	public void click(int x, int y) {
		//grille;
		x = (x-20)/20;
		y = y/20;
		Case c = grille.getCase(x, y);
		if(c != null)
			currentWord += c.letter();
	}

	@Override
	public void keyPress(int keyCode) {
		// TODO Auto-generated method stub
		if(timeLeft < 0)
			return;
		
		if(keyCode == commands[Key.MODE]) {
			if(Constants.wordExists(currentWord)) {
				System.out.println(currentWord+" exists");
				words.add(currentWord);
				currentWord = "";
				
			} else
				currentWord = "";
		}
	}

	private void removeWords() {
		// TODO Auto-generated method stub
		for(String s: words)
			System.out.println(s);
	}

	@Override
	public void keyRelease(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long msecElapsed) {
		// TODO Auto-generated method stub
		timeLeft -= msecElapsed;
		if(timeLeft < 0) {
			removeWords();
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
		
		g.drawString(currentWord, 255+Constants.Padding, 205);
		g.drawString(""+sec, 270+Constants.Padding, 240);
	}
	
	
	
	
}

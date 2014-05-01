package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import Briques.Case;
import Jeu.Constants.Key;

public class Anagramme extends GameMode {

	private String currentWord;
	private String bestWord; 
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
	
	public String CheckAnagramme(int currentRow) {
		String tmp = null; 
		
		return tmp; 
		
	}
	
	/*
	public static ArrayList<String> permutations(String s) {
	    ArrayList<String> out = new ArrayList<String>();
	    if (s.length() == 1) {
	        out.add(s);
	        return out;
	    }
	    char first = s.charAt(0);
	    String rest = s.substring(1);
	    for (String permutation : permutations(rest)) {
	        out.addAll(insertAtAllPositions(first, permutation));
	    }
	    return out;
	}
	public static ArrayList<String> insertAtAllPositions(char ch, String s) {
	    ArrayList<String> out = new ArrayList<String>();
	    for (int i = 0; i <= s.length(); ++i) {
	        String inserted = s.substring(0, i) + ch + s.substring(i);
	        out.add(inserted);
	    }
	    return out;
	}*/
	
	public static boolean isAnagram(String s1, String s2){

        // Early termination check, if strings are of unequal lengths,
        // then they cannot be anagrams
        if ( s1.length() != s2.length() ) {
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        String sc1 = new String(c1);
        String sc2 = new String(c2);
        return sc1.equals(sc2);
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

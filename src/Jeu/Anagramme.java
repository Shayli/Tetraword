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
	
	public Anagramme(Plateau p, int nbrow) {
		super(p);
		currentWord = "";
		nbLetters = 0; 
		found = false; 
		currentRow = nbrow;
		base = getStringLine(p,nbrow); 
		
	}
	
	public static String getStringLine(Plateau p, int nbrow) {
		String tmp = "tester";

		return tmp; 
	}
	
	
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
	
	public static String findBestWord(String line) {
		String Word = ""; 
		Combinations comb = new Combinations(line); //on créé toutes les combinaisons de String possibles
		comb.combine();
		Collections.sort(comb.stock, new LengthComparator());
        Collections.reverse(comb.stock);
			 
	    for (String s : Constants.dictionary){
	    	for(int i=0; i<comb.stock.size(); i++) {
	    		System.out.println(comb.stock.get(i)+ " " + s);	
		    	System.out.println(isAnagram(comb.stock.get(i), s));
		    	if(isAnagram(comb.stock.get(i), s)) return s; 	    		
	    	}	    		        
	    }
			
		return Word; 
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

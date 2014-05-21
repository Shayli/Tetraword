package Jeu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.ImageIcon;

import Briques.Case;
import Jeu.Constants.Key;
/**
 * Classe Anagramme
 * Gère le mode anagramme
 * @author Monia, Laury & André
 * @version 1 
 */
public class Anagramme extends GameMode {

	private String currentWord;
	private String bestWord; 
	private String base; 
	private int nbLetters ;
	private boolean found, bestWordFound; 
	private int currentRow; 
	private float difficulty; //pourcentage de lettres en commun avec ke bestword pour gagner
	private long timeLeft;
	private Image anagramme;
	private ArrayList<Point> points;
	private Stack<Integer> rowsLeft;

	public Anagramme(Plateau p, Stack<Integer> o, int difficulte) {
		super(p);
		rowsLeft = o;
		/*if(difficulte <= 0)
			this.difficulty = 25;
		else if(difficulte >= 100)
			this.difficulty = 100;
		else
			this.difficulty= difficulte; */
		this.difficulty = 50;
		 
		
		points = new ArrayList<Point>();
		timeLeft = 1000*20;
		
		initWords();
		
		ImageIcon a = new ImageIcon("resources/anagramme.png", ""); 
		anagramme = a.getImage();
	}
	
	public void initWords()
	{
		reset();
		this.found = false;
		timeLeft = 1000*20;
		this.currentRow = rowsLeft.pop();
		bestWordFound = false;
		this.base = getStringLine(plateau,currentRow);
		bestWord = "";
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				bestWord = Constants.findBestWord(base);
				System.out.println(bestWord);
				bestWordFound = true;
				/*System.out.println(bestWord.length());
				System.out.println(difficulty);
				System.out.println((int)(bestWord.length()*difficulty/100.0f));*/
			}
		});
		t.start();
	}
	
	public static String getStringLine(Plateau p, int nbrow) {
		String tmp = "";
		for(int i=0; i<Grille.cols; i++) {
			tmp += p.grille.getCase(i,nbrow).letter(); 
		}

		return tmp; 
	}
	
	public void reset() { //recommencer tant qu'on a pas trouvé
		this.currentWord = ""; 
		this.nbLetters = 0;
		
		points.clear();
	}
	
	public boolean win() {
		System.out.println(currentWord+" "+bestWord);
		//return Constants.wordExists(currentWord);
		if(!Constants.wordExists(currentWord))
			return false;
		boolean tmp = false; 
		if(nbLetters > bestWord.length()) return true; //si on a selectionné plus de lettre que le meilleur anagramme
		
		else {	
			if(currentWord.length() >= (int)(this.bestWord.length()*this.difficulty/100.0f)) tmp = true; 
			else tmp = false; 
		
		}
		
		return tmp; 
	}
	
	public void setDifficulty(int nb) { 
		this.difficulty = nb;
		}
	
	public void click(int x, int y) {

		if(nbLetters == Grille.cols) this.found = win();
		if(grille.isInverted())
			y = Grille.rows - (y+1);
		if(y == this.currentRow) {
			Case c = grille.getCase(x, y);
			
			if(c != null) {
				Point p = new Point(x,y);
				if(points.contains(p))
					return;
				
				this.currentWord += c.letter();
				this.nbLetters++; 
				points.add(p);
			}
			
			
		}
		
		
	}

	@Override
	public void keyPress(int keyCode) {
				
		if(keyCode == commands[Key.MODE]) {
			this.found = win();
			if(!this.found) reset(); 
		}
	}


	@Override
	public void keyRelease(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long msecElapsed) {
		timeLeft -= msecElapsed;
		if(nbLetters == Grille.cols) {
			this.found = win();
			if(!this.found) reset(); 
		}
		if(this.found) {
			grille.removeRow(currentRow);
			plateau.addPoints((int)(this.bestWord.length()*this.difficulty/100.0f));
			if(rowsLeft.isEmpty())
			{
				plateau.changeMode(new Tetris(plateau));
				Constants.releaseMouse();
			}
			else {
				initWords();
			}
		}
		if(timeLeft <0) {
			grille.removeRow(currentRow);
			plateau.addPoints(-100);
			if(rowsLeft.isEmpty())
			{
				plateau.changeMode(new Tetris(plateau));
				Constants.releaseMouse();
			}
			else {
				initWords();
			}
			
		}
	}

	@Override
	public void draw(Graphics g) {
		grille.draw(g);
		g.drawImage(anagramme, 333, 525, null);
		g.setFont(Constants.pacifico); 
		g.setColor(Color.white);		
		int sec = (int)timeLeft/1000;
		g.drawString("Time left: "+sec, 355, 90);
		g.drawString(currentWord.toLowerCase(), 380, 577);
		if(bestWordFound) {
			g.drawString("["+((int)(this.bestWord.length()*this.difficulty/100.0f))+"]", 442, 548);
		}
		
		g.drawString("Appuyez sur "+Constants.getCommand(playerId, Key.MODE), 333, 620);
		g.drawString("pour valider", 333, 645);

		{
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g.setColor(Color.pink);
			if(grille.isInverted())
				g.drawRect((int)(Constants.MarginImg+5), (int)((Grille.rows-(currentRow))*Case.size+Constants.MarginImg+13), (int)(Grille.cols*Case.size), (int)(Case.size));
			else
				g.drawRect((int)(Constants.MarginImg+5), (int)((currentRow+1)*Case.size+Constants.MarginImg+13), (int)(Grille.cols*Case.size), (int)(Case.size));
		}
		for(Point p: points) {
			g.setColor(Color.pink);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			if(grille.isInverted())
				g.drawOval((int)(p.x*Case.size+8+Constants.MarginImg), (int)((Grille.rows-(p.y))*Case.size+14+Constants.MarginImg), (int)Case.size-2, (int)Case.size-2);
			else
				g.drawOval((int)(p.x*Case.size+8+Constants.MarginImg), (int)((p.y+1)*Case.size+14+Constants.MarginImg), (int)Case.size-2, (int)Case.size-2);
		}

	}
}

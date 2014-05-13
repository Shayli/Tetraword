package Jeu;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

import sun.awt.Mutex;
import Briques.Brique;
import Briques.Case;
import Patterns.Observable;

/**
 * Classe Grille 
 * Gère les briques sur une grille 
 * @author Monia, Laury & André
 * @version 1 
 */
public class Grille {
	public static final int cols = 8;
	public static final int rows = 16;
	public LinkedList<Brique> briques;
	protected Brique currentBrique;
	protected Brique nextBrique;
	public Observable events;
	private Mutex mutex;
	private int nbBriques;
	
	public Grille() {
		briques = new LinkedList<Brique>();
		nextBrique = null;
		currentBrique = next();
		events = new Observable();
		mutex = new Mutex();
		nbBriques = 0;
		//briques.add(new Briques.Cross(this));
	}
	
	public void add(){
		
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setFont(new Font("Helvetica", Font.PLAIN,18));
		mutex.lock();
		for(Brique b : briques) {
			b.draw(g);
		}
		if(currentBrique != null)
			currentBrique.draw(g);
		nextBrique.draw(g);
		
		mutex.unlock();
	}

	public boolean isEmpty(float f, float y) {
		if((int)f < 0 || (int)f >= cols || y >= rows)
			return false;
		
		for(Brique b: briques) {
			if(b.isHere((int)f,(int)y))
				return false;
		}
		
		if(currentBrique != null && currentBrique.isHere((int)f, (int)y))
			return false;
		
		return true;
	}
	
	public Case getCase(int x, int y) {
		if(x < 0 || x >= cols || y >= rows)
			return null;
		
		for(Brique b: briques) {
			Case c = b.get(x,y);
			if(c != null)
				return c;
		}
		
		if(currentBrique != null)
			return currentBrique.get(x, y);
		return null;
	}

	public void update() {
		mutex.lock();
		if(!currentBrique.down()) {
			briques.add(currentBrique);
			
			for(Case c: currentBrique.cases){
				if(c.getY()+currentBrique.y < 1)
				{
					events.notify("lose", null);
				}
			}
			currentBrique = null;
			events.notify("block", null);
			
			checkLine();
			currentBrique = next();
		}
		mutex.unlock();
	}

	private void checkLine() {
		Stack<Integer> lines = new Stack<Integer>();
		for(int rowChecker = rows-1; rowChecker >= 0; --rowChecker) {
			boolean full = true;
			for(int x = 0; x < cols; ++x) {
				if(isEmpty(x,rowChecker)) {
					full = false;
					break;
				}
			}
			if(full)
				lines.add(rowChecker);
		}
		if(!lines.isEmpty())
			events.notify("line",lines);
		
		Iterator<Brique> it = briques.iterator();
		while(it.hasNext()) {
			Brique b = it.next();
			
			if(b.cases.size() == 0)
				it.remove();
		}
	}
	
	private Brique next() {
		if(nextBrique == null) {
			nextBrique = Constants.nextBrique(this, nbBriques++);
		}
		Brique tmp = nextBrique;
		nextBrique = Constants.nextBrique(this, nbBriques++);
		if(nextBrique instanceof Briques.L){
			nextBrique.x = cols+cols/4;
			nextBrique.y = rows/3+1;
		}
		if(nextBrique instanceof Briques.L){
			nextBrique.x = cols+cols/4;
			nextBrique.y = rows/3+1;
		}
		if(nextBrique instanceof Briques.L2){
			nextBrique.x = cols+cols/4;
			nextBrique.y = rows/3+2;
		}
		if(nextBrique instanceof Briques.S){
			nextBrique.x = cols+cols/4;
			nextBrique.y = rows/3+1;
		}
		if(nextBrique instanceof Briques.S2){
			nextBrique.x = cols+cols/4;
			nextBrique.y = rows/3+2;
		}
		if(nextBrique instanceof Briques.Cross){
			nextBrique.x = cols+cols/4;
			nextBrique.y = rows/3+1;
		}
		if(nextBrique instanceof Briques.Cube){
			nextBrique.x = cols+cols/5;
			nextBrique.y = rows/3+1;
		}
		if(nextBrique instanceof Briques.Bar){
			nextBrique.x = cols+cols/3.2f;
			nextBrique.y = rows/3+1;
		}
		tmp.x = cols/2;
		tmp.y = 0;
		return tmp;
	}



	public void rotateCurrent() {
		if(currentBrique != null)
		currentBrique.rotate();
	}

	public void moveLeftCurrent() {
		if(currentBrique != null)
		currentBrique.moveLeft();
	}

	public void moveRightCurrent() {
		if(currentBrique != null)
		currentBrique.moveRight();
		// TODO Auto-generated method stub
		
	}
	
	public Brique getCurrentBrique(){
		return currentBrique;
	}
	
	public void removeRow(int row) {
		for(Brique b : briques) {
			b.removeCases(row);
		}
	}

	public void removeCase(int x, int y) {
		for(Brique b : briques) {
			b.removeCase(x, y);
		}
	}
	

}

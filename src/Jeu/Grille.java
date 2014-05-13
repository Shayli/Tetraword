package Jeu;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

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
	private int rowChecker;
	private Mutex mutex ;
	
	public Grille() {
		briques = new LinkedList<Brique>();
		nextBrique = null;
		currentBrique = next();
		events = new Observable();
		rowChecker = 0;
		mutex = new Mutex();
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
		
		return currentBrique.get(x, y);
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
		for(rowChecker = rows-1; rowChecker >= 0; --rowChecker) {
			boolean full = true;
			for(int x = 0; x < cols; ++x) {
				if(isEmpty(x,rowChecker)) {
					full = false;
					break;
				}
			}
			if(full)
				events.notify("line",rowChecker);
		}
		Iterator<Brique> it = briques.iterator();
		while(it.hasNext()) {
			Brique b = it.next();
			
			if(b.cases.size() == 0)
				it.remove();
		}
	}
	
	private Brique next() {
		if(nextBrique == null) {
			nextBrique = nextBrique();
		}
		Brique tmp = nextBrique;
		nextBrique = nextBrique();
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

	private Brique nextBrique() {
		int i = ((int)(Math.random()*100)) % 7;
		switch(i) {
		case 0:
			return new Briques.L(this);
		case 1:
			return new Briques.L2(this);
		case 2:
			return new Briques.S(this);
		case 3:
			return new Briques.S2(this);
		case 4:
			return new Briques.Cross(this);
		case 5:
			return new Briques.Cube(this);
		case 6:
			return new Briques.Bar(this);
		default:
			return null;
		}
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
		++rowChecker;
		for(Brique b : briques) {
			b.removeCases(row);
		}
	}
	

}

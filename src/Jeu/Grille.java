package Jeu;

import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;

import sun.awt.Mutex;
import Briques.Brique;
import Briques.Case;
import Patterns.Observable;

public class Grille {
	public static final int cols = 10;
	public static final int rows = 17;
	public LinkedList<Brique> briques;
	protected Brique currentBrique;
	public Observable events;
	private int rowChecker;
	private Mutex mutex ;
	
	public Grille() {
		briques = new LinkedList<Brique>();
		currentBrique = nextBrique();
		events = new Observable();
		rowChecker = 0;
		mutex = new Mutex();
		//briques.add(new Briques.Cross(this));
	}
	
	public void add(){
		
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		mutex.lock();
		for(Brique b : briques) {
			b.draw(g);
		}
		currentBrique.draw(g);
		mutex.unlock();
	}

	public boolean isEmpty(int x, int y) {
		if(x < 0 || x > cols || y > rows)
			return false;
		
		for(Brique b: briques) {
			if(b.isHere(x,y))
				return false;
		}
		
		if(currentBrique.isHere(x, y))
			return false;
		
		return true;
	}

	public void update() {
		mutex.lock();
		if(!currentBrique.down()) {
			for(Case c: currentBrique.cases){
				if(c.getY()+currentBrique.y < 1)
				{
					events.notify("lose", null);
				}
			}
			briques.add(currentBrique);
			checkLine();
			currentBrique = nextBrique();
		}
		mutex.unlock();
	}

	private void checkLine() {
		for(rowChecker = rows; rowChecker > 0; --rowChecker) {
			boolean full = true;
			for(int x = 0; x <= cols; ++x) {
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
		currentBrique.rotate();
	}

	public void moveLeftCurrent() {
		currentBrique.moveLeft();
	}

	public void moveRightCurrent() {
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

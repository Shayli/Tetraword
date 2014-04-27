package Jeu;

import java.awt.Graphics;
import java.util.LinkedList;

import Briques.Brique;

public class Grille {
	public static final int cols = 10;
	public static final int rows = 18;
	public LinkedList<Brique> briques;
	protected Brique currentBrique;
	
	public Grille() {
		briques = new LinkedList<Brique>();
		currentBrique = new Briques.Cross(this);
		//briques.add(new Briques.Cross(this));
	}
	
	public void add(){
		
	}

	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		for(Brique b : briques) {
			b.draw(g);
		}
		currentBrique.draw(g);
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
		for(Brique b : briques)
			b.down();
		if(!currentBrique.down()) {
			briques.add(currentBrique);
			currentBrique = nextBrique();
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
}

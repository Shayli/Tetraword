package Briques;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;

import Jeu.Grille;

/**
 * 
 *  x            x          x x    x x
 *  x X x    x X x        x X        X x
 *    0        1            2        3
 *
 *    x      x x
 *  x X x    X x          x X x x
 *
 *    4      5              6
 *
 */

abstract public class Brique {
	public ArrayList<Case> cases;
	public Grille grille;
	public int x,y;
	private Image img;
	
	public Brique(Grille g, Image i) {
		grille = g;
		cases = new ArrayList<Case>();
		x = grille.cols/2;
		y = 0;
		img = i;
	}
	
	public void draw(Graphics g){
		for(Case c: cases) {
			c.draw(g,x,y,img);
		}
	}
	
	abstract public void rotate();

	abstract public void moveLeft();
	
	abstract public void moveRight();
	
	abstract public boolean down();

	public boolean isHere(int x2, int y2) {
		for(Case c: cases) {
			if(c.getX()+x == x2 && c.getY()+y == y2)
				return true;
		}
		return false;
	}

	public void removeCases(int row) {
		// TODO Auto-generated method stub
		if(y > row)
			return;
		Iterator<Case> it = cases.iterator();
		while(it.hasNext()) {
			Case c = it.next();
			if(c.getY()+y == row)
				it.remove();
			else if(c.getY()+y < row)
				c.setY(c.getY()+1);
		}
	}

	public Case get(int x2, int y2) {
		for(Case c: cases) {
			if(c.getX()+x == x2 && c.getY()+y == y2)
				return c;
		}
		return null;
	}
}

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
/**
 * Classe Brique 
 * @author Monia, Laury & André
 * @version 1 
 */
abstract public class Brique {
	public ArrayList<Case> cases;
	public Grille grille;
	public float x,y;
	private Image img;
	/**
     * L'état de rotation de la brique.
     * <p>Il y a 4 états pour chaque brique.</p>
     * 
     */
	protected int state;
	
	public Brique(Image i) {
		cases = new ArrayList<Case>();
		x = Grille.cols/2;
		y = 0;
		img = i;
	}
	
	public abstract Brique clone();

	public void draw(Graphics g, boolean inv){
		for(Case c: cases) {
			c.draw(g,x,y,img, inv);
		}
	}
	
	public void fall() {
		ArrayList<Case> tmp = new ArrayList<Case>();
		if(cases.isEmpty())
			return;
		
		for(Case c: cases) {
			int i;
			for(i = 0; i<tmp.size(); ++i) {
				if(tmp.get(i).getY() <c.getY())
					break;
			}
			tmp.add(i, c);
		}
		do {
		
			for(Case c: tmp) {
				int t = (int)y+c.getY()+1;
				Case c2 = grille.getCase((int)x+c.getX(), t);
			
				if(t >= Grille.rows || (c2 != null && !cases.contains(c2)))
					return;
			}
			
			++y;
		} while(true);
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
		ArrayList<Case> tmp = new ArrayList<Case>();
		while(it.hasNext()) {
			Case c = it.next();
			if(c.getY()+y == row) {
				it.remove();
			}
			else if(c.getY()+y < row) {
				int i;
				for(i = 0; i<tmp.size(); ++i) {
					if(tmp.get(i).getY() <c.getY())
						break;
				}
				tmp.add(i, c);
			}
		}
		
		for(Case c: tmp) {
			if(grille.isEmpty(x+c.getX(), y+c.getY()+1))
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

	public void removeCase(int x2, int y2) {
		Case c = get(x2, y2);
		if(c != null)
			cases.remove(c);
	}
}

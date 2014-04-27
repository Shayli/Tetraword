package Briques;

import java.awt.Graphics;
import java.awt.Image;

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
	public Case[] cases = new Case[4];
	public Grille grille;
	protected int x, y;
	private Image img;
	
	public Brique(Grille g, Image i) {
		grille = g;
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
}

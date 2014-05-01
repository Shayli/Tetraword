package Briques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import Jeu.Constants;

public class Case {
	
	private char a;
	private int x;
	private int y;
	private int size;
	
	public Case(int x1, int y1, int size){
		this.setX(x1);
		this.setY(y1);
		this.a = Constants.randomLetter();
		this.size = size;
	}
	
	public void draw(Graphics g, int x1, int y1, Image i) { 
		g.drawImage (i, (x1+x)*size+Constants.Padding, (y1+y)*size, null ); 
		String s = "" +a ;
		//g.setColor(Color.red);
		//g.drawRect((x1+x)*size, (y1+y)*size, size, size);
		//g.fillRect((x1+x)*size, (y1+y)*size, size, size);
		g.setColor(Color.black);
		g.drawString(s, (x1+x)*size+6+Constants.Padding, (y1+y)*size+15);
	}

	
	public void move(int x1, int y1) {
		this.x += x1*size;
		this.y += y1*size;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x1) {
		this.x = x1;
	}
	public void setY(int y1) {
		this.y = y1;
	}
}

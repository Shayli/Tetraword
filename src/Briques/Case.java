package Briques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import Jeu.Constants;
/**
 * Classe case 
 * @author Monia, Laury & André
 * @version 1 
 */
public class Case {
	
	private char a;
	private int x;
	private int y;
	public static double size = 37.5;
	
	public Case(int x1, int y1, double size){
		this.setX(x1);
		this.setY(y1);
		this.a = Constants.randomLetter();
	}
	
	public void draw(Graphics g, float x2, float y2, Image i) { 
		if(y2+y >= 0){
			g.drawImage (i,(int)((x2+x)*size+10+Constants.MarginImg), (int)((y2+y)*size+55+Constants.MarginImg), null ); 
			String s = "" +a ;
			//g.setColor(Color.red);
			//g.drawRect((x1+x)*size, (y1+y)*size, size, size);
			//g.fillRect((x1+x)*size, (y1+y)*size, size, size);
			g.setColor(Color.black);
			g.drawString(s, (int)((x2+x)*size+Constants.Padding+4), (int)((y2+y)*size+80));
		}
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
	
	public char letter() {
		return a;
	}
}

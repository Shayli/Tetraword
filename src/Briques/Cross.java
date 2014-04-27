package Briques;

import Jeu.Constants;
import Jeu.Grille;

public class Cross extends Brique {
	private int state;
	/*
	 * xxx		 x		 x		x
	 *  x		xx		xxx		xx
	 *   		 x				x
	 *      
	 *  0		 1		 2		3
	 */

	public Cross(Grille g) {
		super(g, Constants.Cross);
		state = 0;
		cases[0] = new Case(-1,0,'A', 20);
		cases[1] = new Case(0, 0,'B', 20);
		cases[2] = new Case(1, 0,'C', 20);
		cases[3] = new Case(0, 1,'D', 20);
	}

	public void rotate() {
		//System.out.println("rotate "+state);
		switch(state){
			case 0 : 
				if(!grille.isEmpty(x, y-1))
					return;
				
				cases[0].setX(0);
				cases[0].setY(-1);
				
				cases[2].setX(0);
				cases[2].setY(1);
				
				cases[3].setX(-1);
				cases[3].setY(0);
				state = 1;
				break;
				
			case 1 : 
				if(!grille.isEmpty(x+1, y))
					return;
				
				cases[0].setX(1);
				cases[0].setY(0);
				
				cases[2].setX(-1);
				cases[2].setY(0);
				
				cases[3].setX(0);
				cases[3].setY(-1);
				state = 2;
				break;
				
			case 2 : 
				if(!grille.isEmpty(x, y+1))
					return;
				
				cases[0].setX(0);
				cases[0].setY(1);
				
				cases[2].setX(0);
				cases[2].setY(-1);
				
				cases[3].setX(1);
				cases[3].setY(0);
				state = 3;
				break;
				
			case 3 : 
				if(!grille.isEmpty(x-1, y))
					return;
				
				cases[0].setX(-1);
				cases[0].setY(0);
				
				cases[2].setX(1);
				cases[2].setY(0);
				
				cases[3].setX(0);
				cases[3].setY(1);
				state = 0;
				break;
		}
	}

	
	public boolean down() {
		//System.out.println(state);
		switch(state){
			case 0 : 
			{
				int x0 = cases[0].getX(), y0 = cases[0].getY() +1;
				int x2 = cases[2].getX(), y2 = cases[2].getY() +1;
				int x3 = cases[3].getX(), y3 = cases[3].getY() +1;
				
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
			}
			return false;	
			case 1 :
			{
				int x3 = cases[3].getX(), y3 = cases[3].getY() +1;
				int x2 = cases[2].getX(), y2 = cases[2].getY() +1;
				if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
			}	
				return false;
			
			case 2 :
			{
				int x2 = cases[2].getX(), y2 = cases[2].getY() +1;
				int x1 = cases[1].getX(), y1 = cases[1].getY() +1;
				int x0 = cases[0].getX(), y0 = cases[0].getY() +1;
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x2, y+y2))
				{
					++y;
					return true;
				}
			}	
				return false;
			
			case 3 : 
			{
				int x0 = cases[0].getX(), y0 = cases[0].getY() +1;
				int x3 = cases[3].getX(), y3 = cases[3].getY() +1;
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
			}	
				return false;
			
			default : 
				return false;
		}
	}

	public void moveLeft() {
		switch(state) {
		case 0:
		{
			int x0 = cases[0].getX()-1, y0 = cases[0].getY();
			int x3 = cases[3].getX()-1, y3 = cases[3].getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x3,y+y3))
				--x;
		}
		break;
		case 1:
		{
			int x0 = cases[0].getX()-1, y0 = cases[0].getY();
			int x2 = cases[2].getX()-1, y2 = cases[2].getY();
			int x3 = cases[3].getX()-1, y3 = cases[3].getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				--x;
		}
		break;
		case 2:
		{
			int x2 = cases[2].getX()-1, y2 = cases[2].getY();
			int x3 = cases[3].getX()-1, y3 = cases[3].getY();
			if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				--x;
		}
		break;
		case 3:
		{
			int x0 = cases[0].getX()-1, y0 = cases[0].getY();
			int x1 = cases[1].getX()-1, y1 = cases[1].getY();
			int x2 = cases[2].getX()-1, y2 = cases[2].getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x2,y+y2))
				--x;
		}
		break;
		}
	}
	
	public void moveRight() {
		switch(state) {
		case 0:
		{
			int x2 = cases[2].getX()+1, y2 = cases[2].getY();
			int x3 = cases[3].getX()+1, y3 = cases[3].getY();
			if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				++x;
		}
		break;
		case 1:
		{
			int x0 = cases[0].getX()+1, y0 = cases[0].getY();
			int x1 = cases[1].getX()+1, y1 = cases[1].getY();
			int x2 = cases[2].getX()+1, y2 = cases[2].getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x2,y+y2))
				++x;
		}
		break;
		case 2:
		{
			int x0 = cases[0].getX()+1, y0 = cases[0].getY();
			int x3 = cases[3].getX()+1, y3 = cases[3].getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x3,y+y3))
				++x;
		}
		break;
		case 3:
		{
			int x0 = cases[0].getX()+1, y0 = cases[0].getY();
			int x2 = cases[2].getX()+1, y2 = cases[2].getY();
			int x3 = cases[3].getX()+1, y3 = cases[3].getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				++x;
		}
		break;
		}
	}
}
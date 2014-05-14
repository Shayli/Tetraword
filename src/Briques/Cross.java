package Briques;

import Jeu.Constants;
import Jeu.Grille;
/**
 * Classe Cross
 * Un type de brique
 * @author Monia, Laury & André
 * @version 1 
 */
public class Cross extends Brique {
	/*
	 * xxx		 x		 x		x
	 *  x		xx		xxx		xx
	 *   		 x				x
	 *      
	 *  0		 1		 2		3
	 */

	public Cross() {
		super(Constants.Cross);
		state = 0;
		cases.add(new Case(-1,0, 33));
		cases.add(new Case(0, 0, 33));
		cases.add(new Case(1, 0, 33));
		cases.add(new Case(0, 1, 33));
	}
	
	public Cross clone() {
		Cross b = new Cross();
		b.x = x;
		b.y = y;
		return b;
	}
	

	public void rotate() {
		//System.out.println("rotate "+state);
		switch(state){
			case 0 : 
				if(!grille.isEmpty(x, y-1))
					return;
				
				cases.get(0).setX(0);
				cases.get(0).setY(-1);
				
				cases.get(2).setX(0);
				cases.get(2).setY(1);
				
				cases.get(3).setX(-1);
				cases.get(3).setY(0);
				state = 1;
				break;
				
			case 1 : 
				if(!grille.isEmpty(x+1, y))
					return;
				
				cases.get(0).setX(1);
				cases.get(0).setY(0);
				
				cases.get(2).setX(-1);
				cases.get(2).setY(0);
				
				cases.get(3).setX(0);
				cases.get(3).setY(-1);
				state = 2;
				break;
				
			case 2 : 
				if(!grille.isEmpty(x, y+1))
					return;
				
				cases.get(0).setX(0);
				cases.get(0).setY(1);
				
				cases.get(2).setX(0);
				cases.get(2).setY(-1);
				
				cases.get(3).setX(1);
				cases.get(3).setY(0);
				state = 3;
				break;
				
			case 3 : 
				if(!grille.isEmpty(x-1, y))
					return;
				
				cases.get(0).setX(-1);
				cases.get(0).setY(0);
				
				cases.get(2).setX(1);
				cases.get(2).setY(0);
				
				cases.get(3).setX(0);
				cases.get(3).setY(1);
				state = 0;
				break;
		}
	}

	
	public boolean down() {
		//System.out.println(state);
		switch(state){
			case 0 : 
			{
				int x0 = cases.get(0).getX(), y0 = cases.get(0).getY() +1;
				int x2 = cases.get(2).getX(), y2 = cases.get(2).getY() +1;
				int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
				
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
			}
			return false;	
			case 1 :
			{
				int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
				int x2 = cases.get(2).getX(), y2 = cases.get(2).getY() +1;
				if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
			}	
				return false;
			
			case 2 :
			{
				int x2 = cases.get(2).getX(), y2 = cases.get(2).getY() +1;
				int x1 = cases.get(1).getX(), y1 = cases.get(1).getY() +1;
				int x0 = cases.get(0).getX(), y0 = cases.get(0).getY() +1;
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x2, y+y2))
				{
					++y;
					return true;
				}
			}	
				return false;
			
			case 3 : 
			{
				int x0 = cases.get(0).getX(), y0 = cases.get(0).getY() +1;
				int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
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
			int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
			int x3 = cases.get(3).getX()-1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x3,y+y3))
				--x;
		}
		break;
		case 1:
		{
			int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
			int x2 = cases.get(2).getX()-1, y2 = cases.get(2).getY();
			int x3 = cases.get(3).getX()-1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				--x;
		}
		break;
		case 2:
		{
			int x2 = cases.get(2).getX()-1, y2 = cases.get(2).getY();
			int x3 = cases.get(3).getX()-1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				--x;
		}
		break;
		case 3:
		{
			int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
			int x1 = cases.get(1).getX()-1, y1 = cases.get(1).getY();
			int x2 = cases.get(2).getX()-1, y2 = cases.get(2).getY();
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
			int x2 = cases.get(2).getX()+1, y2 = cases.get(2).getY();
			int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				++x;
		}
		break;
		case 1:
		{
			int x0 = cases.get(0).getX()+1, y0 = cases.get(0).getY();
			int x1 = cases.get(1).getX()+1, y1 = cases.get(1).getY();
			int x2 = cases.get(2).getX()+1, y2 = cases.get(2).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x2,y+y2))
				++x;
		}
		break;
		case 2:
		{
			int x0 = cases.get(0).getX()+1, y0 = cases.get(0).getY();
			int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x3,y+y3))
				++x;
		}
		break;
		case 3:
		{
			int x0 = cases.get(0).getX()+1, y0 = cases.get(0).getY();
			int x2 = cases.get(2).getX()+1, y2 = cases.get(2).getY();
			int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3,y+y3))
				++x;
		}
		break;
		}
	}
}
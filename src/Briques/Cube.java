package Briques;

import Jeu.Constants;
import Jeu.Grille;
/**
 * Classe Cube
 * Un type de brique
 * @author Monia, Laury & André
 * @version 1 
 */
public class Cube extends Brique {
	public Cube(){
		super(Constants.Cube);
		cases.add(new Case(0, 0, 33));
		cases.add(new Case(1, 0, 33));
		cases.add(new Case(1, 1, 33));
		cases.add(new Case(0, 1, 33));
	}
	
	public Cube clone() {
		Cube b = new Cube();
		b.x = x;
		b.y = y;
		return b;
	}
	
	public boolean down() {
		switch(state){
			case 0 :
			{
				int x2 = cases.get(2).getX(), y2 = cases.get(2).getY() +1;
				int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
				if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
				return false;
			}
			
			case 1 :
			{
				int x2 = cases.get(2).getX(), y2 = cases.get(2).getY() +1;
				int x1 = cases.get(1).getX(), y1 = cases.get(1).getY() +1;
				if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x1, y+y1))
				{
					++y;
					return true;
				}
				return false;
			}
			
			case 2 :
			{
				int x1 = cases.get(1).getX(), y1 = cases.get(1).getY() +1;
				int x0 = cases.get(0).getX(), y0 = cases.get(0).getY() +1;
				if(grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x0, y+y0))
				{
					++y;
					return true;
				}
				return false;
			}
			
			case 3 :
			{
				int x0 = cases.get(0).getX(), y0 = cases.get(0).getY() +1;
				int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
				return false;
			}
			default:
				return false;
		}
	}

	@Override
	public void rotate() {
		switch(state){
			case 0 :
			{
				/*if(!grille.isEmpty(x-1, y) || !grille.isEmpty(x-1, y+11))
					return;
				*/
				cases.get(0).setX(1);
				cases.get(0).setY(0);
				
				cases.get(1).setX(1);
				cases.get(1).setY(1);
				
				cases.get(2).setX(0);
				cases.get(2).setY(1);
				
				cases.get(3).setX(0);
				cases.get(3).setY(0);
				state = 1;
				break;
			}
			case 1 :
			{
				cases.get(0).setX(1);
				cases.get(0).setY(1);
				
				cases.get(1).setX(0);
				cases.get(1).setY(1);
				
				cases.get(2).setX(0);
				cases.get(2).setY(0);
				
				cases.get(3).setX(1);
				cases.get(3).setY(0);
				state = 2;
				break;
			}
			case 2 : 
			{
				cases.get(0).setX(0);
				cases.get(0).setY(1);
				
				cases.get(1).setX(0);
				cases.get(1).setY(0);
				
				cases.get(2).setX(1);
				cases.get(2).setY(0);
				
				cases.get(3).setX(1);
				cases.get(3).setY(1);
				state = 3;
				break;
			}
			case 3 : 
			{
				cases.get(0).setX(0);
				cases.get(0).setY(0);
				
				cases.get(1).setX(1);
				cases.get(1).setY(0);
				
				cases.get(2).setX(1);
				cases.get(2).setY(1);
				
				cases.get(3).setX(0);
				cases.get(3).setY(1);
				state = 0;
				break;
			}
		}
	}

	@Override
	public void moveLeft() {
		switch(state){
			case 0 :
			{
				int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
				int x3 = cases.get(3).getX()-1, y3 = cases.get(3).getY();
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x3, y+y3))
					--x;
			}
			break;
			
			case 1 : 
			{
				int x3 = cases.get(3).getX()-1, y3 = cases.get(3).getY();
				int x2 = cases.get(2).getX()-1, y2 = cases.get(2).getY();
				if(grille.isEmpty(x+x3, y+y3) && grille.isEmpty(x+x2, y+y2))
					--x;
			}
			break;
			
			case 2 : 
			{
				int x2 = cases.get(2).getX()-1, y2 = cases.get(2).getY();
				int x1 = cases.get(1).getX()-1, y1 = cases.get(1).getY();
				if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x1, y+y1))
					--x;
			}
			break;
			
			case 3 : 
			{
				int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
				int x1 = cases.get(1).getX()-1, y1 = cases.get(1).getY();
				if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1))
					--x;
			}
			break;
			
		}
	}

	@Override
	public void moveRight() {
		switch(state){
			case 0 :
			{
				int x2 = cases.get(2).getX()+1, y2 = cases.get(2).getY();
				int x1 = cases.get(1).getX()+1, y1 = cases.get(1).getY();
				if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x1, y+y1))
					++x;
			}
			break;
			
			case 1 :
			{
				int x1 = cases.get(1).getX()+1, y1 = cases.get(1).getY();
				int x0 = cases.get(0).getX()+1, y0 = cases.get(0).getY();
				if(grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x0, y+y0))
					++x;
			}
			break;
			
			case 2 : 
			{
				int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
				int x0 = cases.get(0).getX()+1, y0 = cases.get(0).getY();
				if(grille.isEmpty(x+x3, y+y3) && grille.isEmpty(x+x0, y+y0))
					++x;
			}
			break;
			
			case 3 : 
			{
				int x2 = cases.get(2).getX()+1, y2 = cases.get(2).getY();
				int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
				if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
					++x;
			}
			break;
		}
	}

}

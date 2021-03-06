package Briques;

import Jeu.Constants;
import Jeu.Grille;
/**
 * Classe S
 * Un type de brique
 * @author Monia, Laury & André
 * @version 1 
 */
public class S extends Brique {
	/*
	 * xx		 x		
	 *  xx		xx		
	 * 			x				
	 *  		
	 *  0		 1		
	 * 
	 */
	
	public S() {
		super(Constants.S);
		state = 0;
		cases.add(new Case(-1,0, 20));
		cases.add(new Case(0, 0, 20));
		cases.add(new Case(0, 1, 20));
		cases.add(new Case(1, 1, 20));
	}
	
	public S clone() {
		S b = new S();
		b.x = x;
		b.y = y;
		return b;
	}

	public void rotate() {
		if(state == 0) {
			if(!grille.isEmpty(x, y-1) || !grille.isEmpty(x-1, y+1))
				return;
			
			cases.get(0).setX(0);
			cases.get(0).setY(-1);
			
			cases.get(2).setX(-1);
			cases.get(2).setY(0);
			
			cases.get(3).setX(-1);
			cases.get(3).setY(1);
			state = 1;
		}
		else {
			if(!grille.isEmpty(x, y+1) || !grille.isEmpty(x+1, y+1))
				return;
			
			cases.get(0).setX(-1);
			cases.get(0).setY(0);
			
			cases.get(2).setX(0);
			cases.get(2).setY(1);
			
			cases.get(3).setX(1);
			cases.get(3).setY(1);
			
			state = 0;
		}
	}

	public boolean down() {
		if(state == 0) {
			int x0 = cases.get(0).getX(), y0 = cases.get(0).getY() +1;
			int x2 = cases.get(2).getX(), y2 = cases.get(2).getY() +1;
			int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
			{
				++y;
				return true;
			}
			
			return false;
		}else{
			int x1 = cases.get(1).getX(), y1 = cases.get(1).getY() +1;
			int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
			if(grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x3, y+y3))
			{
				++y;
				return true;
			}
			
			return false;
		}
	}

	@Override
	public void moveLeft() {
		if(state == 0) {
			int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
			int x2 = cases.get(2).getX()-1, y2 = cases.get(2).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2))
				--x;
		}else{
			int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
			int x2 = cases.get(2).getX()-1, y2 = cases.get(2).getY();
			int x3 = cases.get(3).getX()-1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
				--x;			
		}
	}

	@Override
	public void moveRight() {
		if(state == 0) {
			int x1 = cases.get(1).getX()+1, y1 = cases.get(1).getY();
			int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x3, y+y3))
				++x;	
		}else{
			int x0 = cases.get(0).getX()+1, y0 = cases.get(0).getY();
			int x1 = cases.get(1).getX()+1, y1 = cases.get(1).getY();
			int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x3, y+y3))
				++x;
		}
	}
}

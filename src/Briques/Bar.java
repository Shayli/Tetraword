package Briques;

import Jeu.Constants;

/**
 * Classe Bar
 * <p>Un type de brique</p>
 * @author Monia, Laury & André
 * @version 1 
 */
public class Bar extends Brique {
	
	public Bar() {
		super(Constants.Bar);
		state = 1;
		cases.add(new Case(1,0, 33));
		cases.add(new Case(0,0, 33));
		cases.add(new Case(-1,0, 33));
		cases.add(new Case(-2,0, 33));
	}
	
	public Bar clone() {
		Bar b = new Bar();
		b.x = x;
		b.y = y;
		return b;
	}
	
	
	public void rotate() {
		if(grille == null)
			return;
		//System.out.println(state);
		switch(state){
			case 0 : 
				if(!grille.isEmpty(x+1, y) || !grille.isEmpty(x-1, y) || !grille.isEmpty(x-2, y))
					return;
				
				cases.get(0).setX(1);
				cases.get(0).setY(0);
				
				cases.get(2).setX(-1);
				cases.get(2).setY(0);
				
				cases.get(3).setX(-2);
				cases.get(3).setY(0);
				state = 1;
				break;
				
			case 1 : 
				if(!grille.isEmpty(x, y+1) || !grille.isEmpty(x, y-1) || !grille.isEmpty(x, y-2))
					return;
				
				cases.get(0).setX(0);
				cases.get(0).setY(1);
				
				cases.get(2).setX(0);
				cases.get(2).setY(-1);
				
				cases.get(3).setX(0);
				cases.get(3).setY(-2);
				
				state = 2;
				break;
				
			case 2 : 
				if(!grille.isEmpty(x-1, y) || !grille.isEmpty(x+1, y) || !grille.isEmpty(x+2, y))
					return;
				
				cases.get(0).setX(-1);
				cases.get(0).setY(0);
				
				cases.get(2).setX(1);
				cases.get(2).setY(0);
				
				cases.get(3).setX(2);
				cases.get(3).setY(0);
				
				state = 3;
				break;
				
			case 3 : 
				if(!grille.isEmpty(x, y-1) || !grille.isEmpty(x, y+1) || !grille.isEmpty(x, y+2))
					return;
				
				cases.get(0).setX(0);
				cases.get(0).setY(-1);
				
				cases.get(2).setX(0);
				cases.get(2).setY(1);
				
				cases.get(3).setX(0);
				cases.get(3).setY(2);
				
				state = 0;
				break;
				
			default : 
				return;
		}
	}
	
	public boolean down() {
		switch(state){
			case 0 : 
			{
				int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
				if(grille.isEmpty(x+x3, y+y3))
				{
					++y;
					return true;
				}
			} 	
			return false;
			
			case 1: case 3: 
			{
				boolean ok = true;
				for(Case c: cases){
					if(!grille.isEmpty(c.getX()+x, c.getY()+y+1))
						ok = false;
				}
				if(ok)
					++y;
				return ok;
			}
			
				
			case 2 : 
			{
				int x0 = cases.get(0).getX(), y0 = cases.get(0).getY() +1;
				if(grille.isEmpty(x+x0, y+y0))
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

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		switch(state) {
		case 0: case 2:
			{
				boolean ok = true;
				for(Case c: cases) {
					if(!grille.isEmpty(c.getX()+x-1, c.getY()+y))
						ok = false;
				}
				
				if(ok)
					--x;
			}
			break;
		case 1:
		{
			int x3 = cases.get(3).getX()-1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x3, y+y3))
				--x;
			
		}
		break;
		
		case 3:
		{
			int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
			if(grille.isEmpty(x+x0, y+y0))
				--x;
		}
		break;
		
		default : 
			return;
		}
	}

	@Override
	public void moveRight() {
		switch(state) {
		case 0: case 2:
			{
				boolean ok = true;
				for(Case c: cases) {
					if(!grille.isEmpty(c.getX()+x+1, c.getY()+y))
						ok = false;
				}
				
				if(ok)
					++x;
			}
			break;
			
		case 1:
		{
			int x0 = cases.get(0).getX()+1, y0 = cases.get(0).getY();
			if(grille.isEmpty(x+x0, y+y0))
				++x;
		}
		break;
		
		case 3:
		{
			int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
			if(grille.isEmpty(x+x3, y+y3))
				++x;
		}
		break;
		
		default : 
			return;
		}
	}
}

package Briques;

import Jeu.Constants;
import Jeu.Grille;

public class Bar extends Brique {
	private int state;
	
	public Bar(Grille g) {
		super(g, Constants.Bar);
		state = 0;
		cases.add(new Case(0,-1,'A', 20));
		cases.add(new Case(0,0,'B', 20));
		cases.add(new Case(0,1,'C', 20));
		cases.add(new Case(0, 2,'D', 20));
	}
	
	public void rotate() {
		System.out.println(state);
		if(state == 0) {
			if(!grille.isEmpty(x-1, y) || !grille.isEmpty(x+1, y) || !grille.isEmpty(x+2, y))
				return;
			
			cases.get(0).setX(-1);
			cases.get(0).setY(0);
			
			cases.get(2).setX(1);
			cases.get(2).setY(0);
			
			cases.get(3).setX(2);
			cases.get(3).setY(0);
			state = 1;
		}
		else {
			if(!grille.isEmpty(x, y-1) || !grille.isEmpty(x, y+1) || !grille.isEmpty(x, y+2))
				return;
			
			cases.get(0).setX(0);
			cases.get(0).setY(-1);
			
			cases.get(2).setX(0);
			cases.get(2).setY(1);
			
			cases.get(3).setX(0);
			cases.get(3).setY(2);
			
			state = 0;
		}
	}
	
	public boolean down() {
		if(state == 0) {
			int x2 = cases.get(3).getX(), y2 = cases.get(3).getY() +1;
			if(grille.isEmpty(x+x2, y+y2))
			{
				++y;
				return true;
			}
			
			return false;
			
		} else {
			boolean ok = true;
			for(Case c: cases) {
				if(!grille.isEmpty(c.getX()+x, c.getY()+y+1))
					ok = false;
			}
			
			if(ok)
				++y;
				
			return ok;
		}
	}

	@Override
	public void moveLeft() {
		// TODO Auto-generated method stub
		switch(state) {
		case 1:
			{
				int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
				if(grille.isEmpty(x+x0, y+y0))
					--x;
			}
			break;
		case 0:
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
		}
	}

	@Override
	public void moveRight() {
		switch(state) {
		case 1:
			{
				int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
				if(grille.isEmpty(x+x3, y+y3))
					++x;
			}
			break;
		case 0:
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
		}
	}
}

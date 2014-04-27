package Briques;

import Jeu.Constants;
import Jeu.Grille;

public class Cube extends Brique {
	
	public Cube(Grille g){
		super(g, Constants.Cube);
		cases.add(new Case(0, 0,'A', 20));
		cases.add(new Case(0, 1,'B', 20));
		cases.add(new Case(1, 0,'C', 20));
		cases.add(new Case(1, 1,'D', 20));
	}
	
	public boolean down() {
		int x1 = cases.get(1).getX(), y1 = cases.get(1).getY() +1;
		int x3 = cases.get(3).getX(), y3 = cases.get(3).getY() +1;
		if(grille.isEmpty(x+x1, y+y1) && grille.isEmpty(x+x3, y+y3))
		{
			++y;
			return true;
		}
		
		return false;
	}

	@Override
	public void rotate() {}

	@Override
	public void moveLeft() {
		int x0 = cases.get(0).getX()-1, y0 = cases.get(0).getY();
		int x1 = cases.get(1).getX()-1, y1 = cases.get(1).getY();
		if(grille.isEmpty(x+x0, y+y0) && grille.isEmpty(x+x1, y+y1))
			--x;
	}

	@Override
	public void moveRight() {
		int x2 = cases.get(2).getX()+1, y2 = cases.get(2).getY();
		int x3 = cases.get(3).getX()+1, y3 = cases.get(3).getY();
		if(grille.isEmpty(x+x2, y+y2) && grille.isEmpty(x+x3, y+y3))
			++x;
	}

}

package Jeu;

/**
 * Classe Point
 * Créer un point de coordonnées (x,y)
 * @author Monia, Laury & André
 * @version 1 
 */
public class Point {
	int x;
	int y; 
	
	public Point(int x, int y) { this.x = x; this.y = y; }
	
	public boolean equals(Object o){
		if(!(o instanceof Point))
			return false;
		Point p = (Point)o;
		return x == p.x && y == p.y;
		
	}

}

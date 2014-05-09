package Jeu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import Briques.Case;
import Jeu.Constants.Key;

public class Worddle extends GameMode {
	protected ArrayList<String> words;
	protected ArrayList<ArrayList<Point>> points;
	protected Point lastPoint;
	private String currentWord;
	private long timeLeft;
	
	public Worddle(Plateau p) {
		super(p);
		words = new ArrayList<String>();
		points = new ArrayList<ArrayList<Point>>();
		points.add(new ArrayList<Point>());
		currentWord = "";
		timeLeft = 1000*60;
		boolean found = false;
		do {
			int x = (int)(Math.random()*100) % Grille.cols;
			int y = (int)(Math.random()*100) % Grille.rows;
			if(!grille.isEmpty(x, y)) {
				lastPoint = new Point(x,y);
				points.get(0).add(lastPoint);
				found = true;
				Case c = grille.getCase(x, y);
				currentWord += c.letter();
			}
		} while(!found);
	}
	
	public void click(int x, int y) {
		//grille;
		Point curr = new Point(x,y);
		if(Math.abs(lastPoint.x-curr.x) > 1 || Math.abs(lastPoint.y-curr.y) > 1)
			return;
		
		lastPoint = curr;
		Case c = grille.getCase(x, y);
		if(c != null) {
			currentWord += c.letter();
			points.get(points.size()-1).add(curr);
		}
	}

	@Override
	public void keyPress(int keyCode) {
		// TODO Auto-generated method stub
		if(timeLeft < 0)
			return;
		
		if(keyCode == commands[Key.MODE]) {
			if(Constants.wordExists(currentWord)) {
				System.out.println(currentWord+" exists");
				words.add(currentWord);
				
				ArrayList<Point> tmp = points.get(points.size()-1);
				points.add(new ArrayList<Point>());
				int r = (int)(Math.random()*100) % (tmp.size()-1) +1;
				Point p = new Point(tmp.get(r).x, tmp.get(r).y);
				points.get(points.size()-1).add(p);
				currentWord = ""+currentWord.charAt(r);
			} else {
				System.out.println(currentWord+" does not exists");
				currentWord = "";
				points.get(points.size()-1).clear();
			}
		}
	}

	private void removeWords() {
		// TODO Auto-generated method stub
		for(String s: words)
			System.out.println(s);
	}

	@Override
	public void keyRelease(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(long msecElapsed) {
		// TODO Auto-generated method stub
		timeLeft -= msecElapsed;
		if(timeLeft < 0) {
			removeWords();
			plateau.changeMode(new Tetris(plateau));
		}
	}

	@Override
	public void draw(Graphics g) {
		grille.draw(g);
		
		// TODO draw letters selected
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(250+Constants.Padding, 200, 100, 80, 10, 10);
		g.setColor(Color.black);
		g.drawString("Time left:", 270 + Constants.Padding, 220);
		int sec = (int)timeLeft/1000;
		
		g.drawString(currentWord, 255+Constants.Padding, 205);
		g.drawString(""+sec, 270+Constants.Padding, 240);
		g.setColor(Color.red);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1));
		for(int j = 0; j<points.size(); ++j) {
			ArrayList<Point> a = points.get(j);
			if(j == points.size()-1)
				g2.setStroke(new BasicStroke(5));
			
			if(a.size() < 1)
				continue;
			
			if(a.size() == 1) {
				Point p = a.get(0);
				g2.drawLine(p.x*20+8+ Constants.Padding, p.y*20+8, p.x*20+12 + Constants.Padding, p.y*20+12);
			}
			else {
				for(int i = 0; i<a.size()-1; ++i) {
					Point p = a.get(i);
					Point p2 = a.get(i+1);
					g2.drawLine(p.x*20+10+ Constants.Padding, p.y*20+10, p2.x*20+10 + Constants.Padding, p2.y*20+10);
				}
			}
		}
		g2.setStroke(new BasicStroke(1));
	}
	
	
	
	
}

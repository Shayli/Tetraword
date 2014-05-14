package Jeu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Briques.Case;
import Jeu.Constants.Key;

/**
 * Classe Worddle
 * Gère le mode Worddle
 * @author Monia, Laury & André
 * @version 1 
 */
public class Worddle extends GameMode {
	protected ArrayList<String> words;
	protected ArrayList<ArrayList<Point>> points;
	protected Point lastPoint;
	private String currentWord;
	private long timeLeft;
	private Image worddleImg;
	
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
		
		ImageIcon a = new ImageIcon("resources/worddleImg.png", ""); 
		worddleImg = a.getImage();
		p.wordle.setEnabled(false);
	}
	
	public void click(int x, int y) {
		//grille;
		if(grille.isInverted())
			y = Grille.rows - (y+1);
		Point curr = new Point(x,y);
		if(Math.abs(lastPoint.x-curr.x) > 1 || Math.abs(lastPoint.y-curr.y) > 1)
			return;
		
		for(Point p : points.get(points.size()-1)){
			if(curr.equals(p))
				return;
		}
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
				words.add(currentWord);
				
				ArrayList<Point> tmp = points.get(points.size()-1);
				points.add(new ArrayList<Point>());
				int r = (int)(Math.random()*100) % (tmp.size()-1) +1;
				Point p = new Point(tmp.get(r).x, tmp.get(r).y);
				points.get(points.size()-1).add(p);
				currentWord = ""+currentWord.charAt(r);
			} else {
				currentWord = "";
				Point p = points.get(points.size()-1).get(0);
				points.get(points.size()-1).clear();
				points.get(points.size()-1).add(p);
				currentWord += grille.getCase(p.x, p.y).letter();
				lastPoint = p;
			}
		}
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
			checkWords();
			plateau.wordle.setEnabled(true);
			plateau.changeMode(new Tetris(plateau));
			Constants.releaseMouse();
		}
	}

	private void checkWords() {
		if(!Constants.wordExists(currentWord))
			points.remove(points.size()-1);
		
		for(ArrayList<Point> ap : points) {
			for(Point p: ap) {
				grille.removeCase(p.x,p.y);
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		grille.draw(g);
		
		g.setColor(Color.white);
		g.setFont(Constants.pacifico); 
		int sec = (int)timeLeft/1000;
		g.drawString("Time spend: "+sec, 355, 90);
		
		g.drawImage(worddleImg, 335, 455, null);
		g.drawString(currentWord.toLowerCase(), 370, 503);

		g.setColor(Color.pink);
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
				if(grille.isInverted())
					g2.drawLine((int)(p.x*Case.size+22+Constants.MarginImg), (int)((Grille.rows-(p.y))*Case.size+28+Constants.MarginImg), (int)(p.x*Case.size+27+Constants.MarginImg), (int)((Grille.rows-(p.y))*Case.size+32+Constants.MarginImg));
				else
					g2.drawLine((int)(p.x*Case.size+22+Constants.MarginImg), (int)((p.y+1)*Case.size+28+Constants.MarginImg), (int)(p.x*Case.size+27+Constants.MarginImg), (int)((p.y+1)*Case.size+32+Constants.MarginImg));
			}
			else {
				for(int i = 0; i<a.size()-1; ++i) {
					Point p = a.get(i);
					Point p2 = a.get(i+1);
					if(grille.isInverted())
						g2.drawLine((int)(p.x*Case.size+25+Constants.MarginImg), (int)((Grille.rows-(p.y))*Case.size+30+Constants.MarginImg), (int)(p2.x*Case.size+25+Constants.MarginImg), (int)((Grille.rows-(p2.y))*Case.size+30+Constants.MarginImg));
					else
						g2.drawLine((int)(p.x*Case.size+25+Constants.MarginImg), (int)((p.y+1)*Case.size+30+Constants.MarginImg), (int)(p2.x*Case.size+25+Constants.MarginImg), (int)((p2.y+1)*Case.size+30+Constants.MarginImg));
				}
			}
		}
		g2.setStroke(new BasicStroke(1));
	}
	
	
	
	
}

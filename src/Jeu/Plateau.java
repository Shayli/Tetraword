package Jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;
import java.util.Timer;

import javax.swing.JPanel;

import Briques.Brique;
import Patterns.Observer;

public class Plateau extends JPanel implements KeyListener, MouseListener {
	
	public Grille grille;
	private Difficulte dif;
	private ArrayDeque hist; 
	
	private int score;
	private long lStartTime;
	private int difficulte;
	private boolean clicked;
	
	GameMode current;
	public int playerId;
	
	public Plateau(int player){
		this.setFocusable(true);
		this.requestFocus();
		grille = new Grille();
		
		lStartTime = System.currentTimeMillis();
		difficulte = 10;
		playerId = player;
		current = new Tetris(this);
		clicked = false;
		addKeyListener(this);
		addMouseListener(this);
		grille.events.add(new Observer() {

			@Override
			public void notify(String s, Object o) {
				if(s == "line") {
					grille.removeRow((Integer)o);
					score += 1;
					if(difficulte > 1 && score % 5 == 0)
						--difficulte;
				}
				
			}
			
		});
	}
	
	public void destroyLine(int x){
		
	}
	public void destroyBrick(int x, int y){
	
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		current.keyPress(k);
		revalidate();
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int k = arg0.getKeyCode();
		current.keyRelease(k);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		//g.setFont(new Font("Clear Sans", Font.PLAIN, 13)); 
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(0+Constants.Padding, 0, (grille.cols+1) * 20 +1, (grille.rows+1)*20+1, 10, 10);
		

		//Score
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(250+Constants.Padding, 0, 100, 80, 10, 10);
		g.setColor(Color.white);
		g.drawString("Score", 270+Constants.Padding, 20);
		
		//Next brique
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(250+Constants.Padding, 100, 100, 80, 10, 10);
		g.setColor(Color.white);
		g.drawString("Next Brique", 270+Constants.Padding, 120);
	
		current.draw(g);
	}
	
	public void update() {
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;
		current.update(difference);
		lStartTime = lEndTime;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	/*	int posX = e.getX();
        int posY = e.getY();
        
        current.click(posX, posY);*/
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		clicked = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int posX = e.getX();
        int posY = e.getY();
        if(!clicked) {
        	current.click(posX, posY);
        	clicked = true;
        }
    }
	
	public void changeMode(GameMode mode) {
		current = mode;
	}
}

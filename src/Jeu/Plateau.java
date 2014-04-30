package Jeu;

import java.awt.Color;
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
	private static class KP{
		static int ROTATE = 0;
		static int DOWN = 1;
		static int LEFT = 2;
		static int RIGHT = 3;
	}
	public Grille grille;
	private Difficulte dif;
	private ArrayDeque hist; 
	private int[] command = {KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT};
	private int[] command2 = {KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D};
	private int[] command3 = {KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L};
	private int score;
	private Brique nextBrique;
	private long lStartTime;
	private int difficulte;
	private boolean fastForward;
	private Anagramme anagrammeMode; 
	
	public Plateau(){
		this.setFocusable(true);
		this.requestFocus();
		grille = new Grille();
		
		lStartTime = System.currentTimeMillis();
		difficulte = 10;
		fastForward = false;
		
		addKeyListener(this);
		addMouseListener(this);
		grille.events.observers.add(new Observer() {

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
		if(k == command[KP.ROTATE])
			grille.rotateCurrent();
		else if(k == command[KP.LEFT])
			grille.moveLeftCurrent();
		else if(k == command[KP.RIGHT])
			grille.moveRightCurrent();
		else if(k == command[KP.DOWN])
			fastForward = true;
		
		revalidate();
		repaint();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int k = arg0.getKeyCode();
		if(k == command[KP.DOWN])
			fastForward = false;
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		g.setColor(new Color(187, 173, 160));
		g.fillRoundRect(0+Constants.Padding, 0, (grille.cols+1) * 20 +1, this.getHeight()-19, 10, 10);
		

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
	
		grille.draw(g);
	}
	
	public void update() {
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;
		if(fastForward) {
			if(difference > 50) {
				grille.update();
				lStartTime = lEndTime;
			}
		}
		else {
			if(difference > 100*difficulte) {
				grille.update();
				lStartTime = lEndTime;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int posX = e.getX();
        int posY = e.getY();
        /* conversion à faire*/
        if(anagrammeMode != null) {
        
        }
        
        //System.out.println("X: " + posX + " Y: " + posY);
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}
}

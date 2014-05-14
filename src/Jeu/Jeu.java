package Jeu;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Briques.Case;

/**
 * Classe Jeu
 * Gère tout le tetris
 * @author Monia, Laury & André
 * @version 1 
 */
public class Jeu extends JFrame implements KeyListener, MouseListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Plateau> plateaux;
	private JPanel home;
	private JPanel content;
	private Boolean started = false;
	private boolean clicked;
	private Settings settings;
	private Rules rules;
	private HighScorePanel score;
	
	public Jeu(){
	    this.setTitle("Tetraword");
	    this.setSize(500, 700);
	    this.setLocationRelativeTo(null);
	    plateaux = new ArrayList<Plateau>();
	    home = new Home(this);
	    home();
	    
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.requestFocus();
	}
	
	public void home(){
		this.setSize(500, 700);
		started = false;
		this.setContentPane(home);
		this.validate();
		this.repaint();
	}
	
	public void solo(){
		this.setSize(500, 700);
		plateaux.clear();
	    plateaux.add(new Plateau(this,0));
	    content  = new JPanel(new GridLayout(1,1));
	    content.add(plateaux.get(0));
	    this.setContentPane(content);
	    this.repaint();
	    started = true;
	    this.requestFocus();
	}
	
	public void duo(){
		this.setSize(1000, 700);
		plateaux.clear();
		plateaux.add(new Plateau(this, 0));
		plateaux.add(new Plateau(this, 1));
		content = new JPanel(new GridLayout(1,2));
		content.add(plateaux.get(0));
		content.add(plateaux.get(1));
		this.setContentPane(content);
	    this.repaint();
	    started = true;
	    this.requestFocus();
	}
	
	public void settings(){
		this.setSize(500, 700);
		settings = new Settings(this);
		this.setContentPane(settings);
		this.validate();
		this.repaint();
	}
	
	public void rules(){
		this.setSize(500, 700);
		rules = new Rules(this);
		this.setContentPane(rules);
		this.validate();
		this.repaint();
	}
	
	public void highscore(int indice){
		this.setSize(500, 700);
		score = new HighScorePanel(indice, this);
		this.setContentPane(score);
		this.validate();
		this.repaint();
	}
	
	public static void main(String[] args){
		Constants.initialize();
		Jeu tetris = new Jeu();
		tetris.run();
	}
	
	public void quit() {
		System.out.println("quitter");
		if(0 == JOptionPane.showConfirmDialog(this,"Souhaitez-vous quitter le jeu ?","Demande de confirmation",JOptionPane.OK_OPTION)) {
			dispose();
			System.exit(0);
		}
	}
	
	public ArrayList<Plateau> getPlateaux(){
		return plateaux;
	}
	public void startGame(int n){
		//plateau = new Plateau();
		System.out.println(n+" joueurs");
	}
	
	public void run() {
		while(true) {
			//System.out.println("update");
			if(started){
				boolean alive = false;
				for(Plateau p: plateaux) {
					p.update();
					if(p.isAlive())
						alive = true;
				}
				if(!alive) {
					for(Plateau p: plateaux)
						p.askForName();
					
					highscore(-1);
				}
				
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						for(Plateau p: plateaux) {
							p.revalidate();
							p.repaint();
						}
					}
				});
			}
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void stopGame(){
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		for(Plateau p: plateaux)
			p.keyPressed(k);
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		int k = arg0.getKeyCode();
		for(Plateau p: plateaux)
			p.keyReleased(k);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
	
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
        	for(Plateau p: plateaux) {
        		p.click((int)((posX-10)/Case.size),(int)((posY-80)/Case.size));
        		posX -= 500;
        	}
        
        	clicked = true;
        }
    }
}

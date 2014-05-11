package Jeu;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Briques.Case;


public class Jeu extends JFrame implements KeyListener, MouseListener  {
	private ArrayList<Plateau> plateaux;
	private JPanel home;
	private JPanel content;
	private int currMode = 1; //1 pour Tetris, 2 pour Anagramme, 3 pour Worddle 
	private Boolean started = false;
	private boolean clicked;
	
	public Jeu(){
		    this.setTitle("Tetraword");
		    this.setSize(500, 700);
		    this.setLocationRelativeTo(null);
		    plateaux = new ArrayList<Plateau>();
//		    this.setBackground(new Color(250, 248, 239));
		    
		   /* JPanel north = new JPanel();
		    JLabel tetraword = new JLabel(); 
		    tetraword.setFont(new Font("Clear Sans", Font.PLAIN, 30)); 
		    tetraword.setText("Tetraword");
		    tetraword.setForeground(new Color(119, 110, 101));
		    plateau = new Plateau(0);
		    north.add(tetraword);
		    content.add(north, BorderLayout.NORTH);
		    content.add(plateau, BorderLayout.CENTER)*/
		    
		    home = new Home(this);
	        //this.setContentPane(content);
		    this.setContentPane(home);
		    
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setVisible(true);
		this.setFocusable(true);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.requestFocus();
	}
	
	public void solo(){
	    plateaux.add(new Plateau(0));
	    content  = new JPanel(new GridLayout(1,1));
	    content.add(plateaux.get(0));
	    this.setContentPane(content);
	    this.repaint();
	    started = true;
	    this.requestFocus();
	}
	
	public void duo(){
		this.setSize(1000, 700);
		plateaux.add(new Plateau(0));
		plateaux.add(new Plateau(1));
		content = new JPanel(new GridLayout(1,2));
		content.add(plateaux.get(0));
		content.add(plateaux.get(1));
		this.setContentPane(content);
	    this.repaint();
	    started = true;
	    this.requestFocus();
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
	
	
	public void startGame(int n){
		//plateau = new Plateau();
		System.out.println(n+" joueurs");
	}
	
	public void run() {
		while(true) {
			//System.out.println("update");
			if(started){
				for(Plateau p: plateaux)
					p.update();
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

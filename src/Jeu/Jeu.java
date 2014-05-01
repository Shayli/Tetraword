package Jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Briques.Case;

public class Jeu extends JFrame implements WindowListener {
	private Plateau plateau;
	private JPanel content = new JPanel(new BorderLayout());
	private JLabel welcome = new JLabel();
	private Menu menu = new Menu();
	private int currMode = 1; //1 pour Tetris, 2 pour Anagramme, 3 pour Worddle 
	
	public Jeu(){
		    this.setTitle("Tetraword");
		    this.setSize(500, 500);
		    this.setLocationRelativeTo(null);
		    this.setBackground(new Color(250, 248, 239));
		    JPanel north = new JPanel();
		    JLabel tetraword = new JLabel(); 
		    tetraword.setFont(new Font("Clear Sans", Font.PLAIN, 30)); 
		    tetraword.setText("Tetraword");
		    tetraword.setForeground(new Color(119, 110, 101));
		    plateau = new Plateau(0);
		    north.add(tetraword);
		    welcome.setText("Bienvenue sur Tetraword. Utilisez le menu pour lancer une partie");
		    north.add(welcome);
		    content.add(north, BorderLayout.NORTH);
		    
		   
		    content.add(plateau, BorderLayout.CENTER);
		    
		  /*  final JPanel casePan = new JPanel() {
	            @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                c = new Case(100, 50, 'A', 30);
	                c.draw(g);
	            }
	        };
	        content.add(casePan, BorderLayout.CENTER);
	        */
	        this.setContentPane(content);
	        this.setJMenuBar(menu);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setVisible(true);
		
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
			plateau.update();
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					plateau.revalidate();
					plateau.repaint();
				}
			});

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
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
}

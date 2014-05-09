package Jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class Jeu extends JFrame implements WindowListener {
	private Plateau plateau;
	private JPanel home;
	private JPanel content = new JPanel(new BorderLayout());
	private int currMode = 1; //1 pour Tetris, 2 pour Anagramme, 3 pour Worddle 
	private Boolean started = false;
	
	public Jeu(){
		    this.setTitle("Tetraword");
		    this.setSize(500, 700);
		    this.setLocationRelativeTo(null);
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
		
	}
	
	public void solo(){
	    plateau = new Plateau(0);
	    content.add(plateau, BorderLayout.CENTER);
	    this.setContentPane(content);
	    this.repaint();
	    plateau.requestFocus();
	    started = true;
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
				plateau.update();
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						plateau.revalidate();
						plateau.repaint();
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

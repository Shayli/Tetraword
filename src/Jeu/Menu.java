package Jeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;

/**
 * Classe Menu
 * Créer un Menu sur la JFrame
 * @author Monia, Laury & André
 * @version 1 
 */
public class Menu extends JMenuBar implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static Menu menu;
	private JMenu jeu = new JMenu("Jeu");
	private JMenuItem solo = new JMenuItem("Jeu Solo");
	private JMenuItem multi = new JMenuItem("Jeu Multi");
	private JMenuItem stop = new JMenuItem("Stop");
	private JMenuItem quit = new JMenuItem("Quitter");
	
	private JMenu score = new JMenu("Scores");
	
	public Menu(){
		this.add(jeu);
		jeu.add(solo);
		solo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Jeu solo");
			}
		});
		
		jeu.add(multi);
		multi.addActionListener(this);
		jeu.add(new JSeparator());
		jeu.add(stop);
		stop.addActionListener(this);
		jeu.add(quit);
		quit.addActionListener(this);

		
		this.add(score);
	}
	
	public void isPlaying(boolean b) {
		solo.setEnabled(!b);
		multi.setEnabled(!b);
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.println("Menu : "+command);
		if (command.equals("Jeu Solo")){
			System.out.println("solo");
			//tetris.startGame(1);
		}else if (command.equals("Jeu Multi")){
			System.out.println("multi");
			//tetris.startGame(2);
		}else if (command.equals("Stop")) {
			if(0 == JOptionPane.showConfirmDialog(this,"Souhaitez-vous arr�ter la partie en cours ?","Demande de confirmation",JOptionPane.OK_OPTION)) {
				//tetris.stopGame();
				//isPlaying(false);
				System.out.println("stop");
			}
		} else if (command.equals("Quitter")) {
			System.out.println("quitter");
			//tetris.quit();
		}
	}

}

package Jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Jeu.Constants.Key;
import Patterns.Observer;

/**
 * Classe Plateau 
 * @author Monia, Laury & André
 * @version 1 
 */
public class Plateau extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Grille grille;
	//private ArrayDeque hist; 
	
	private int score;
	private long lStartTime;
	public int difficulte; //1 pour facile, 2 pour moyen, 3 pour difficile
	
	GameMode current;
	public int playerId;
	private Image fond;
	private Image fond2;
	private Image grilleImg;
	private Image scoreImg;
	private Image next;
	public JButton wordle;
	private Jeu jeu;
	private String name;
	private boolean enterName, alive;
	
	public Plateau(Jeu j, int player){
		this.setLayout(null);
		jeu = j;
		ImageIcon a = new ImageIcon("resources/fond1.jpg", ""); 
		fond = a.getImage();
		a = new ImageIcon("resources/fond2.jpg", ""); 
		fond2 = a.getImage();
		a = new ImageIcon("resources/grille.png", "");
		grilleImg = a.getImage();
		a = new ImageIcon("resources/scoreImg.png", "");
		scoreImg = a.getImage();
		a = new ImageIcon("resources/next.png", "");
		next = a.getImage();
		a = new ImageIcon("resources/wordle.png", "");
		wordle = new JButton(a);
		super.setBackground(new Color(255,255,255,0));
		this.add(wordle);
		playerId = player;
		enterName = true;
		alive = true;
		
		Insets insets = this.getInsets();
		Dimension size = wordle.getPreferredSize();
		wordle.setBounds(330 + insets.left, 455 + insets.top, size.width, size.height);
		wordle.setFocusPainted( false );
		wordle.setBorderPainted(false); 
		wordle.setOpaque( false ); 
		wordle.setContentAreaFilled(false);
		wordle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				current.keyPress(Constants.Commands[playerId][Key.MODE]);
				jeu.requestFocus();
			}
		});
		
		grille = new Grille();
		
		lStartTime = System.currentTimeMillis();
		difficulte = 10;
		
		current = new Tetris(this);

		grille.events.add(new Observer() {

			@Override
			public void notify(String s, Object o) {
				if(s.equals("line")) {
					if(Constants.takeMouse()) {
						Stack<Integer> o2 = (Stack<Integer>)o;
						changeMode(new Anagramme(Plateau.this, o2));
						addPoints(50*o2.size()*o2.size());
						
					}
				} else if(s.equals("lose")) {
					alive = false;
				} else if(s.equals("block")) {
					addPoints(25);
				}
				
			}
			
		});
	}
	
	private void computeDifficulte() {
		if(score < 100)
			difficulte = 10;
		else if(score < 200)
			difficulte =  9;
		else if(score < 400)
			difficulte =  8;
		else if(score < 800)
			difficulte =  7;
		else if(score < 1600)
			difficulte =  6;
		else if(score < 3200)
			difficulte = 5;
		else if(score < 4800)
			difficulte = 4;
		else if(score < 6400)
			difficulte = 3;
		else if(score < 8000)
			difficulte = 2;
		else
			difficulte = 1;
		
		
	}
	

	
	@Override
	public void paint(Graphics g){
		if(playerId == 0){
			g.drawImage(fond, 0, 0, null);
		}else{
			g.drawImage(fond2, 0, 0, null);
		}
		
		super.paint(g);
		g.setFont(Constants.pacifico); 
		g.setColor(new Color(81,20,21));
		
		g.drawString("Tetraword", 185, 25);
		
		g.drawImage(grilleImg, 10, 55, null);
		
		//g.setColor(new Color(187, 173, 160));
		//g.fillRoundRect(0+Constants.Padding, 0, (grille.cols) * 20 +1, (grille.rows)*20+1, 10, 10);
		

		//Score
		g.drawImage(scoreImg, 330, 130, null);
		g.setColor(Color.white);
		g.drawString("Score", 330+Constants.Padding, 150);
		g.drawString(""+score, 330+Constants.Padding, 180);
		//Next brique
		g.drawImage(next, 330, 240, null);
		g.setColor(Color.white);
		g.drawString("Next Brique", 330+Constants.Padding, 260);
	
		
		current.draw(g);
	}
	
	public void update() {
		long lEndTime = System.currentTimeMillis();
		long difference = lEndTime - lStartTime;
		current.update(difference);
		lStartTime = lEndTime;
	}
	
	public void setDifficulte(int i){
		difficulte = i;
	}

	public void changeMode(GameMode mode) {
		current = mode;
	}

	public void click(int posX, int posY) {
		current.click(posX, posY);
	}

	public void keyReleased(int k) {
		current.keyRelease(k);
	}

	public void keyPressed(int k) {
		current.keyPress(k);
	}



	public void addPoints(int i) {
		score += i;
		computeDifficulte();
	}

	public boolean isAlive() {
		return alive;
	}
	
	public void askForName() {
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					if(!enterName)
						return;
					enterName = false;
					String name;
					do {
						name = JOptionPane.showInputDialog(jeu, "Player "+(playerId+1)+" scored "+score,"Enter your nickname", JOptionPane.QUESTION_MESSAGE);
					} while(name == null);
				}
			});
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

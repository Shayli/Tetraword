package Jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Jeu.Constants.Key;
import Modificateurs.Modificateur;
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
	public int niveau, difficulte; //1 pour facile, 2 pour moyen, 3 pour difficile

	
	GameMode current;
	public int playerId;
	private Image fond;
	private Image fond2;
	private Image grilleImg;
	private Image scoreImg;
	private Image next;
	public JButton wordle;
	private Jeu jeu;
	private boolean enterName, alive;
	public int scoreModifier;
	private ArrayList<Modificateur> modifiers;
	
	
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
		scoreModifier = 1;
		modifiers = new ArrayList<Modificateur>();
		
		Insets insets = this.getInsets();
		Dimension size = wordle.getPreferredSize();
		wordle.setBounds(315 + insets.left, 455 + insets.top, size.width, size.height);
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
		niveau = 10;
		difficulte = Constants.difficulty;
		
		current = new Tetris(this);

		grille.events.add(new Observer() {

			@Override
			public void notify(String s, Object o) {
				if(s.equals("line")) {
					if(Constants.takeMouse()) {
						Stack<Integer> o2 = (Stack<Integer>)o;
						changeMode(new Anagramme(Plateau.this, o2, difficulte));
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
			niveau = 10;
		else if(score < 200)
			niveau =  9;
		else if(score < 400)
			niveau =  8;
		else if(score < 800)
			niveau =  7;
		else if(score < 1600)
			niveau =  6;
		else if(score < 3200)
			niveau = 5;
		else if(score < 4800)
			niveau = 4;
		else if(score < 6400)
			niveau = 3;
		else if(score < 8000)
			niveau = 2;
		else
			niveau = 1;
		
		
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
		Iterator it = modifiers.iterator();
		while(it.hasNext()) {
			Modificateur m = (Modificateur)it.next();
			if(m.done()) {
				m.stop();
				it.remove();
			}
			else
				m.update(difference);
		}
		
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
		score += i*scoreModifier;
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
					Pattern p = Pattern.compile("[a-zA-Z0-9]");
					do {
						name = JOptionPane.showInputDialog(Plateau.this, "Player "+(playerId+1)+" scored "+score,"Enter your nickname", JOptionPane.QUESTION_MESSAGE);
						if(name.isEmpty() || name == null)
							name = "Player"+(playerId+1);
					} while(!p.matcher(name).find());
					Constants.writeScore(name, score);
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
	
	public void addModifier(Modificateur m) {
		m.start();
		modifiers.add(m);
	}
}

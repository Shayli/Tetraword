package Jeu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayDeque;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Briques.Case;
import Patterns.Observer;

public class Plateau extends JPanel {
	
	public Grille grille;
	private Difficulte dif;
	private ArrayDeque hist; 
	
	private int score;
	private long lStartTime;
	private int difficulte;
	
	GameMode current;
	public int playerId;
	private Image fond;
	private Image fond2;
	private Image grilleImg;
	private Image scoreImg;
	private Image next;
	private JButton wordle;
	
	public Plateau(int player){
		this.setLayout(null);
		
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
		
		
		
		
		Insets insets = this.getInsets();
		Dimension size = wordle.getPreferredSize();
		wordle.setBounds(330 + insets.left, 455 + insets.top, size.width, size.height);
		wordle.setFocusPainted( false );
		wordle.setBorderPainted(false); 
		wordle.setOpaque( false ); 
		wordle.setContentAreaFilled(false);
		wordle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Plateau.this.changeMode(new Worddle(Plateau.this));
			}
		});
		
		grille = new Grille();
		
		lStartTime = System.currentTimeMillis();
		difficulte = 10;
		playerId = player;
		current = new Tetris(this);

		grille.events.add(new Observer() {

			@Override
			public void notify(String s, Object o) {
				if(s == "line") {
					System.out.println("line");
					changeMode(new Anagramme(Plateau.this, (Integer)o));
					score += 1;
					if(difficulte > 1 && score % 5 == 0)
						--difficulte;
				}
				
			}
			
		});
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
}

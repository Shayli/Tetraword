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
	private Image fond;
	private Image grilleImg;
	private Image scoreImg;
	private Image next;
	private JButton wordle;
	
	public Plateau(int player){
		this.setFocusable(true);
		this.requestFocus();
		this.setLayout(null);
		
		ImageIcon a = new ImageIcon("resources/fond.jpg", ""); 
		fond = a.getImage();
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
				
			}
		});
		
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
					System.out.println("line");
					changeMode(new Anagramme(Plateau.this, (Integer)o));
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
		g.setFont(Constants.pacifico); 
		g.setColor(new Color(81,20,21));
		g.drawImage(fond, 0, 0, null);
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
		int posX = (int)((e.getX()-10)/Case.size);
        int posY = (int)((e.getY()-55)/Case.size);
        if(!clicked) {
        	current.click(posX, posY);
        	clicked = true;
        }
    }
	
	public void changeMode(GameMode mode) {
		current = mode;
	}
}

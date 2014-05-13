package Jeu;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Jeu.Constants.Key;

/**
 * Classe Settings
 * Gère la page de configuration 
 * @author Monia, Laury & André
 * @version 1 
 */
public class Settings extends JPanel implements KeyListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fond;
	private JComboBox languages;
	private JComboBox difficult;
	private boolean writeCommand;
	private JButton j1g, j1d, j1h, j1b, j1e, j2g, j2d, j2h, j2b, j2e;
	protected int player;
	protected int key;
	
	public Settings(final Jeu j){
		//GridLayout l = new GridLayout(8, 4);
		super();	
		addKeyListener(this);
		writeCommand = false;
		ImageIcon a = new ImageIcon("resources/settings.jpg", ""); 
		fond = a.getImage();
		this.setLayout(new GridLayout(0, 4));
		JLabel lang = new JLabel("Langues");
		languages = new JComboBox();
		languages.addItem("Français");
		languages.addItem("Anglais");
		languages.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().equals("Anglais")) {
					Constants.loadDictionary("english");
				}else{
					Constants.loadDictionary("french");
				}
			}
		});
		//languages.setBounds(0,0,100, 200);
		this.add(lang);
		this.add(languages);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		
		JLabel com = new JLabel("Commandes");
		JLabel j1 = new JLabel("Joueur 1");
		JLabel j2 = new JLabel("Joueur 2");
		JLabel g = new JLabel("Gauche");
		j1g = new JButton();
		j1g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 0;
				key = Key.LEFT;
				Settings.this.requestFocus();
			}
		});
		j1d = new JButton();
		j1d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 0;
				key = Key.RIGHT;
				Settings.this.requestFocus();
			}
		});
		j1h = new JButton();
		j1h.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 0;
				key = Key.ROTATE;
				Settings.this.requestFocus();
			}
		});
		j1b = new JButton();
		j1b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 0;
				key = Key.DOWN;
				Settings.this.requestFocus();
			}
		});
		j1e = new JButton();
		j1e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 0;
				key = Key.MODE;
				Settings.this.requestFocus();
			}
		});
		j2g = new JButton();
		j2g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 1;
				key = Key.LEFT;
				Settings.this.requestFocus();
			}
		});
		j2d = new JButton();
		j2d.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 1;
				key = Key.RIGHT;
				Settings.this.requestFocus();
			}
		});
		j2h = new JButton();
		j2h.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 1;
				key = Key.ROTATE;
				Settings.this.requestFocus();
			}
		});
		j2b = new JButton();
		j2b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 1;
				key = Key.DOWN;
				Settings.this.requestFocus();
			}
		});
		j2e = new JButton();
		j2e.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				writeCommand = true;
				player = 1;
				key = Key.MODE;
				Settings.this.requestFocus();
			}
		});		
		JLabel d = new JLabel("Droite");
		JLabel fall = new JLabel("Chute");
		JLabel rotate = new JLabel("Rotation");
		JLabel mode = new JLabel("Mode");
		this.add(com);
		this.add(new JLabel(""));
		this.add(j1);
		this.add(j2);
		
		this.add(new JLabel(""));
		this.add(g);
		this.add(j1g);
		this.add(j2g);
			
		this.add(new JLabel(""));
		this.add(d);
		this.add(j1d);
		this.add(j2d);
			
		this.add(new JLabel(""));
		this.add(fall);
			this.add(j1b);
			this.add(j2b);
			
		this.add(new JLabel(""));
		this.add(rotate);
			this.add(j1h);
			this.add(j2h);
			
		this.add(new JLabel(""));
		this.add(mode);
			this.add(j1e);
			this.add(j2e);
			
						
		JLabel diff = new JLabel("Difficulté");
		difficult = new JComboBox();
		difficult.addItem("Facile");
		difficult.addItem("Moyen");
		difficult.addItem("Difficile");
		difficult.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getItem().equals("Facile")) {
					for(Plateau p : j.getPlateaux())
						p.setDifficulte(1);
				}else if (e.getItem().equals("Moyen")){
					for(Plateau p : j.getPlateaux())
						p.setDifficulte(2);
				}else{
					for(Plateau p : j.getPlateaux())
						p.setDifficulte(3);
				}
			}
		});
		this.add(diff);
		this.add(difficult);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		
		JButton back = new JButton("Retour");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.home();
			}
		});
		this.add(back);
		refreshCommand();
		this.revalidate();
		this.repaint();
	}
	
	public Insets getInsets() {
		 return new Insets(80,80,80,80);
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, null);
    }
	
	public void refreshCommand(){
		j1g.setText(Constants.getCommand(0, Key.LEFT));
		j1d.setText(Constants.getCommand(0, Key.RIGHT));
		j1h.setText(Constants.getCommand(0, Key.ROTATE));
		j1b.setText(Constants.getCommand(0, Key.DOWN));
		j1e.setText(Constants.getCommand(0, Key.MODE));
		j2g.setText(Constants.getCommand(1, Key.LEFT));
		j2d.setText(Constants.getCommand(1, Key.RIGHT));
		j2h.setText(Constants.getCommand(1, Key.ROTATE));
		j2b.setText(Constants.getCommand(1, Key.DOWN));
		j2e.setText(Constants.getCommand(1, Key.MODE));
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if(writeCommand) {
			System.out.println(k);
			Constants.Commands[player][key] = k;
			writeCommand = false;
			refreshCommand();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//int k = e.getKeyCode();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
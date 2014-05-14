package Jeu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Classe home
 * Gère la page d'accueil 
 * @author Monia, Laury & André
 * @version 1 
 */
public class Home extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fond; 
	private JButton solo;
	private JButton versus;
	private JButton settings;
	private JButton rules;
	private JButton fr;
	private JButton en;
	
	
	public Home(final Jeu jeu){
		this.setLayout(null);
		
		ImageIcon a = new ImageIcon("resources/home.jpg", ""); 
		fond = a.getImage();
		a = new ImageIcon("resources/button.png", ""); 
		solo = new JButton("Solo",a);
		versus = new JButton("Versus", a);
		settings = new JButton("Settings", a);
		rules = new JButton("Rules", a);
		a = new ImageIcon("resources/fr.png", "");
		fr = new JButton(a);
		a = new ImageIcon("resources/en.png", "");
		en = new JButton(a);
		this.add(solo);
		this.add(versus);
		this.add(settings);
		this.add(rules);
		this.add(fr);
		this.add(en);
		Insets insets = this.getInsets();
		Dimension size = solo.getPreferredSize();
		solo.setBounds(180 + insets.left, 435 + insets.top, size.width, size.height);
		solo.setFocusPainted( false );
		solo.setBorderPainted(false); 
		solo.setOpaque( false ); 
		solo.setContentAreaFilled(false);
		solo.setHorizontalTextPosition(JButton.CENTER);
		solo.setVerticalTextPosition(JButton.CENTER);
		solo.setFont(Constants.pacifico);
		solo.setForeground(Color.white);
		solo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.solo();
			}
		});
		size = versus.getPreferredSize();
		versus.setBounds(173 + insets.left, 485 + insets.top, size.width, size.height);
		versus.setFocusPainted( false );
		versus.setBorderPainted(false); 
		versus.setOpaque( false ); 
		versus.setContentAreaFilled(false);
		versus.setHorizontalTextPosition(JButton.CENTER);
		versus.setVerticalTextPosition(JButton.CENTER);
		versus.setFont(Constants.pacifico);
		versus.setForeground(Color.white);
		versus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.duo();
			}
		});
		size = settings.getPreferredSize();
		settings.setBounds(170 + insets.left, 535 + insets.top, size.width, size.height);
		settings.setFocusPainted( false );
		settings.setBorderPainted(false); 
		settings.setOpaque( false ); 
		settings.setContentAreaFilled(false);
		settings.setHorizontalTextPosition(JButton.CENTER);
		settings.setVerticalTextPosition(JButton.CENTER);
		settings.setFont(Constants.pacifico);
		settings.setForeground(Color.white);
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.settings();
			}
		});
		size = rules.getPreferredSize();
		rules.setBounds(177 + insets.left, 585 + insets.top, size.width, size.height);
		rules.setFocusPainted( false );
		rules.setBorderPainted(false); 
		rules.setOpaque( false ); 
		rules.setContentAreaFilled(false);
		rules.setHorizontalTextPosition(JButton.CENTER);
		rules.setVerticalTextPosition(JButton.CENTER);
		rules.setFont(Constants.pacifico);
		rules.setForeground(Color.white);
		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jeu.rules();
			}
		});
		size = fr.getPreferredSize();
		fr.setBounds(360, 600, size.width, size.height);
		fr.setFocusPainted( false );
		fr.setBorderPainted(false); 
		fr.setOpaque( false ); 
		fr.setContentAreaFilled(false);
		fr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.loadDictionary("french");
			}
		});
		 
		size = en.getPreferredSize();
		en.setBounds(420, 600, size.width, size.height);
		en.setFocusPainted( false );
		en.setBorderPainted(false); 
		en.setOpaque( false ); 
		en.setContentAreaFilled(false);
		en.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Constants.loadDictionary("english");
			}
		});
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, null);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}

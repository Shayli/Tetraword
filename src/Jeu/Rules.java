package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Rules extends JPanel{
	private Image fond;
	private JTextArea text;
	JButton back;
	
	public Rules(final Jeu j){			
		super();
		this.setLayout(null);
		
		ImageIcon a = new ImageIcon("resources/fond.jpg", ""); 
		fond = a.getImage();
		text = new JTextArea("Le jeu se déroule comme un Tétris. A chaque tour de jeu, une \nbrique apparaît au milieu et en haut de l'écran et se met à chuter. "
				+ "\n\nUtilisez gauche-droite pour décaler la brique d'un cran pendant sa \nchute. "
				+ "\n\nUtilisez haut pour effectuer une rotation d'un quart de tour. "
				+ "\n\nUtilisez bas pour la faire descendre plus rapidement."
				+ ""
				+ "\nSur chacune des cases des briques se trouve une lettre. \nUne fois une ligne formée, le mode Anagramme est lancé."
				+ "\n\nCliquez sur les lettres qui vous intéressent dans la ligne \nformée pour construire le mot le plus long possible. \nPressez 'ENTER' pour valider. "
				+ "\nUne ligne est détruite uniquement si l'on a proposé un \nmot comportant au moins le pourcentage correspondant à la difficulté \nchoisie du nombre de lettres du meilleur anagramme existant."
				+ ""
				+ "\n\nQuand vous le souhaitez, le mode worddle peut s'activer. \nDans ce cas là, vous disposez d'un temps limité pour trouver un \nmaximum de mots du dictionnaire à partir d'une brique sélectionnée \naléatoirement."
				+ "\n\nCliquez sur les briques adjacentes pour constituer \nvotre mot et pressez 'ENTER' pour valider."
				+ "\n\nUne fois le mot validé, continuez en suivant le même \nprincipe à partir d'une brique du mot précédent. "
				+ "\n\nA la fin du temps, les briques formant un des \nmots valides sont détruites.");
		text.setEditable(false);
		text.setBackground( new Color(255, 255, 255, 150) );
		text.setMargin(new Insets(10,10,10,10));
		this.add(text);
		text.setBounds(20, 70, 460, 550);
		
		back = new JButton("Retour");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				j.home();
			}
		});
		back.setBounds(380, 620, 80, 30);
		this.add(back);
		this.revalidate();
		this.repaint();
	}
	
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, null);
        g.setFont(Constants.pacifico); 
		g.setColor(new Color(81,20,21));
        g.drawString("Règles du Jeu", 180, 40);
    }
}

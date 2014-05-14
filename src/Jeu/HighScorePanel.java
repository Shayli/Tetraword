package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HighScorePanel extends JPanel{
	private Image fond; 
	private JButton back;
	
	public HighScorePanel(int indice, final Jeu j){
		super();
		this.setLayout(null);
		ImageIcon a = new ImageIcon("resources/home.jpg", ""); 
		fond = a.getImage();
		
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
		g.setColor(Color.black);
		g.drawString("Pseudo", 100, 30);
		g.drawString("Score", 300, 30);
        for(int i = 2; i < highscores.length()+2; ++i){
        	if (i == indice){
        		g.setColor(Color.pink);
        		g.drawString(highscores.get(i).name, 100, i*30);
        		g.drawString(""+highscores.get(i).score, 300, i*30);
        	}else{
	        	g.drawString(highscores.get(i).name, 100, i*30);
	        	g.drawString(""+highscores.get(i).score, 300, i*30);        
        	}
        }
    }
}

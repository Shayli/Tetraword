package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class HighScorePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fond; 
	private JButton back;
	private int indice;
	
	public HighScorePanel(int indice, final Jeu j){
		super();
		this.setLayout(null);
		ImageIcon a = new ImageIcon("resources/fond.jpg", ""); 
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
        for(int i = 0; i < Constants.highscores.size(); ++i){
        	if (i == indice){
        		g.setColor(Color.pink);
        		g.drawString(Constants.highscores.get(i).name, 100, (i+2)*30);
        		g.drawString(""+Constants.highscores.get(i).score, 300, (i+2)*30);
        	}else{
	        	g.drawString(Constants.highscores.get(i).name, 100, (i+2)*30);
	        	g.drawString(""+Constants.highscores.get(i).score, 300, (i+2)*30);        
        	}
        }
    }
}

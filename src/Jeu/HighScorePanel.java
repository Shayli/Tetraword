package Jeu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Briques.Case;

public class HighScorePanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image fond; 
	//private JButton back;
	//private int indice;
	
	public HighScorePanel(int indice, final Jeu j){
		super();
		this.setLayout(null);
		ImageIcon a = new ImageIcon("resources/fond.jpg", ""); 
		fond = a.getImage();
		
		JButton back = new JButton("Retour");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("toto");
				j.home();
			}
		});
		back.setBounds(380, 620, 80, 30);
		back.requestFocus();

		this.add(back);
		this.revalidate();
		this.repaint();
	}
	
	public void paintComponent(Graphics g) {
        g.drawImage(fond, 0, 0, null);
        g.setColor(new Color(255, 255, 255, 120));
        g.fillRoundRect(90, 50, 300, 400, 10, 10);
        g.setFont(Constants.pacifico); 
		g.setColor(Color.black);
		g.drawString("Pseudo", 110, 80);
		g.drawString("Score", 300, 80);
        for(int i = 0; i < Constants.highscores.size(); ++i){
        	/*if (i == indice){
        		{
        			Graphics2D g2 = (Graphics2D) g;
        			g2.setStroke(new BasicStroke(3));
        			g.setColor(Color.pink);
        			g.drawRect(100,((i+4)*30)+10 ,270 ,30);
        		}
        		g.setColor(Color.black);
        		g.drawString(Constants.highscores.get(i).name, 110, (i+4)*30);
        		g.drawString(""+Constants.highscores.get(i).score, 300, (i+4)*30);
        	}else{*/
        		g.setColor(Color.black);
	        	g.drawString(Constants.highscores.get(i).name, 110, (i+4)*30);
	        	g.drawString(""+Constants.highscores.get(i).score, 300, (i+4)*30);        
        	/*}*/
        }
    }
}

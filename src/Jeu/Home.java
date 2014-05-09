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

public class Home extends JPanel implements ActionListener{
	private Image fond; 
	private JButton solo;
	private JButton multi;
	private JButton settings;
	public static Boolean b1 = false;
	private Boolean b2 = false;
	private Boolean b3 = false;
	
	
	public Home(final Jeu jeu){
		this.setLayout(null);
		
		ImageIcon a = new ImageIcon("resources/home.jpg", ""); 
		fond = a.getImage();
		a = new ImageIcon("resources/button.png", ""); 
		solo = new JButton("Solo",a);
		multi = new JButton("Multi", a);
		settings = new JButton("Settings", a);
		this.add(solo);
		this.add(multi);
		this.add(settings);
		Insets insets = this.getInsets();
		Dimension size = solo.getPreferredSize();
		solo.setBounds(180 + insets.left, 445 + insets.top, size.width, size.height);
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
		size = multi.getPreferredSize();
		multi.setBounds(179 + insets.left, 505 + insets.top, size.width, size.height);
		multi.setFocusPainted( false );
		multi.setBorderPainted(false); 
		multi.setOpaque( false ); 
		multi.setContentAreaFilled(false);
		multi.setHorizontalTextPosition(JButton.CENTER);
		multi.setVerticalTextPosition(JButton.CENTER);
		multi.setFont(Constants.pacifico);
		multi.setForeground(Color.white);
		multi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b2 = true;
			}
		});
		size = settings.getPreferredSize();
		settings.setBounds(170 + insets.left, 565 + insets.top, size.width, size.height);
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
				b3 = true;
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
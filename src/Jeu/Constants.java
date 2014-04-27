package Jeu;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Constants {
	public static int Padding = 20;
	public static Image Cross;
	public static Image S;
	public static Image Bar;
	public static Image L;
	public static Image Cube;
	
	public static void initialize(){
		ImageIcon a = new ImageIcon("resources/cross.png", ""); 
		Cross = a.getImage();
		a = new ImageIcon("resources/S.png", ""); 
		S = a.getImage();
		a = new ImageIcon("resources/bar.png", ""); 
		Bar = a.getImage();
		a = new ImageIcon("resources/L.png", ""); 
		L = a.getImage();
		a = new ImageIcon("resources/cube.png", ""); 
		Cube = a.getImage();
	}
}

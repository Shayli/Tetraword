package Jeu;

import java.awt.Image;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class Constants {
	public static int Padding = 20;
	public static Image Cross;
	public static Image S;
	public static Image Bar;
	public static Image L;
	public static Image Cube;
	public static Map<Character, Integer> letter; 
	
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
		
		letter = new HashMap<Character, Integer>();
		letter.put('E', 15);
		letter.put('A', 9);
		letter.put('I', 8);
		letter.put('N', 6);
		letter.put('O', 6);
		letter.put('R', 6);
		letter.put('S', 6);
		letter.put('T', 6);
		letter.put('U', 6);
		letter.put('L', 5);
		letter.put('D', 3);
		letter.put('M', 3);
		letter.put('G', 2);
		letter.put('B', 2);
		letter.put('C', 2);
		letter.put('P', 2);
		letter.put('F', 2);
		letter.put('H', 2);
		letter.put('V', 2);
		letter.put('J', 1);
		letter.put('Q', 1);
		letter.put('K', 1);
		letter.put('W', 1);
		letter.put('X', 1);
		letter.put('Y', 1);
		letter.put('Z', 1);
		int i = 0;
		for(Integer c:letter.values())
			i+= c;
		System.out.println(i);
	}
	
	public static char randomLetter(){
		int rnd = ((int)(Math.random()*10000)%100);
		for(Map.Entry<Character, Integer> e: letter.entrySet()){
			if(rnd < e.getValue())
				return e.getKey();
			rnd -= e.getValue();
		}
		return '1';
	}
}

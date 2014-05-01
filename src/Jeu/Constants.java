package Jeu;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

public class Constants {
	public static int[][] Commands = null;
	public static int Padding = 20;
	public static Image Cross;
	public static Image S;
	public static Image Bar;
	public static Image L;
	public static Image Cube;
	public static Map<Character, Integer> letter; 
	public static LinkedList<String> dictionary;
	
	public static class Key{
		static int ROTATE = 0;
		static int DOWN = 1;
		static int LEFT = 2;
		static int RIGHT = 3;
	}
	
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
		dictionary = new LinkedList<String>();
		loadDictionary("french");
		
		Commands = new int[3][4];
		loadCommands(0,KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
		loadCommands(1,KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D);
		loadCommands(2,KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L);
	}
	
	private static void loadCommands(int i, int vkUp, int vkDown, int vkLeft, int vkRight) {
		Commands[i][Key.ROTATE] = vkUp;
		Commands[i][Key.DOWN] = vkDown;
		Commands[i][Key.LEFT] = vkLeft;
		Commands[i][Key.RIGHT] = vkRight;
	}

	public static void loadDictionary(String filePath){	
		dictionary.clear();
		letter.clear();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("resources/"+filePath+".lang"));
			for(int i =0; i < 26; ++i){
				String line = scanner.next();
				int j = scanner.nextInt(); 
				letter.put(line.charAt(0), j);
			}
			scanner.close();
			scanner = new Scanner(new File("resources/"+filePath+".dico"));
			Pattern p = Pattern.compile("[^a-zA-Z]");
			
			while (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
			    if(!p.matcher(line).find())
			    	dictionary.add(line.toUpperCase());
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	
	public static boolean wordExists(String word){
		return dictionary.contains(word);
	}
}

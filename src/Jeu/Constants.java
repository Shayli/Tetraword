package Jeu;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

public class Constants {
	public static int Padding = 20;
	public static Image Cross;
	public static Image S;
	public static Image Bar;
	public static Image L;
	public static Image Cube;
	public static Map<Character, Integer> letter; 
	public static LinkedList<String> dictionary;
	
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

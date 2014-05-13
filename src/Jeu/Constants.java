package Jeu;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;

import Briques.Brique;
import sun.awt.Mutex;

/**
 * Classe Constants
 * Sert à initiliser des variables utilitaires 
 * @author Monia, Laury & André
 * @version 1 
 */
public class Constants {
	public static int[][] Commands = null;
	public static int Padding = 20;
	public static int MarginImg = 4;
	public static Image Cross;
	public static Image S;
	public static Image S2;
	public static Image Bar;
	public static Image L;
	public static Image L2;
	public static Image Cube;
	public static Map<Character, Integer> letter; 
	public static LinkedList<String> dictionary;
	public static Font pacifico;
	public static LinkedList<String> shortWords;
	private static boolean mouse;
	private static Mutex mutexBrique;
	private static HashMap<Integer, Brique> briques;
	
	public static class Key{
		static int ROTATE = 0;
		static int DOWN = 1;
		static int LEFT = 2;
		static int RIGHT = 3;
		static int MODE = 4;
	}
	
	public static void initialize(){
		ImageIcon a = new ImageIcon("resources/8.png", ""); 
		Cross = a.getImage();
		a = new ImageIcon("resources/2.png", ""); 
		S = a.getImage();
		a = new ImageIcon("resources/6.png", ""); 
		S2 = a.getImage();
		a = new ImageIcon("resources/10.png", ""); 
		Bar = a.getImage();
		a = new ImageIcon("resources/4.png", ""); 
		L = a.getImage();
		a = new ImageIcon("resources/7.png", ""); 
		L2 = a.getImage();
		a = new ImageIcon("resources/9.png", ""); 
		Cube = a.getImage();
		letter = new HashMap<Character, Integer>();
		dictionary = new LinkedList<String>();
		shortWords = new LinkedList<String>();
		mouse = false;
		mutexBrique = new Mutex();
		briques = new HashMap<Integer, Brique>();
		
		loadDictionary("french");
		
		Commands = new int[3][5];
		loadCommands(0,KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);
		loadCommands(1,KeyEvent.VK_Z, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_D, KeyEvent.VK_A);
		loadCommands(2,KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_U);
	
		try {
			pacifico = Font.createFont(Font.TRUETYPE_FONT, new File("resources/Pacifico.ttf"));
			pacifico = pacifico.deriveFont(Font.PLAIN,18);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pacifico);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public static void loadCommands(int i, int vkUp, int vkDown, int vkLeft, int vkRight, int vkEnter) {
		Commands[i][Key.ROTATE] = vkUp;
		Commands[i][Key.DOWN] = vkDown;
		Commands[i][Key.LEFT] = vkLeft;
		Commands[i][Key.RIGHT] = vkRight;
		Commands[i][Key.MODE] = vkEnter;
	}

	public static void loadDictionary(String filePath){	
		dictionary.clear();
		shortWords.clear();
		letter.clear();
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("resources/"+filePath+".lang"));
			System.out.println("ouverture "+filePath+".lang");
			for(int i =0; i < 26; ++i){
				String line = scanner.next();				
				int j = scanner.nextInt(); 
				letter.put(line.charAt(0), j);
				System.out.println(line + " "+ j);
			}
			scanner.close();
			scanner = new Scanner(new File("resources/"+filePath+".dico"), "ISO-8859-1");
			System.out.println("ouverture "+filePath+".dico");
			Pattern p = Pattern.compile("[^a-zA-Z]");
			while (scanner.hasNextLine()) {
			    String line = scanner.nextLine();
			    line = line.toLowerCase();
			    line = line.replace('à','a').replace('é', 'e').replace('ê', 'e').replace('è', 'e').replace('ä', 'a').replace('ï', 'i').replace('î',  'i').replace('ë', 'e').replace('ç', 'c');
			    if(!p.matcher(line).find()) {
			    	if(line.length() <= Grille.cols)
			    		shortWords.add(line.toUpperCase());
		    		else
		    			dictionary.add(line.toUpperCase());
			    }
			}
			//System.out.println(dictionary); 
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
		return dictionary.contains(word) || shortWords.contains(word);
	}
	
	public static void printDico() {
		for(String s : dictionary) {
			System.out.println(s);
		}
	}
	
	public static String findBestWord(String line) {
		String Word = ""; 
		boolean b = false ; 
		Combinations comb = new Combinations(line); //on créé toutes les combinaisons de String possibles
		comb.combine();
		Collections.sort(comb.stock, new LengthComparator());
        Collections.reverse(comb.stock);
                
        ListIterator<String> it = shortWords.listIterator();
        
        	//System.out.println(s);
        	for(int i=0; i<comb.stock.size(); i++) {
        		//System.out.println(comb.stock.get(i));
        		it = shortWords.listIterator(0); 
        		while(it.hasNext()) {
                	String s = (String)it.next();		    		
		    		b = isAnagram(comb.stock.get(i), s);		    		
			    	if(b) {			    		
			    		return  s; 	    		
			    	}
        		}
        	}  
			
		return Word; 
	}
	
	public static boolean isAnagram(String s1, String s2){

        // Early termination check, if strings are of unequal lengths,
        // then they cannot be anagrams
		

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        String sc1 = new String(c1);
        String sc2 = new String(c2);
        //System.out.println(sc1 + "    "  + sc2);
        return sc1.equals(sc2);
       
	}
	
	public static String getCommand(int p, int k){
		switch(Constants.Commands[p][k]){
		case KeyEvent.VK_ENTER:
			return "Enter";
		case KeyEvent.VK_UP:
			return "Up";
		case KeyEvent.VK_DOWN:
			return "Down";
		case KeyEvent.VK_LEFT:
			return "Left";
		case KeyEvent.VK_RIGHT:
			return "Right";
			
		default:
			return ""+(char)Constants.Commands[p][k];
		}
	}

	public static boolean takeMouse() {
		if(mouse)
			return false;
		
		mouse = true;	
		return true;
	}
	
	public static void releaseMouse() {
		mouse = false;
	}
	
	public static Brique nextBrique(Grille g, int i) {
		Brique ret = null;
		mutexBrique.lock();
		if(briques.isEmpty() || !briques.containsKey(i)) {
			ret = nextBrique();
			briques.put(i, ret);
		} else {
			ret = briques.get(i).clone();
			briques.remove(i);
		}
		mutexBrique.unlock();
		ret.grille = g;
		return ret;
	}
	
	private static Brique nextBrique() {
		int i = ((int)(Math.random()*100)) % 7;
		switch(i) {
		case 0:
			return new Briques.L();
		case 1:
			return new Briques.L2();
		case 2:
			return new Briques.S();
		case 3:
			return new Briques.S2();
		case 4:
			return new Briques.Cross();
		case 5:
			return new Briques.Cube();
		case 6:
			return new Briques.Bar();
		default:
			return null;
		}
	}
}

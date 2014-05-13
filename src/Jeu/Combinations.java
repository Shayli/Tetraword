package Jeu;
import java.util.ArrayList;

/**
 * Classe Combinaisons
 * @author Monia, Laury & André
 * @version 1 
 */
public class Combinations {
    private StringBuilder output = new StringBuilder();
    private final String inputstring;
    public ArrayList<String> stock; 
    
    public Combinations( final String str ){
        inputstring = str;
        //System.out.println("The input string  is  : " + inputstring);
        stock =  new ArrayList<String>();
    }
    
    
    public void combine() { combine( 0 ); }
    private void combine(int start ){
    	for( int i = start; i < inputstring.length(); ++i ){
            output.append( inputstring.charAt(i) );           
            this.stock.add(output.toString()); 
            if ( i <inputstring.length() )
            combine( i + 1);
            output.setLength( output.length() - 1 );
        }
    
    }
} 


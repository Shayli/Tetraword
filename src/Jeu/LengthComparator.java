package Jeu;

import java.util.*;

/**
 * Classe LengthComparator
 * @author Monia, Laury & André
 * @version 1 
 */
public class LengthComparator implements Comparator<String> {
    //nulls first, then by increasing length, break ties using String's natural order
    public int compare(String x, String y) {
        if (x == null)
            return y==null ? 0 : -1;
        else if (y == null)
            return +1;
        else {
            int lenx = x.length();
            int leny = y.length();
            if (lenx == leny)
                return x.compareTo(y); //break ties?
            else
                return lenx < leny ? -1 : +1;
        }
    }

    //demo
    public static void main(String[] args) {
        ArrayList <String> data = new ArrayList <String>(); 
        System.out.println(data);
        data.add("test"); data.add("blablabla"); data.add("a"); data.add("Monia"); data.add("loliiiiiiiiiii"); data.add("ii"); 
        System.out.println(data);
            
        Collections.sort(data, new LengthComparator());
        Collections.reverse(data);
        System.out.println(data);
    }
}
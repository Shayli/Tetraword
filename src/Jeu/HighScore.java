package Jeu;

import java.util.Comparator;

public class HighScore implements Comparator<HighScore>, Comparable<HighScore> {

	public String name;
	public int score;
	
	public HighScore(String name2, int score2) {
		name = name2;
		score = score2;
	}
	
	@Override
	public int compare(HighScore s, HighScore s2) {
		if(s.score  > s2.score) 
			return -1;
		
		return (s.score < s2.score) ? 1 : 0;
	}
	
	@Override
	public int compareTo(HighScore o) {
		return compare(this, o);
	}
}


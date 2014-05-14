package Modificateurs;

import Jeu.Constants;
import Jeu.Plateau;

public class DoubleScore extends Modificateur {

	public DoubleScore(Plateau p) {
		super(p, Constants.ModifDouble, 1000*10);
	}
	
	@Override
	public void onUpdate(float elapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		plateau.scoreModifier /= 2;
	}

	@Override
	public void onStart() {
		plateau.scoreModifier *= 2;		
	}

}

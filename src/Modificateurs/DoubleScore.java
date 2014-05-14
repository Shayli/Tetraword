package Modificateurs;

import Jeu.Constants;
import Jeu.Plateau;

public class DoubleScore extends Modificateur {

	public DoubleScore() {
		super(Type.DOUBLE, Constants.ModifDouble, 1000*10);
	}
	
	@Override
	public void onUpdate(float elapsed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStop() {
		System.out.println("double end");
		plateau.scoreModifier /= 2;
	}

	@Override
	public void onStart() {
		System.out.println("double start");
		plateau.scoreModifier *= 2;		
	}

}

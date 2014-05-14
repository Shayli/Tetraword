package Modificateurs;

import Jeu.Constants;

public class Slow extends Modificateur {

	public Slow() {
		super(Type.SLOW, Constants.ModifSlow, 1000*10);
	}
	
	@Override
	public void onStart() {
		
	}

	@Override
	public void onUpdate(float elapsed) {
		plateau.difficulte /= 2;
	}

	@Override
	public void onStop() {

	}

}

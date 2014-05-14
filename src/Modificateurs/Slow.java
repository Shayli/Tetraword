package Modificateurs;

import Jeu.Constants;

public class Slow extends Modificateur {

	public Slow() {
		super(Type.SLOW, Constants.ModifSlow, 1000*10);
	}
	
	@Override
	public void onStart() {
		plateau.difficulte /= 2;
	}

	@Override
	public void onUpdate(float elapsed) {

	}

	@Override
	public void onStop() {
		plateau.difficulte *= 2;
	}

}

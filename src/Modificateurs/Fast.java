package Modificateurs;

import Jeu.Constants;

public class Fast extends Modificateur {
	
	public Fast() {
		super(Type.FAST,Constants.ModifFast, 1000*10);
	}
	
	@Override
	public void onStart() {

	}

	@Override
	public void onUpdate(float elapsed) {
		plateau.difficulte *= 2;
	}

	@Override
	public void onStop() {
		plateau.difficulte /= 2;
	}

}

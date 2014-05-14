package Modificateurs;

import Jeu.Constants;

public class Fast extends Modificateur {
	
	public Fast() {
		super(Type.FAST,Constants.ModifFast, 1000*10);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		plateau.difficulte *= 2;
	}

	@Override
	public void onUpdate(float elapsed) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		plateau.difficulte /= 2;
	}

}

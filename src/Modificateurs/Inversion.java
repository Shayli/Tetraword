package Modificateurs;

import Jeu.Constants;

public class Inversion extends Modificateur {

	public Inversion() {
		super(Type.INVERT, Constants.ModifInv, 1000*10);
	}

	@Override
	public void onUpdate(float elapsed) {
		
	}
	
	public void onStop() {
		plateau.grille.setInverted(false);
	}

	@Override
	public void onStart() {
		plateau.grille.setInverted(true);
	}
}

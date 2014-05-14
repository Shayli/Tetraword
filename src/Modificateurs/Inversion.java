package Modificateurs;

import Jeu.Constants;
import Jeu.Plateau;

public class Inversion extends Modificateur {

	public Inversion(Plateau p) {
		super(p, Constants.ModifInv, 1000*10);
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

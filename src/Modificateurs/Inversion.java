package Modificateurs;

import Jeu.Constants;
import Jeu.Plateau;

public class Inversion extends Modificateur {

	public Inversion() {
		super(Type.INVERT, Constants.ModifInv, 1000*10);
	}

	@Override
	public void onUpdate(float elapsed) {
		
	}
	
	public void onStop() {
		System.out.println("invert end");
		plateau.grille.setInverted(false);
	}

	@Override
	public void onStart() {
		System.out.println("invert start");
		plateau.grille.setInverted(true);
	}
}

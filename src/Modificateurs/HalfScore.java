package Modificateurs;

import Jeu.Constants;

public class HalfScore extends Modificateur {

	public HalfScore() {
		super(Type.HALF, Constants.ModifHalf, 1000*10);
	}

	@Override
	public void onStart() {
		plateau.scoreModifier /= 2;
	}

	@Override
	public void onUpdate(float elapsed) {
	}

	@Override
	public void onStop() {
		plateau.scoreModifier *= 2;
	}

}

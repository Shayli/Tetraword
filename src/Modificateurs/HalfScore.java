package Modificateurs;

import Jeu.Constants;
import Jeu.Plateau;

public class HalfScore extends Modificateur {

	public HalfScore() {
		super(Type.HALF, Constants.ModifHalf, 1000*10);
	}

	@Override
	public void onStart() {
		System.out.println("half start");
		plateau.scoreModifier /= 2;
	}

	@Override
	public void onUpdate(float elapsed) {
	}

	@Override
	public void onStop() {
		System.out.println("half end");
		plateau.scoreModifier *= 2;
	}

}

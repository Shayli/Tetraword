package Modificateurs;

import java.awt.Graphics;
import java.awt.Image;

import Jeu.Plateau;

public abstract class Modificateur {
	protected Plateau plateau;
	private long timeLeft;
	private boolean done;
	private Image image;
	
	public Modificateur(Plateau p, Image img, long time) {
		plateau = p;
		timeLeft = time;
		done = false;
		image = img;
	}
	
	public void start() {
		onStart();
	}
	
	public void draw(Graphics g, int i) {
		//g.drawImage(image, 300+i*30, 300, null);
	}
	
	public void update(long elapsed) {
		timeLeft -= elapsed;
		if(timeLeft < 0)
			done = true;
		
		onUpdate(elapsed);
	}
	
	public boolean done() {
		return done;
	}
	
	abstract public void onStart();
	abstract public void onUpdate(float elapsed);
	abstract public void onStop();

	public void stop() {
		onStop();
	}
}

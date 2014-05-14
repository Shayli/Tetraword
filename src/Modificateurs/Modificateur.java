package Modificateurs;

import java.awt.Graphics;
import java.awt.Image;

import Jeu.Plateau;

public abstract class Modificateur {
	public enum Type {
		RANDOM,
		HALF,
		DOUBLE,
		INVERT,
		FAST,
		SLOW
	};
	
	public Plateau plateau;
	private long timeLeft;
	private long baseTime;
	private boolean done;
	private Image image;
	public int x,y;
	public Type type;
	
	public Modificateur(Type t, Image img, long time) {
		timeLeft = time;
		baseTime = time;
		done = false;
		image = img;
		x=0;
		y=0;
		type = t;
	}
	
	public void start() {
		onStart();
	}
	
	public void draw(Graphics g, int x, int y) {
		g.drawImage (image, x, y, null);
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

	public void refresh() {
		timeLeft += baseTime;
	}
}

package Patterns;

import java.util.ArrayList;

public class Observable {
	public ArrayList<Observer> observers = new ArrayList<Observer>();
	
	public void notify(String s, Object o)
	{
		for(Observer ob : observers)
			ob.notify(s,o);
	}
}

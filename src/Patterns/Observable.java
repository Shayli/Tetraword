package Patterns;

import java.util.ArrayList;

import sun.awt.Mutex;

public class Observable {
	protected ArrayList<Observer> observers = new ArrayList<Observer>();
	protected ArrayList<Observer> toRemove = new ArrayList<Observer>();
	protected Mutex mutex, mutex2;
	
	public Observable() {
		mutex = new Mutex();
		mutex2 = new Mutex();
	}
	
	public void notify(String s, Object o)
	{
		mutex.lock();
		mutex2.lock();
		for(Observer ob: toRemove)
			observers.remove(ob);
		toRemove.clear();
		mutex2.unlock();
		for(Observer ob : observers)
			ob.notify(s,o);
		mutex.unlock();
	}
	public void add(Observer o) {
		mutex.lock();
		observers.add(o);
		mutex.unlock();
	}
	
	public void remove(Observer o) {
		mutex2.lock();
		toRemove.add(o);
		mutex2.unlock();
	}
}

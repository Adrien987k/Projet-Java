package view;

import java.util.ArrayList;
import java.util.List;



public abstract class MyObservable {
	
	private ArrayList<Change> changes = new ArrayList<>();

	private ArrayList<MyObserver> observers = new ArrayList<>();	
	
	protected void addChange(Change c) {
	    changes.add(c);
	}
	
	protected void addAllChanges(List<Change> changes){
		this.changes.addAll(changes);
	}
	
	public void registerObserver(MyObserver observer) {
	    observers.add(observer);
	}
	
	public void unregisterObserver(MyObserver observer) {
		observers.remove(observer);
	}	

	public void notifyObserver() {
		List<Change> changes = getChanges();
	    for(MyObserver o : observers){
	    	o.update(changes);
	    }
	}
	
	private List<Change> getChanges() {
		List<Change> result = new ArrayList<>(changes);
		changes.clear();
		return result;
	}
}

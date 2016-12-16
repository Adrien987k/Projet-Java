package view;

import java.util.ArrayList;
import java.util.List;



public abstract class MyObservable {
	
	private ArrayList<AbsChange> changes = new ArrayList<>();

	private ArrayList<MyObserver> observers = new ArrayList<>();	
	
	protected void addChange(AbsChange c) {
	    changes.add(c);
	}
	
	protected void addAllChanges(List<? extends AbsChange> changes){
		this.changes.addAll(changes);
	}
	
	public void registerObserver(MyObserver observer) {
	    observers.add(observer);
	}
	
	public void unregisterObserver(MyObserver observer) {
		observers.remove(observer);
	}	

	public void notifyObserver() {
		List<AbsChange> changes = getChanges();
	    for(MyObserver o : observers){
	    	o.update(changes);
	    }
	}
	
	private List<AbsChange> getChanges() {
		List<AbsChange> result = new ArrayList<>(changes);
		changes.clear();
		return result;
	}
}

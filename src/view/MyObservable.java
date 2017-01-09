package view;

import java.util.ArrayList;
import java.util.List;


/**
 * This class is a different implementations of Observable.
 * It works with @see Change.
 * Change is a class wich contains all informations.
 * Observers read only fields they need to know and ignore the rest
 * @author Arnaud
 *
 */
public abstract class MyObservable {
	/**
	 * List of all changes to send to the observers.
	 */
	private ArrayList<Change> changes = new ArrayList<>();

	/**
	 * List of observers.
	 */
	private ArrayList<MyObserver> observers = new ArrayList<>();	
	
	/**
	 * Add a change to the list.
	 * @param c
	 */
	protected void addChange(Change c) {
	    changes.add(c);
	}
	
	/**
	 * Add a list of changes in the list.
	 * @param changes
	 */
	protected void addAllChanges(List<Change> changes){
		this.changes.addAll(changes);
	}
	
	/**
	 * Register the specified observer in the list.
	 * @param observer
	 */
	public void registerObserver(MyObserver observer) {
	    observers.add(observer);
	}
	
	/**
	 * Remove the specified observer from the list.
	 * @param observer
	 */
	public void unregisterObserver(MyObserver observer) {
		observers.remove(observer);
	}	

	/**
	 * Send changes to all observers.
	 */
	public void notifyObserver() {
		List<Change> changes = getChanges();
	    for(MyObserver o : observers){
	    	o.update(changes);
	    }
	}
	
	/**
	 * Get and delete all changes from the list.
	 * @return
	 */
	private List<Change> getChanges() {
		List<Change> result = new ArrayList<>(changes);
		changes.clear();
		return result;
	}
}

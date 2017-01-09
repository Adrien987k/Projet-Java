package view;

/**
 * Agent is an observable class used to ease the communication when an object is observed by several observer.
 * An Agent is add as a field in a observable object wich is already observed.
 * If this object needs to be observed by more than one observer, it must create one Agent per observer.
 * @author Arnaud
 *
 */
public class Agent extends MyObservable {
	
	/**
	 * Add a change to the Agent.
	 * This method is used because the range of addChange method in MyObservable is limited.
	 * @param change
	 */
	public void addChangeToAgent(Change change) {
		addChange(change);
	}
	
}

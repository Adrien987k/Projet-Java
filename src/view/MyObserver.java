package view;

import java.util.List;

/**
 * This class is a different implementations of Observer/
 * Please check the @see MyObservable documentation for more details.
 * @author Arnaud
 *
 */
public interface MyObserver {
	
	/**
	 * Get the changes from the observed object.
	 * @param changes
	 */
	public void update(List<Change> changes);
	
}

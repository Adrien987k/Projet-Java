package view;

import java.util.List;

/**
 * Renderer is an interface wich is implemented by class wich draw the gameMap on the interface.
 * @author Arnaud
 *
 */
public interface Renderer {
	
	public void render(List<Change> changes);
	
}

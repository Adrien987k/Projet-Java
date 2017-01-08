package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class contain all the data loaded in the file
 * 
 * @author Adrien
 *
 */
public class Grid {
	
	/**
	 * The grid of strings that represent the initial position of the blocks on the map
	 */
	private List<ArrayList<String>> data = new ArrayList<>();
	
	/**
	 * Different parameters of the level
	 */
	private Map<String, Integer> levelParameters = new HashMap<>();
	
	/**
	 * 
	 * @param data              The grid of strings that represent the initial position of the blocks on the map
	 * @param levelParameters   Different parameters of the level
	 */
	public Grid(List<ArrayList<String>> data, Map<String, Integer> levelParameters){
		this.data = data;
		this.levelParameters = levelParameters;
	}
	
	/*GETTERS AND SETTER*/
	
	public Map<String, Integer> getLevelParameters(){
		return levelParameters;
	}

	public List<ArrayList<String>> getData() {
		return data;
	}
	
	/*AND OF GETTERS AND SETTERS*/
	
}

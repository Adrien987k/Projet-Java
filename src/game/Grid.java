package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grid {
	
	private List<ArrayList<String>> data = new ArrayList<>();
	private Map<String, Integer> levelParameters = new HashMap<>();
	
	public Grid(List<ArrayList<String>> data, Map<String, Integer> levelParameters){
		this.data = data;
		this.levelParameters = levelParameters;
	}
	
	public Map<String, Integer> getLevelParameters(){
		return levelParameters;
	}

	public List<ArrayList<String>> getData() {
		return data;
	}
	
}

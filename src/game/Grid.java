package game;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	
	private List<ArrayList<String>> data = new ArrayList<>();
	private int nbLemmings;
	private int speed;
	
	public Grid(List<ArrayList<String>> data, int nbLemmings, int speed){
		this.data = data;
		this.nbLemmings = nbLemmings;
		this.speed = speed;
	}

	public List<ArrayList<String>> getData() {
		return data;
	}

	public int getNbLemmings() {
		return nbLemmings;
	}

	public int getSpeed() {
		return speed;
	}
	
	

}

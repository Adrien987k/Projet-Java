package game;

import java.util.ArrayList;
import java.util.List;

import component.Coordinate;
import factory.Factory;
import factory.IFactory;
import view.AdvancedView;
import view.AllView;
import view.Change;
import view.MyObservable;
import view.MyObserver;

/**
 * The game load the different levels and link all elements of the application between them
 * 
 * @author Adrien
 *
 */
public class Game extends MyObservable implements MyObserver {
	
	/**
	 * Pixel magnification setting
	 */
	private static final int SCALE = 50;
	
	/**
	 * The default speed the game should run with when it is not specify in the level file
	 */
	private static final int DEFAULT_SPEED = 1000;
	
	/**
	 * The number of level to play
	 */
	private static final int NB_LEVEL = 3;
	
	/**
	 * Instance of the loader
	 */
	private ILoader loader = new Loader();
	
	/**
	 * The graphical interface
	 */
	private AllView view;
	
	/**
	 * The game engine
	 */
	private GameMap gameMap;
	
	/**
	 * The factory that create all component on the map
	 */
	private IFactory factory = new Factory();
	
	/**
	 * The number of the current running level
	 */
	private int currentLevel = 1;
	
	/**
	 * Load the next level which is at the specify file path 
	 */
	private void loadLevel(String filePath){
		Grid grid = loader.loadFile(filePath);
		gameMap = new GameMap(factory, grid, this);
		gameMap.getCaseAgent().registerObserver(this);

		view = new AdvancedView(this, SCALE);
		gameMap.getDataAgent().registerObserver(getView().getInformationPanel());
		view.getGamePanel().drawBackground();
		gameMap.run(DEFAULT_SPEED);
		
		view.getFrame().dispose();
	}
	
	/**
	 * The current level will be the last one
	 */
	public void closeGame(){
		currentLevel = NB_LEVEL + 1;
	}
	
	/**
	 * Create the game and run it
	 */
	public Game() {
		while(currentLevel <= NB_LEVEL){		
			loadLevel("data\\level\\level" + currentLevel + ".txt");
			currentLevel++;
		}
	}
	
	/*GETTERS AND SETTER*/
	
	public int getWidth() {
		return gameMap.getGridWidth();
	}

	public int getHeight() {
		return gameMap.getGridHeight();
	}
	
	public AllView getView() {
		return view;
	}
	
	public GameMap getGameMap() {
		return gameMap;
	}
	
	/*END OF GETTERS AND SETTERS*/
	
	/**
	 * Send all the changes received from the engine to the graphical interface
	 */
	@Override
	public void update(List<Change> changes) {
		List<Coordinate> coordinates = new ArrayList<>();
		boolean alreadyExist = false;
		for(Change c : changes) {
			alreadyExist = false;
			Coordinate here = c.getCoordinate();
			for(Coordinate d : coordinates){
				if(c.equals(d)) alreadyExist = true;
			}
			if(!alreadyExist) 
				addChange(new Change(here, gameMap.priorityOrder(here)));
		}
		notifyObserver();
	}
	
}

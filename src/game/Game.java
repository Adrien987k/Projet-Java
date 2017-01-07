package game;

import java.util.ArrayList;
import java.util.List;

import component.Coordinate;
import factory.Factory;
import factory.IFactory;
import view.AbsChange;
import view.AbsMemoryChange;
import view.AdvancedView;
import view.AllView;
import view.ChangeGraphics;
import view.MyObservable;
import view.MyObserver;

public class Game extends MyObservable implements MyObserver {
	
	private static final int SCALE = 50;
	private static final int DEFAULT_SPEED = 25;
	private static final int NB_LEVEL = 3;
	
	private ILoader loader = new Loader();
	private AllView view;
	private GameMap gameMap;
	private IFactory factory = new Factory();
	private int actualLevel = 1;
	
	private void loadLevel(String filePath){
		Grid grid = loader.loadFile(filePath);
		gameMap = new GameMap(factory, grid, this);
		gameMap.getCaseAgent().registerObserver(this);
		
		view = new AdvancedView(this, SCALE);
		gameMap.getDataAgent().registerObserver(getView().getInformationPanel());
		gameMap.run(DEFAULT_SPEED);
		
		view.getFrame().dispose();
	}
	
	public void closeGame(){
		actualLevel = NB_LEVEL + 1;
	}
	
	public Game() {
		while(actualLevel <= NB_LEVEL){		
			loadLevel("data\\level\\level" + actualLevel + ".txt");
			actualLevel++;
		}
	}
	
	@Override
	public void update(List<? extends AbsChange> changes) {
		List<Coordinate> coordinates = new ArrayList<>();
		boolean alreadyExist = false;
		for(AbsChange c : changes) {
			alreadyExist = false;
			Coordinate here = ( (AbsMemoryChange) c).getCoordinate();
			for(Coordinate d : coordinates){
				if(c.equals(d)) alreadyExist = true;
			}
			if(!alreadyExist) 
				addChange(new ChangeGraphics(here, gameMap.priorityOrder(here)));
		}
		notifyObserver();
	}

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
	
}

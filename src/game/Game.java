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
	
	private ILoader loader;
	private AllView view;
	private GameMap gameMap;
	private IFactory factory;
	
	private static final int SCALE = 50;
	private static final int DEFAULT_SPEED = 25;
	
	public Game() {
		factory = new Factory();
		loader = new Loader();
		Grid grid = loader.loadFile("data\\level\\test.txt");
		gameMap = new GameMap(factory, grid);
		gameMap.getCaseAgent().registerObserver(this);
		
		view = new AdvancedView(50, 50, this, SCALE);
		gameMap.getDataAgent().registerObserver(getView().getInformationPanel());
	}
	
	public void run() {
		gameMap.run(DEFAULT_SPEED);
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

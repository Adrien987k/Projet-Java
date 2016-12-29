package game;

import java.util.ArrayList;
import java.util.List;

import component.Coordinate;
import factory.Factory;
import factory.IFactory;
import view.AbsChange;
import view.BasicView;
import view.ChangeGraphics;
import view.MyObservable;
import view.MyObserver;
import view.View;

public class Game extends MyObservable implements MyObserver {
	
	private ILoader loader;
	private View view;
	private GameMap gameMap;
	private IFactory factory;
	
	private static final int SCALE = 100;
	private static final int DEFAULT_SPEED = 25;
	
	public Game() {
		factory = new Factory();
		loader = new Loader(factory);
		gameMap = loader.loadFile("test.txt");
		gameMap.registerObserver(this);
		view = new BasicView(100, 100, this, SCALE);
	}
	
	public void run() {
		gameMap.run(DEFAULT_SPEED);
	}
	
	@Override
	public void update(List<? extends AbsChange> changes) {
		List<Coordinate> coordinates = new ArrayList<>();
		boolean alreadyExist = false;
		for(AbsChange c : changes) {
			Coordinate here = c.getCoordinate();
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
	
}

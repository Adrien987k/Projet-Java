package game;

import java.util.List;

import view.AbsChange;
import view.BasicView;
import view.ChangeGraphics;
import view.MyObservable;
import view.MyObserver;
import view.View;

import component.Coordinate;

import factory.Factory;
import factory.IFactory;

public class Game extends MyObservable implements MyObserver {
	
	private ILoader loader;
	private View view;
	private GameMap gameMap;
	private IFactory factory;
	
	private static final int SCALE = 100;
	private static final int DEFAULT_SPEED = 25;
	/*private static final int DEFAULT_WIDTH = 10;
	private static final int DEFAULT_HEIGTH = 10;*/
	
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
		for(AbsChange c: changes) {
			Coordinate here = c.getCoordinate();
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

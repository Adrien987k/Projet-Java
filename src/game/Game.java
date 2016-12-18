package game;

import java.util.List;

import component.Coordinate;
import factory.Factory;
import factory.IFactory;
import view.AbsChange;
import view.BasicView;
import view.ChangeGraphics;
import view.Type;
import view.MyObservable;
import view.MyObserver;
import view.Renderer;

public class Game extends MyObservable implements MyObserver {
	
	private ILoader loader;
	private Renderer renderer;
	private GameMap gameMap;
	private IFactory factory;
	
	private static final int SCALE = 5;
	private static final int DEFAULT_SPEED = 25;
	private static final int WIDTH = 100;
	private static final int HEIGTH = 100;
	
	public Game() {
		factory = new Factory();
		loader = new Loader(factory);
		gameMap = loader.loadFile("level 1");
		renderer = new BasicView(0,0,this,SCALE);
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
		return WIDTH;
	}

	public int getHeight() {
		return HEIGTH;
	}
	
}

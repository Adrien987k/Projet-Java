package game;

import java.util.List;

import component.Coordinate;
import view.AbsChange;
import view.ChangeGraphics;
import view.ChangeType;
import view.MyObservable;
import view.MyObserver;

public class Game extends MyObservable implements MyObserver {
	
	private Loader loader;
	//private Renderer renderer;
	private GameMap gameMap;
	
	public Game() {
		
	}
	
	@Override
	public void update(List<? extends AbsChange> changes) {
		for(AbsChange c: changes) {
			Coordinate here = c.getNext();
			addChange(new ChangeGraphics(here,gameMap.priorityOrder(here)));
		}
		notifyObserver();
	}
	
}

package game;

import java.util.List;

import view.AbsChange;
import view.MyObservable;
import view.MyObserver;

import component.Coordinate;

public class Game extends MyObservable implements MyObserver {
	
	private Loader loader;
	//private Renderer renderer;
	private GameMap gameMap;
	
	@Override
	public void update(List<? extends AbsChange> changes) {
		for(AbsChange c: changes) {
			Coordinate here = c.getNext();
			
			
		}
	}
	
}

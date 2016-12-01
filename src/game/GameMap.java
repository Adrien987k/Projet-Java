package game;

import java.util.ArrayList;
import java.util.List;

import view.AbsChange;
import view.ChangeCase;
import view.ChangeMemory;
import view.MyObservable;
import view.MyObserver;

import component.Component;
import component.Coordinate;

public class GameMap extends MyObservable implements MyObserver {
	
	private ArrayList<Component>[][] gridComponents;
	
	public GameMap(){
		
		
		
	}
	
	public void step() {
		//code pour appeler step sur tous les lemmings
		
		notifyObserver();
	}

	@Override
	public void update(List<? extends AbsChange> changes) {
		for(AbsChange c : changes){
			Coordinate last = ((ChangeMemory) c).getComponent().getCoordinate();
			Coordinate next = c.getNext();
			
			Component component = ((ChangeMemory) c).getComponent();
			Component componentNext = ((ChangeMemory) c).getComponentNext();
			
			List<Component> lastArea = getArea(last);
			lastArea.remove(component);
			
			List<Component> nextArea = getArea(next);
			nextArea.add(componentNext);
			componentNext.setCoordinate(next);
			
			this.addChange(new ChangeCase(next));
			if(!next.equals(last))
				this.addChange(new ChangeCase(last));
		}
		
	}
	

	public List<Component> getArea(Coordinate c){
		return gridComponents[c.getX()][c.getY()];
	}
	
	
	
}

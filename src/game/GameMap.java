package game;

import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.List;

import component.Component;
import component.Coordinate;
import view.AbsChange;
import view.ChangeCase;
import view.ChangeMemory;
import view.Type;
import view.MyObservable;
import view.MyObserver;

public class GameMap extends MyObservable implements MyObserver {
	
	private ArrayList<Component>[][] gridComponents;
	
	public GameMap(){
		
		
		
	}
	
	public void run(int speed) {
		init();
		
		//TODO condition d'arrêt
		while(true) {
			step();
			try {
				Thread.sleep((long) speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void step() {
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				for(Component c:gridComponents[i][j])
					c.step();
			}
		}
		notifyObserver();
	}
	
	private void init() {
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				this.addChange(new ChangeCase(new Coordinate(i,j)));
			}
		}
		
		notifyObserver();
	}

	@Override
	public void update(List<? extends AbsChange> changes) {
		for(AbsChange c : changes){
			Coordinate last = ((ChangeMemory) c).getComponent().getCoordinate();
			Coordinate next = c.getCoordinate();
			
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
	
	private List<Component> getArea(Coordinate c){
		return gridComponents[c.getX()][c.getY()];
	}
	
	public List<Type> priorityOrder(Coordinate c) {
		List<Type> list = new ArrayList<>();
		getArea(c).sort((e1,e2) -> e1.getPriority() - e2.getPriority());
		for(Component component: getArea(c)) {
			list.add(component.getType());
		}
		return list;
	}
	
	
	
}

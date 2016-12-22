package game;

import java.util.ArrayList;
import java.util.List;

import view.AbsChange;
import view.ChangeCase;
import view.ChangeMemory;
import view.MyObservable;
import view.MyObserver;
import view.Type;
import component.Component;
import component.Coordinate;
import factory.IFactory;

public class GameMap extends MyObservable implements MyObserver {
	
	private IFactory factory;
	private ArrayList<Component>[][] gridComponents;
	private int speed;
	private int nbLemmings;
	private boolean running = false;
	
	private int nbFreeLemming = 0;
	private int nbDeadLemming = 0;
	
	public GameMap(IFactory factory, Grid grid){
		this.factory = factory;
		gridComponents = processGrid(grid.getData());
		nbLemmings = grid.getNbLemmings();
		speed = grid.getSpeed();
	}
	
	private ArrayList<Component>[][] processGrid(List<ArrayList<String>> data){
		@SuppressWarnings("unchecked")
		ArrayList<Component>[][] result = (ArrayList<Component>[][]) new ArrayList[data.size()][data.get(0).size()];
		ArrayList<Component> temp;
		
		for(int i = 0; i < result.length; i++){
			for(int j = 0; j < result[0].length; j++){
				temp = new ArrayList<>();
				temp.add(factory.make(convertStringToType(data.get(i).get(j)), new Coordinate(i, j), this));
				result[i][j] = temp;
			}
		}
		return result;
	}
	
	private Type convertStringToType(String string){
		for(Type t : Type.values()){
			if(t.getFileEncoding().equals(string)) return t;
		}
		throw new IllegalArgumentException("Encoding non reconnu");
	}
	
	public void run(int defaultSpeed) {
		init();
		if(speed <= 0) speed = defaultSpeed;
		running = true;
		while(running) {
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
				for(Component c : gridComponents[i][j])
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
	
	public void addChangeMemory(ChangeMemory changeMemory){
		addChange(changeMemory);
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
	
	public IFactory getFactory(){
		return factory;
	}
	
	public void incNbFreeLemming(){
		nbFreeLemming++;
	}
	
	public void incNbDeadLemming(){
		nbDeadLemming++;
	}
	
	public int getGridHeight(){
		return gridComponents.length;
	}
	
	public int getGridWidth(){
		return gridComponents[0].length;
	}
	
	public List<Component> getArea(Coordinate coordinate){
		if(isOut(coordinate)) return new ArrayList<Component>();
		return gridComponents[coordinate.getX()][coordinate.getY()];
	}
	
	public boolean isOut(Coordinate coordinate){
		return (coordinate.getX() < 0) 
		|| (coordinate.getX() > getGridHeight())
		|| (coordinate.getY() < 0) 
		|| (coordinate.getY() > getGridWidth());
	}
	
	public List<Type> priorityOrder(Coordinate coordinate) {
		List<Type> list = new ArrayList<>();
		getArea(coordinate).sort((e1,e2) -> e1.getPriority() - e2.getPriority());
		for(Component component: getArea(coordinate)) {
			list.add(component.getType());
		}
		return list;
	}
	
	
	
}

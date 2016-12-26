package game;

import java.util.ArrayList;
import java.util.List;

import lemming.Lemming;
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
	
	public static final int LEMMING_GENERATION_RATE = 10;
	
	private IFactory factory;
	private ArrayList<Component>[][] gridComponents;
	private int speed;
	private int nbLemmings;
	private List<Coordinate> starts = new ArrayList<>();
	private boolean running = false;
	private int cursorStart = 0;
	private int cursorGeneration = 0;
	
	//TODO Communiquer ces donn�e pour l'affichage
	// Utiliser une structure data a communiquer ???
	private int nbRemainingLemming;
	private int nbFreeLemming = 0;
	private int nbDeadLemming = 0;
	
	public GameMap(IFactory factory, Grid grid){
		this.factory = factory;
		gridComponents = processGrid(grid.getData());
		nbLemmings = grid.getNbLemmings();
		nbRemainingLemming = nbLemmings;
		speed = grid.getSpeed();
		registerObserver(this);
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
		cursorGeneration++;
		if(cursorGeneration == LEMMING_GENERATION_RATE && nbRemainingLemming > 0){
			generateLemming();
			cursorGeneration = 0;
		}
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				for(Component c : gridComponents[i][j]){
					c.step();
				}
			}
		}
		notifyObserver();
	}
	
	private void init() {
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				this.addChange(new ChangeCase(new Coordinate(i, j)));
				if(gridComponents[i][j].get(0).getType() == Type.START){
					starts.add(new Coordinate(i, j));
				}
			}
		}
		notifyObserver();
	}
	
	private void generateLemming(){
		add(starts.get(cursorStart), factory.make(Type.LEMMING, starts.get(cursorStart), this));
		cursorStart++;
		if(cursorStart == starts.size()) cursorStart = 0;
		nbRemainingLemming--;
	}
	
	/* Ajoute un component � la coordionn�e donn�e*/
	public void add(Coordinate coordinate, Component component){
		addChange(new ChangeMemory(coordinate, null, component));
	}
	
	/* Retire le component */
	public void remove(Component component){
		addChange(new ChangeMemory(component.getCoordinate(), component, null));
	}
	
	/* D�place un component � la coordonn�e donn�e */
	public void move(Coordinate coordinate, Component component){
		addChange(new ChangeMemory(coordinate, component, component));
	}
	
	/* Transforme un component en un autre */
	public void change(Component component, Component componentNext){
		addChange(new ChangeMemory(component.getCoordinate(), component, componentNext));
	}

	@Override
	public void update(List<? extends AbsChange> changes) {
		ArrayList<ChangeMemory> list = new ArrayList<>();
		for(AbsChange c : changes){
			if(c instanceof ChangeMemory) list.add((ChangeMemory)c);
		}
		@SuppressWarnings("unchecked")
		ArrayList<ChangeMemory> Mchanges = (ArrayList<ChangeMemory>) changes;
		for(ChangeMemory c : list){
			if(c.getComponent() != null){
				Coordinate last = c.getComponent().getCoordinate();
				Component component = c.getComponent();
				List<Component> lastArea = getArea(last);
				lastArea.remove(component);
				this.addChange(new ChangeCase(last));
			}
			if(c.getComponentNext() != null){
				Coordinate next = c.getCoordinate();
				Component componentNext = c.getComponentNext();
				List<Component> nextArea = getArea(next);
				nextArea.add(componentNext);
				componentNext.setCoordinate(next);
				this.addChange(new ChangeCase(next));				
			}
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

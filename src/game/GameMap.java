package game;

import java.util.ArrayList;
import java.util.List;

import States.State;
import block.TP;
import component.Component;
import component.Coordinate;
import factory.IFactory;
import view.AbsChange;
import view.Agent;
import view.ChangeCase;
import view.ChangeData;
import view.ChangeMemory;
import view.ChangeStateHere;
import view.MyObservable;
import view.MyObserver;
import view.Type;

public class GameMap extends MyObservable implements MyObserver {
	
	public static final int LEMMING_GENERATION_RATE = 10;
	public static final int NOTIFY_RATE = 1;
	
	private boolean isFreeze = false;		
	private IFactory factory;
	private ArrayList<Component>[][] gridComponents;
	private int currentSpeed;
	private int speed;
	private int nbLemmings;
	private List<Coordinate> starts = new ArrayList<>();
	private boolean running = false;
	private int cursorStart = 0;
	private int cursorGeneration = 0;
	private Agent caseAgent = new Agent();
	private Agent dataAgent = new Agent();
	private Agent mouseAgent = new Agent() {
		@Override
		public void update(List<? extends AbsChange> changes) {
			for(AbsChange c: changes) {
				changeStateHere(((ChangeStateHere) c).getCoordinate(), ((ChangeStateHere) c).getState());
			}
		}
	};
	private Agent timeAgent = new Agent() {
		@Override
		public void update(List<? extends AbsChange> changes) {
			pause();
		}
	};
	private Agent genocideAgent = new Agent() {
		@Override
		public void update(List<? extends AbsChange> changes) {
			killAllLemmings();
		}
	};
	private Agent addLemmingAgent = new Agent() {       
		@Override
		public void update(List<? extends AbsChange> changes) {
			if(getNbRemainingLemming() > 0)
					generateLemming();
		}
	};
	
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
		ArrayList<Component>[][] result = new ArrayList[data.size()][data.get(0).size()];
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
	
	public void pause() {
		setIsFreeze(!getIsFreeze());
	}
	
	public boolean getIsFreeze() {
		return isFreeze;
	}
	public void setIsFreeze(boolean isFreeze) {
		this.isFreeze = isFreeze;
	}
	
	public void run(int defaultSpeed) {
		init();
		if(speed <= 0) speed = defaultSpeed;
		currentSpeed = speed;
		running = true;
		while(running) {
			step();
			try {
				while(getIsFreeze()) {
					Thread.sleep(currentSpeed);
				}
				Thread.sleep(currentSpeed);
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
		getDataAgent().addChangeToAgent(createDataChange());
		for(int i = 0; i < NOTIFY_RATE; i++){			
			notifyEveryone();
		}
	}
	
	private void init() {
		TP teleport1 = null;
		TP teleport2 = null;
		boolean cursorTp = false;
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				getCaseAgent().addChangeToAgent(new ChangeCase(new Coordinate(i, j)));
				//Collect coordinates of start
				if(gridComponents[i][j].get(0).getType() == Type.START){
					starts.add(new Coordinate(i, j));
				}
				//Link teleporters between them
				if(gridComponents[i][j].get(0).getType() == Type.TP){
					if(!cursorTp) {
						teleport1 = (TP) gridComponents[i][j].get(0);
						cursorTp = true;
					} else {
						teleport2 = (TP) gridComponents[i][j].get(0);
						teleport1.setDestination(teleport2);
						teleport2.setDestination(teleport1);
						cursorTp = false;
					}
				}
			}
		}
		getDataAgent().addChangeToAgent(createDataChange());
		notifyEveryone();
	}
	
	private void generateLemming(){
		add(starts.get(cursorStart), factory.make(Type.LEMMING, starts.get(cursorStart), this));
		cursorStart++;
		if(cursorStart == starts.size()) cursorStart = 0;
		nbRemainingLemming--;
	}
	
	/* Ajoute un component à la coordionnée donnée*/
	public void add(Coordinate coordinate, Component component){
		addChange(new ChangeMemory(coordinate, null, component));
	}
	
	/* Retire le component */
	public void remove(Component component){
		addChange(new ChangeMemory(component.getCoordinate(), component, null));
	}
	
	/* Déplace un component à la coordonnée donnée */
	public void move(Coordinate coordinate, Component component){
		addChange(new ChangeMemory(coordinate, component, component));
	}
	
	/* Transforme un component en un autre */
	public void change(Component component, Component componentNext){
		addChange(new ChangeMemory(component.getCoordinate(), component, componentNext));
	}

	@Override
	public void update(List<? extends AbsChange> changes) {
		@SuppressWarnings("unchecked")
		ArrayList<ChangeMemory> Mchanges = (ArrayList<ChangeMemory>) changes;
		for(ChangeMemory c : Mchanges){
			if(c.getComponent() != null){
				Coordinate last = c.getComponent().getCoordinate();
				Component component = c.getComponent();
				List<Component> lastArea = getArea(last);
				lastArea.remove(component);
				getCaseAgent().addChangeToAgent(new ChangeCase(last));
			}
			if(c.getComponentNext() != null){
				Coordinate next = c.getCoordinate();
				Component componentNext = c.getComponentNext();
				List<Component> nextArea = getArea(next);
				nextArea.add(componentNext);
				componentNext.setCoordinate(next);
				getCaseAgent().addChangeToAgent(new ChangeCase(next));				
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
		if(isOut(coordinate)) return new ArrayList<>();
		return gridComponents[coordinate.getX()][coordinate.getY()];
	}
	
	public boolean isOut(Coordinate coordinate){
		return (coordinate.getX() < 0)
		|| (coordinate.getX() >= getGridHeight())
		|| (coordinate.getY() < 0) 
		|| (coordinate.getY() >= getGridWidth());
	}
	
	public List<Type> priorityOrder(Coordinate coordinate) {
		List<Type> list = new ArrayList<>();
		getArea(coordinate).sort((e1,e2) -> e1.getPriority() - e2.getPriority());
		for(Component component: getArea(coordinate)) {
			list.add(component.getType());
		}
		return list;
	}
	
	public void notifyEveryone() {
		notifyObserver();
		notifyAgents();
	}
	
	public void notifyAgents() {
		getCaseAgent().notifyObserver();
		getDataAgent().notifyObserver();
	}
	
	public Agent getCaseAgent() {
		return caseAgent;
	}
	public Agent getDataAgent() {
		return dataAgent;
	}
	public Agent getMouseAgent() {
		return mouseAgent;
	}
	public Agent getTimeAgent() {
		return timeAgent;
	}
	public Agent getAddLemmingAgent() {
		return addLemmingAgent;
	}
	public Agent getGenocideAgent() {
		return genocideAgent;
	}
	
	public AbsChange createDataChange() {
		return new ChangeData(getNbDeadLemming(),getNbFreeLemming(),getNbRemainingLemming());
	}

	public int getNbRemainingLemming() {
		return nbRemainingLemming;
	}

	public int getNbFreeLemming() {
		return nbFreeLemming;
	}

	public int getNbDeadLemming() {
		return nbDeadLemming;
	}
	
	private void killAllLemmings() {
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				for(Component c : gridComponents[i][j]){
					c.kill();
				}
			}
		}
	}
	
	private boolean changeStateHere(Coordinate c,State state) {
		List<Component> area = getArea(c);
		for(Component component: area) {
			if(component.changeStateIf(state))
				return true;
		}
		return false;
	}

	
	
	
}

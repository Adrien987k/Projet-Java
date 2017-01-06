package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import view.MyObservable;
import view.MyObserver;
import view.Type;

public class GameMap extends MyObservable implements MyObserver {
	
	public static final int LEMMING_GENERATION_RATE = 10;
	public static final int NOTIFY_RATE = 5;
	
	private boolean isFreeze = false;		
	private IFactory factory;
	private ArrayList<Component>[][] gridComponents;
	private int speed;
	private int currentSpeed;
	private Map<String, Integer> levelParameters;
	
	private List<Coordinate> starts = new ArrayList<>();
	private boolean running = true;
	private int cursorStart = 0;
	private int cursorGeneration = 0;
	
	private Agent caseAgent = new Agent();
	private Agent dataAgent = new Agent();
	
	private int nbRemainingLemming;
	private int nbFreeLemming = 0;
	private int nbDeadLemming = 0;
	
	public GameMap(IFactory factory, Grid grid){
		this.factory = factory;
		gridComponents = processGrid(grid.getData());
		levelParameters = grid.getLevelParameters();
		nbRemainingLemming = getNbLemmings();
		speed = levelParameters.get("speed");
		registerObserver(this);
	}
	
	public int getLevelParameterByToken(String fileToken){
		return levelParameters.get(fileToken);
	}
	
	public int getNbLemmings(){
		return levelParameters.get("nbLemmings");
	}

	public int getObjective(){
		return levelParameters.get("objective");
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
		while(running) {
			step();
			updateRunning();
			try {
				while(getIsFreeze()) {
					Thread.sleep(currentSpeed);
				}
				Thread.sleep(currentSpeed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		getDataAgent().addChangeToAgent(createDataChange());
		getDataAgent().notifyObserver();
	}
	
	private void updateRunning(){
		if(nbFreeLemming + nbDeadLemming == getNbLemmings()) running = false;
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
		
		//On trie les changements en mettant les suppresions en premier.
		Mchanges.sort((c1, c2) -> {
			if(c1.getComponentNext() == null && c2.getComponentNext() != null) return 1;
			else return -1;
					});
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
		notifyAgents();
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
		getArea(coordinate).sort((e1,e2) -> e1.getPriority().getValue() - e2.getPriority().getValue());
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

	public AbsChange createDataChange() {
		return new ChangeData(getNbDeadLemming(),getNbFreeLemming(),getNbRemainingLemming(),getRunning(),getLevelParameters());
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
	
	public boolean getRunning() {
		return running;
	}
	
	public Map<String,Integer> getLevelParameters() {
		return levelParameters;
	}
	public void addLemming() {
		if(getNbRemainingLemming() > 0)
			generateLemming();
	}
		
	
	public void killAllLemmings() {
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				for(Component component : gridComponents[i][j]){
					component.kill();
				}
			}
		}
	}
	
	public boolean changeStateHere(Coordinate c, State state) {
		List<Component> area = getArea(c);
		for(Component component: area) {
			if(component.changeStateIf(state)){
				return true;
			}
		}
		return false;
	}
	
}

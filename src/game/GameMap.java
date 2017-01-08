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

/**
 * The gameMap is the engine of the game.
 * It contains the map of all the components
 * 
 * @author Adrien
 *
 */
public class GameMap extends MyObservable implements MyObserver {
	
	/**
	 * The number of step to wait before generate an other lemming
	 */
	public static final int LEMMING_GENERATION_RATE = 10;
	
	/**
	 * The number of time by step it send data to the view
	 */
	public static final int NOTIFY_RATE = 5;
	
	/**
	 * The game it belong to
	 */
	private Game game;
	
	/**
	 * Indicate if the game is paused
	 */
	private boolean isFreeze = false;	
	
	/**
	 * The factory use to create component of the map
	 */
	private IFactory factory;
	
	/**
	 * A grid containing all the components 
	 * For each coordinate, there is a list of all the components at this coordinate 
	 */
	private ArrayList<Component>[][] gridComponents;
	
	/**
	 * The initial speed
	 */
	private int speed;
	
	/**
	 * The current speed
	 */
	private int currentSpeed;
	
	/**
	 * All the initial parameters of the level by name
	 */
	private Map<String, Integer> levelParameters;
	
	/**
	 * All the coordinate of start
	 */
	private List<Coordinate> starts = new ArrayList<>();
	
	/**
	 * Indicate if the game is running or not
	 */
	private boolean running = true;
	
	/**
	 * Indicate the ID of the coordinate of start in the starts list for the next lemming
	 */
	private int cursorStart = 0;
	
	/**
	 * A counter of step for lemming generation
	 */
	private int cursorGeneration = 0;
	
	/**
	 * Send notifications on where a change occurred on the map to the view
	 */
	private Agent caseAgent = new Agent();
	
	/**
	 * Send data to the view
	 */
	private Agent dataAgent = new Agent();
	
	/**
	 * Number of remaining lemming
	 */
	private int nbRemainingLemming;
	
	/**
	 * Number of free lemming
	 */
	private int nbFreeLemming = 0;
	
	/**
	 * Number of dead lemmmig
	 */
	private int nbDeadLemming = 0;
	
	/**
	 * 
	 * @param factory   The factory use to create component on the map
	 * @param grid      The grid loaded in the file 
	 * @param game      The game it belong to
	 */
	public GameMap(IFactory factory, Grid grid, Game game){
		this.factory = factory;
		this.game = game;
		gridComponents = processGrid(grid.getData());
		levelParameters = grid.getLevelParameters();
		nbRemainingLemming = getNbLemmings();
		speed = levelParameters.get("speed");
		registerObserver(this);
	}
	
	/*GETTERS AND SETTERS*/
	
	public Game getGame(){
		return game;
	}
	
	public boolean getIsFreeze() {
		return isFreeze;
	}
	
	public void setIsFreeze(boolean isFreeze) {
		this.isFreeze = isFreeze;
	}
	
	public IFactory getFactory(){
		return factory;
	}
	
	public int getGridHeight(){
		return gridComponents.length;
	}
	
	public int getGridWidth(){
		return gridComponents[0].length;
	}
	
	public Map<String,Integer> getLevelParameters() {
		return levelParameters;
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
	
	public boolean getRunning() {
		return running;
	}
	
	public Agent getCaseAgent() {
		return caseAgent;
	}
	public Agent getDataAgent() {
		return dataAgent;
	}
	
	public int getNbRemainingLemming() {
		return nbRemainingLemming;
	}
	
	public int getNbFreeLemming() {
		return nbFreeLemming;
	}
	
	public void incNbFreeLemming(){
		nbFreeLemming++;
	}
	
	public int getNbDeadLemming() {
		return nbDeadLemming;
	}
	
	public void incNbDeadLemming(){
		nbDeadLemming++;
	}
	
	/*AND OF GETTERS AND SETTER*/
	
	/**
	 * Take the gird data loaded in the file and transform it into a grid of component
	 */
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
	
	/**
	 * Take the string encoding loaded in the file and return the corresponding Type
	 */
	private Type convertStringToType(String string){
		for(Type t : Type.values()){
			if(t.getFileEncoding().equals(string)) return t;
		}
		throw new IllegalArgumentException("Encoding non reconnu");
	}
	
	/**
	 * Pause the game. If the game was already paused launch it
	 */
	public void pause() {
		setIsFreeze(!getIsFreeze());
	}
	
	/**
	 * Run the current level
	 */
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
	
	/**
	 * Stop the game if there is no remaining lemmings on the map
	 */
	private void updateRunning(){
		if(nbFreeLemming + nbDeadLemming == getNbLemmings()) running = false;
	}
	
	/**
	 * Initialize the current level before starting it
	 */
	private void init() {
		TP teleport1 = null;
		TP teleport2 = null;
		boolean cursorTp = false;
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				getCaseAgent().addChangeToAgent(new ChangeCase(new Coordinate(i, j)));
				/*Collect coordinates of start*/
				if(gridComponents[i][j].get(0).getType() == Type.START){
					starts.add(new Coordinate(i, j));
				}
				/*Link teleporters between them*/
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
	
	/**
	 * Compute the next state of the game and then send information to the graphical interface
	 */
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
	
	/**
	 * Add instantly a lemming on the map
	 */
	public void addLemming() {
		if(getNbRemainingLemming() > 0)
			generateLemming();
	}
	
	/**
	 * Generate a new lemming on the map
	 */
	private void generateLemming(){
		add(starts.get(cursorStart), factory.make(Type.LEMMING, starts.get(cursorStart), this));
		cursorStart++;
		if(cursorStart == starts.size()) cursorStart = 0;
		nbRemainingLemming--;
	}
	
	/**
	 * Add a new component at the specified coordinate to the map
	 */
	public void add(Coordinate coordinate, Component component){
		addChange(new ChangeMemory(coordinate, null, component));
	}
	
	/**
	 * Remove the specified component of the map
	 */
	public void remove(Component component){
		addChange(new ChangeMemory(component.getCoordinate(), component, null));
	}
	
	/**
	 * Move the specified component to an other coordinate
	 */
	public void move(Coordinate coordinate, Component component){
		addChange(new ChangeMemory(coordinate, component, component));
	}
	
	/**
	 * Transform the specified component to an other
	 */
	public void change(Component component, Component componentNext){
		addChange(new ChangeMemory(component.getCoordinate(), component, componentNext));
	}

	/**
	 * Execute all the changes added with methods add / remove / move / change
	 */
	@Override
	public void update(List<? extends AbsChange> changes) {
		@SuppressWarnings("unchecked")
		ArrayList<ChangeMemory> Mchanges = (ArrayList<ChangeMemory>) changes;
		/*Sort all the changes putting the suppressions first*/
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
	
	/**
	 * Return the list of component existing at the specified coordinate
	 */
	public List<Component> getArea(Coordinate coordinate){
		if(isOut(coordinate)) return new ArrayList<>();
		return gridComponents[coordinate.getX()][coordinate.getY()];
	}
	
	/**
	 * Indicate if the specified coordinate is out of the map 
	 */
	public boolean isOut(Coordinate coordinate){
		return (coordinate.getX() < 0)
		|| (coordinate.getX() >= getGridHeight())
		|| (coordinate.getY() < 0) 
		|| (coordinate.getY() >= getGridWidth());
	}
	
	/**
	 * Return the list of Type sort by priority existing at the specidied coordinate 
	 */
	public List<Type> priorityOrder(Coordinate coordinate) {
		List<Type> list = new ArrayList<>();
		getArea(coordinate).sort((e1,e2) -> e1.getPriority().getValue() - e2.getPriority().getValue());
		for(Component component: getArea(coordinate)) {
			list.add(component.getType());
		}
		return list;
	}
	
	/**
	 * Notify all the changes to all the observers
	 */
	public void notifyEveryone() {
		notifyObserver();
		notifyAgents();
	}
	
	/**
	 * Notify only the agents
	 */
	public void notifyAgents() {
		getCaseAgent().notifyObserver();
		getDataAgent().notifyObserver();
	}

	/**
	 * Create and return an object containing current data of the game
	 */
	public AbsChange createDataChange() {
		return new ChangeData(getNbDeadLemming(),getNbFreeLemming(),getNbRemainingLemming(),getRunning(),getLevelParameters());
	}

	/**
	 * Kill all lemming of the map
	 */
	public void killAllLemmings() {
		for(int i = 0; i < gridComponents.length; i++) {
			for(int j = 0; j < gridComponents[0].length; j++) {
				for(Component component : gridComponents[i][j]){
					component.kill();
				}
			}
		}
	}
	
	/**
	 * Try to apply a state change to the specified coordinate
	 * The change will be apply to the first lemming encounter at this coordinate
	 * 
	 * @param coordinate   The coordinate where it has to change
	 * @param state        The state to apply 
	 * @return             Return true if a change has been applied
	 */
	public boolean changeStateHere(Coordinate coordinate, State state) {
		List<Component> area = getArea(coordinate);
		for(Component component: area) {
			if(component.changeStateIf(state)){
				return true;
			}
		}
		return false;
	}
	
}

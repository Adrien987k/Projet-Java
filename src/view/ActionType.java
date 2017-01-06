package view;

import java.awt.Color;

import States.State;

/*
 * Concerne les boutons dans la barre d'actions.
 * Permet de connaitre le type et les effets de l'action.
 * Utile notamment pour stocker la dernière action séléctionnée.
 */
public enum ActionType {
	NONE("None","<html> No action selected"),
	SET_BOMBER("Set Bomber",
			"<html> The selected lemming walks 3 steps before exploding, it will destroy everything destructible within 2 cases.</html> ", 
			new Color(130,130,180),
			State.BOMBER,
			"data\\img\\defaultTexturePack\\actionBarIcon\\bomber.jpg"),
	SET_BLOCKER("Set Blocker",
			"<html> The selected lemming stops, then each lemming trying to cross him will do a half turn.",
			new Color(70,130,180),
			State.BLOCKER,
			"data\\img\\defaultTexturePack\\actionBarIcon\\blocker.jpg"),
	SET_CARPENTER("Set Carpenter",
			"<html> The selected lemming builds a stair one case up, during 5 steps.",
			new Color(244,164,96),
			State.CARPENTER,
			"data\\img\\defaultTexturePack\\actionBarIcon\\carpenter.jpg"),
	SET_CLIMBER("Set Climber",
			"<html> The selected lemming climbs any vertical obstacle until the top, no matter the height.",
			new Color(124,252,0),
			State.CLIMBER,
			"data\\img\\defaultTexturePack\\actionBarIcon\\climber.jpg"),
	SET_DIGGER("Set Digger",
			"<html> The selected lemming digs any destructible obstacle down, during 5 steps.",
			new Color(210,105,42),
			State.DIGGER,
			"data\\img\\defaultTexturePack\\actionBarIcon\\digger.jpg"),
	SET_PARACHUTIST("Set Parachutist",
			"<html> The selected lemming falls 2 times less faster than others and survives of his fall.",
			new Color(0,0,128),
			State.PARACHUTIST,
			"data\\img\\defaultTexturePack\\actionBarIcon\\parachutist.jpg"),
	SET_TUNNELER("Set Tunneler",
			"<html> The selected lemming digs any destructible obstacle in front of him while walking, until open air.",
			new Color(105,105,105),
			State.TUNNELER,
			"data\\img\\defaultTexturePack\\actionBarIcon\\tunneler.jpg"),
	ADD_LEMMING("Add Lemming",
			"<html> Add, if left, a lemming on the field",
			new Color(255,215,0),
			"data\\img\\defaultTexturePack\\actionBarIcon\\addLemming.jpg"),
	KILL_LEMMING("Kill","<html> Kill the selected Lemming", 
			Color.RED, 
			"data\\img\\defaultTexturePack\\actionBarIcon\\armaggedon.jpg"),
	PAUSE("Pause","<html> Freeze the game until Pause is clicked again",
			Color.LIGHT_GRAY, 
			"data\\img\\defaultTexturePack\\actionBarIcon\\pause.jpg");
	
	private String description;
	private String title;
	private Color color;
	private State state;
	private String iconPath;
	
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private ActionType(String title, String description){
		this.title = title;
		this.description = description;
	}
	
	private ActionType(String title, String description, Color color, String iconPath) {
		this.title = title;
		this.description = description;
		this.color = color;
		this.iconPath = iconPath;
	}
	
	private ActionType(String title, String description, Color color, State state, String iconPath) {
		this.title = title;
		this.description = description;
		this.color = color;
		this.state = state;
		this.iconPath = iconPath;
	}
	
	public void SetDefaultColor() {
		color = DEFAULT_COLOR;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public State getState() {
		return state;
	}
	
	public String getIconPath(){
		return iconPath;
	}
	
}

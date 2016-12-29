package view;

import java.awt.Color;

import States.State;

/*
 * Concerne les boutons dans la barre d'actions.
 * Permet de connaitre le type et les effets de l'action.
 * Utile notamment pour stocker la dernière action séléctionnée.
 */
public enum ActionType {
	NONE("None","No action selected"),
	SET_BLOCKER("Set Blocker","Set the selected lemming to state Blocker.",new Color(70,130,180),State.BLOCKER),
	SET_CARPENTER("Set Carpenter", "Set the selected lemming to state Carpenter.",new Color(244,164,96),State.CARPENTER),
	SET_CLIMBER("Set Climber", "Set the selected lemming to state Climber.",new Color(124,252,0),State.CLIMBER),
	SET_DIGGER("Set Digger", "Set the selected lemming to state Digger.",new Color(210,105,42),State.DIGGER),
	SET_PARACHUTIST("Set Parachutist", "Set the selected lemming to state Parachutist.",new Color(0,0,128),State.PARACHUTIST),
	SET_TUNNELER("Set Tunneler", "Set the selected lemming to state Tunneler.",new Color(105,105,105),State.TUNNELER),
	ADD_LEMMING("Add Lemming", "Add, if left, a lemming on the field",new Color(255,215,0)),
	KILL_LEMMING("Kill","Kill the selected Lemming",Color.RED),
	PAUSE("Pause","Freeze the game until Pause is clicked again",Color.LIGHT_GRAY);
	
	private String description;
	private String title;
	private Color color;
	private State state;
	
	public static final Color DEFAULT_COLOR = Color.WHITE;
	
	private ActionType(String title, String description) {
		this.title = title;
		this.description = description;
		SetDefaultColor();
		state = null;
	}
	
	private ActionType(String title, String description, Color color) {
		this.title = title;
		this.description = description;
		this.color = color;
		state = null;
	}
	
	private ActionType(String title, String description, Color color, State state) {
		this.title = title;
		this.description = description;
		this.color = color;
		this.state = state;
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
}

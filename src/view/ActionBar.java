package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;

public class ActionBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AllView view;
	private Agent actionAgent = new Agent();
	private Agent timeAgent = new Agent();
	private Agent addLemmingAgent = new Agent();
	private Agent genocideAgent = new Agent();
	
	public ActionBar(AllView view) {
		this.view = view;
	}
	
	/*Cr�er une barre d'action classique avec tous les types d'actions
	 * Associe directement les ActionListeners
	 * Ajoute la barre dans la fen�tre
	 */
	public static ActionBar createDefaultActionBar(AllView view) {
		ActionBar actionBar = new ActionBar(view);
		actionBar.setLayout(new GridLayout(2,7));
		
		ActionButton button;
		actionBar.addButton(ActionType.SET_BLOCKER, view);
		actionBar.addButton(ActionType.SET_CARPENTER, view);
		actionBar.addButton(ActionType.SET_CLIMBER, view);
		actionBar.addButton(ActionType.SET_DIGGER, view);
		actionBar.addButton(ActionType.SET_PARACHUTIST, view);
		actionBar.addButton(ActionType.SET_CLIMBER, view);
		actionBar.addButton(ActionType.SET_TUNNELER, view);
		actionBar.addButton(ActionType.SET_BOMBER, view);
		
		button = new ActionButton(ActionType.ADD_LEMMING);
		button.addActionListener(new ButtonListener(view,button));
		actionBar.add(button);
		button.getInformationAgent().registerObserver(actionBar.getActionAgent());
		button.getAddLemmingAgent().registerObserver(actionBar.getAddLemmingAgent());
		
		button = new ActionButton(ActionType.KILL_LEMMING);
		button.addActionListener(new ButtonListener(view,button));
		actionBar.add(button);
		button.getInformationAgent().registerObserver(actionBar.getActionAgent());
		button.getGenocideAgent().registerObserver(actionBar.getGenocideAgent());
		
		button = new ActionButton(ActionType.PAUSE);
		button.addActionListener(new ButtonListener(view,button));
		actionBar.add(button);
		button.getInformationAgent().registerObserver(actionBar.getActionAgent());
		button.getTimeAgent().registerObserver(actionBar.getTimeAgent());
		
		actionBar.getView().getFrame().add(actionBar, BorderLayout.SOUTH);
		return actionBar;
	}
	
	public void addButton(ActionType actionType, AllView view) {
		ActionButton button = new ActionButton(actionType);
		button.addActionListener(new ButtonListener(view,button));
		add(button);
		button.getInformationAgent().registerObserver(actionAgent);
	}

	public AllView getView() {
		return view;
	}
	public Agent getActionAgent() {
		return actionAgent;
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
	
}

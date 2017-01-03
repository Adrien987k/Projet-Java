package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

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
	
	/*Créer une barre d'action classique avec tous les types d'actions
	 * Associe directement les ActionListeners
	 * Ajoute la barre dans la fenêtre
	 */
	public static ActionBar createDefaultActionBar(AllView view) {
		ActionBar actionBar = new ActionBar(view);
		actionBar.setLayout(new GridLayout(2,7));		
		
		ActionButton button;
		actionBar.addButton(ActionType.SET_BLOCKER, view,"data\\img\\blocker.jpg");
		actionBar.addButton(ActionType.SET_CARPENTER, view,"data\\img\\carpenter.jpg");
		actionBar.addButton(ActionType.SET_DIGGER, view,"data\\img\\digger.jpg");
		actionBar.addButton(ActionType.SET_PARACHUTIST, view,"data\\img\\parachutist.jpg");
		actionBar.addButton(ActionType.SET_TUNNELER, view,"data\\img\\tunneler.jpg");
		actionBar.addButton(ActionType.SET_BOMBER, view,"data\\img\\bomber.jpg");
		//TODO bouton maudit
		actionBar.addButton(ActionType.SET_CLIMBER, view,"data\\img\\climber.jpg");
		
		button = new ActionButton(ActionType.ADD_LEMMING,"data\\img\\addLemming.jpg");
		button.addActionListener(new ButtonListener(view,button));
		actionBar.add(button);
		button.getInformationAgent().registerObserver(actionBar.getActionAgent());
		button.getAddLemmingAgent().registerObserver(actionBar.getAddLemmingAgent());
		
		button = new ActionButton(ActionType.KILL_LEMMING,"data\\img\\armaggedon.jpg");
		button.addActionListener(new ButtonListener(view,button));
		actionBar.add(button);
		button.getInformationAgent().registerObserver(actionBar.getActionAgent());
		button.getGenocideAgent().registerObserver(actionBar.getGenocideAgent());
		
		button = new ActionButton(ActionType.PAUSE,"data\\img\\pause.jpg");
		button.addActionListener(new ButtonListener(view,button));
		actionBar.add(button);
		button.getInformationAgent().registerObserver(actionBar.getActionAgent());
		button.getTimeAgent().registerObserver(actionBar.getTimeAgent());
		
		actionBar.getView().getFrame().add(actionBar, BorderLayout.SOUTH);
		return actionBar;
	}
	
	@Deprecated
	public void addButton(ActionType actionType, AllView view) {
		ActionButton button = new ActionButton(actionType);
		button.addActionListener(new ButtonListener(view,button));
		add(button);
		button.getInformationAgent().registerObserver(actionAgent);
	}
	public void addButton(ActionType actionType, AllView view, String imagePath) {
		ActionButton button = new ActionButton(actionType,imagePath);
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

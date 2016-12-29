package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class ActionBar extends JPanel {
	private AllView view;
	private InformationAgent agent = new InformationAgent();
	public ActionBar(AllView view) {
		setLayout(new GridLayout(2,6));
		this.view = view;
	}
	
	/*Créer une barre d'action classique avec tous les types d'actions
	 * Associe directement les ActionListeners
	 * Ajoute la barre dans la fenêtre
	 */
	public static ActionBar createDefaultActionBar(AllView view) {
		ActionBar actionBar = new ActionBar(view);
		
		ActionButton button;
		actionBar.addButton(ActionType.SET_BLOCKER, view);
		actionBar.addButton(ActionType.SET_CARPENTER, view);
		actionBar.addButton(ActionType.SET_CLIMBER, view);
		actionBar.addButton(ActionType.SET_DIGGER, view);
		actionBar.addButton(ActionType.SET_PARACHUTIST, view);
		actionBar.addButton(ActionType.SET_CLIMBER, view);
		actionBar.addButton(ActionType.SET_TUNNELER, view);
		actionBar.addButton(ActionType.ADD_LEMMING, view);
		actionBar.addButton(ActionType.KILL_LEMMING, view);
		actionBar.addButton(ActionType.PAUSE, view);
		
		view.getFrame().add(actionBar, BorderLayout.SOUTH);
		return actionBar;
	}
	
	public void addButton(ActionType actionType, AllView view) {
		ActionButton button = new ActionButton(actionType);
		button.addActionListener(new ButtonListener(view,button));
		add(button);
		button.getAgent().registerObserver(agent);
	}

	public InformationAgent getAgent() {
		return agent;
	}
	
}

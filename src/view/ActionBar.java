package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class ActionBar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AllView view;
	
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
		actionBar.addButton(actionBar, ActionType.SET_BLOCKER, view,"blocker.jpg");
		actionBar.addButton(actionBar, ActionType.SET_CARPENTER, view,"carpenter.jpg");
		actionBar.addButton(actionBar, ActionType.SET_DIGGER, view,"digger.jpg");
		actionBar.addButton(actionBar, ActionType.SET_PARACHUTIST, view,"parachutist.jpg");
		actionBar.addButton(actionBar, ActionType.SET_TUNNELER, view,"tunneler.jpg");
		actionBar.addButton(actionBar, ActionType.SET_BOMBER, view,"bomber.jpg");
		//TODO bouton maudit
		actionBar.addButton(actionBar, ActionType.SET_CLIMBER, view,"climber.jpg");
		
		button = new ActionButton(actionBar,ActionType.ADD_LEMMING,"addLemming.jpg");
		button.addActionListener(new ButtonListener(view,button) {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getGame().getGameMap().addLemming();
			}
		});
		actionBar.add(button);
		
		button = new ActionButton(actionBar,ActionType.KILL_LEMMING,"armaggedon.jpg");
		button.addActionListener(new ButtonListener(view,button) {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getGame().getGameMap().killAllLemmings();
			}
		});
		actionBar.add(button);

		button = new ActionButton(actionBar,ActionType.PAUSE,"pause.jpg");
		button.addActionListener(new ButtonListener(view,button) {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getGame().getGameMap().pause();
			}
		});
		actionBar.add(button);

		actionBar.getView().getFrame().add(actionBar, BorderLayout.SOUTH);
		return actionBar;
	}
	
	@Deprecated
	public void addButton(ActionType actionType, AllView view) {
		ActionButton button = new ActionButton(actionType);
		button.addActionListener(new ButtonListener(view,button));
		add(button);
	}
	@Deprecated
	public void addButton(ActionType actionType, AllView view, String imagePath) {
		ActionButton button = new ActionButton(actionType,imagePath);
		button.addActionListener(new ButtonListener(view,button));
		add(button);
	}
	public void addButton(ActionBar actionBar, ActionType actionType, AllView view, String imagePath) {
		ActionButton button = new ActionButton(actionBar, actionType,imagePath);
		button.addActionListener(new ButtonListener(view,button));
		add(button);
	}

	public AllView getView() {
		return view;
	}
	
}

package view;
/**
 * ActionBar
 * 
 * @author Arnaud
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class ActionBar extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public static final int NO_USE = -1;
	
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
		actionBar.setLayout(new GridLayout(2, 7));
		
		ActionButton button;
		actionBar.addButton(actionBar, ActionType.SET_BLOCKER, view);
		actionBar.addButton(actionBar, ActionType.SET_CARPENTER, view);
		actionBar.addButton(actionBar, ActionType.SET_DIGGER, view);
		actionBar.addButton(actionBar, ActionType.SET_PARACHUTIST, view);
		actionBar.addButton(actionBar, ActionType.SET_TUNNELER, view);
		actionBar.addButton(actionBar, ActionType.SET_BOMBER, view);
		actionBar.addButton(actionBar, ActionType.SET_CLIMBER, view);
		
		button = new ActionButton(actionBar, ActionType.ADD_LEMMING, NO_USE);
		button.addActionListener(new ButtonListener(view, button) {
			@Override
			public void instantEffect() {
				view.getGame().getGameMap().addLemming();
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				super.actionPerformed(e);
				super.getActionButton().isLast(false);
			}
		});
		actionBar.add(button);
		
		button = new ActionButton(actionBar, ActionType.KILL_LEMMING, NO_USE);
		button.addActionListener(new ButtonListener(view, button) {
			@Override
			public void instantEffect() {
				view.getGame().getGameMap().killAllLemmings();
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				super.actionPerformed(e);
				super.getActionButton().isLast(false);
			}
		});
		actionBar.add(button);

		button = new ActionButton(actionBar, ActionType.PAUSE, NO_USE);
		button.addActionListener(new ButtonListener(view, button) {
			@Override
			public void instantEffect() {
				super.view.getGame().getGameMap().pause();
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				super.actionPerformed(e);
				super.getActionButton().isLast(false);
			}
		});
		actionBar.add(button);

		actionBar.getView().getFrame().add(actionBar, BorderLayout.SOUTH);
		
		return actionBar;
	}
	
	public void addButton(ActionBar actionBar, ActionType actionType, AllView view) {
		String fileToken = actionType.getState().getFileToken();
		int uses = view.getGame().getGameMap().getLevelParameterByToken(fileToken);
		ActionButton button = new ActionButton(actionBar, actionType, uses);
		button.addActionListener(new ButtonListener(view, button));
		add(button);
	}

	public AllView getView() {
		return view;
	}
	
}

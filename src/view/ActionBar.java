package view;
/**
 * ActionBar is a panel wich contains ActionButton.
 * Buttons are usually created directly in this object to ease connections
 * 	with the rest of the graphics interface.
 * 
 * @author Arnaud
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class ActionBar extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Used to grant illimited uses to a button.
	 */
	public static final int NO_USE = -1;
	
	/**
	 * The graphical interface
	 */
	private AllView view;
	
	/**
	 * Creates an empty action bar and links to the view.
	 * @param view The view it belongs to
	 */
	public ActionBar(AllView view) {
		this.view = view;
	}
	
	/**
	 * Create a default action bar with all basics actions required to play correctly the game.
	 * It creates an action bar with 7 buttons used to change lemming's state and
	 * 	3 additionals buttons to add a lemming on the field, to kill every living lemming or to freeze the game.
	 * ActionListeners are directly implemented and linked with buttons.
	 * The action bar is added to the view.
	 * @param view The view it belongs to
	 * @return a default action bar
	 */
	public static ActionBar createDefaultActionBar(AllView view) {
		ActionBar actionBar = new ActionBar(view);
		actionBar.setLayout(new GridLayout(2, 5));
		
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
	
	/**
	 * Adds a button to the actionBar with the specified action in the specified view.
	 * This method should only be used to create state changer buttons.
	 * @param actionBar the action bar it belongs to.
	 * @param actionType the desired action. 
	 * @param view the view it belongs to
	 */
	public void addButton(ActionBar actionBar, ActionType actionType, AllView view) {
		String fileToken = actionType.getState().getFileToken();
		int uses = view.getGame().getGameMap().getLevelParameterByToken(fileToken);
		ActionButton button = new ActionButton(actionBar, actionType, uses);
		button.addActionListener(new ButtonListener(view, button));
		add(button);
	}
	
	/**
	 * 
	 * @return the view it belongs to.
	 */
	public AllView getView() {
		return view;
	}
	
}

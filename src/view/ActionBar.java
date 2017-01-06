//TODO ajouter des compteurs d'actions restantes

package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;

public class ActionBar extends JPanel{
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
		actionBar.addButton(actionBar, ActionType.SET_BLOCKER, view,"data\\img\\defaultTexturePack\\actionBarIcon\\blocker.jpg");
		actionBar.addButton(actionBar, ActionType.SET_CARPENTER, view,"data\\img\\defaultTexturePack\\actionBarIcon\\carpenter.jpg");
		actionBar.addButton(actionBar, ActionType.SET_DIGGER, view,"data\\img\\defaultTexturePack\\actionBarIcon\\digger.jpg");
		actionBar.addButton(actionBar, ActionType.SET_PARACHUTIST, view,"data\\img\\defaultTexturePack\\actionBarIcon\\parachutist.jpg");
		actionBar.addButton(actionBar, ActionType.SET_TUNNELER, view,"data\\img\\defaultTexturePack\\actionBarIcon\\tunneler.jpg");
		actionBar.addButton(actionBar, ActionType.SET_BOMBER, view,"data\\img\\defaultTexturePack\\actionBarIcon\\bomber.jpg");
		actionBar.addButton(actionBar, ActionType.SET_CLIMBER, view,"data\\img\\defaultTexturePack\\actionBarIcon\\climber.jpg");
		
		button = new ActionButton(actionBar,ActionType.ADD_LEMMING,"data\\img\\defaultTexturePack\\actionBarIcon\\addLemming.jpg",1);
		button.addActionListener(new ButtonListener(view,button) {
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
		
		button = new ActionButton(actionBar,ActionType.KILL_LEMMING,"data\\img\\defaultTexturePack\\actionBarIcon\\armaggedon.jpg",1);
		button.addActionListener(new ButtonListener(view,button) {
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

		button = new ActionButton(actionBar,ActionType.PAUSE,"data\\img\\defaultTexturePack\\actionBarIcon\\pause.jpg",1);
		button.addActionListener(new ButtonListener(view,button) {
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
		ActionButton button = new ActionButton(actionBar, actionType,imagePath,2);
		button.addActionListener(new ButtonListener(view,button));
		add(button);
	}

	public AllView getView() {
		return view;
	}
	
}

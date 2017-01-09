package view;

import javax.swing.JFrame;

import game.Game;

public abstract class AllView extends MyObservable implements View {
	
	protected Game game;
	protected JFrame frame;
	protected GamePanel gamePanel;
	protected ActionBar actionBar;
	protected InformationPanel informationPanel;
	protected ActionType lastActionSelected;
	protected ActionButton lastActionButtonSelected;
	
	
	public abstract void switchToDefaultAction();
	
	public void setLastActionButtonSelected(ActionButton lastActionButtonSelected) {
		this.lastActionButtonSelected = lastActionButtonSelected;
	}
	public void setLastActionSelected(ActionType actionType) {
		this.lastActionSelected = actionType;
	}
	
	public ActionType getLastActionSelected() {
		return lastActionSelected;
	}
	public ActionButton getLastActionButtonSelected() {
		return lastActionButtonSelected;
	}
	public JFrame getFrame() {
		return frame;
	}
	public InformationPanel getInformationPanel() {
		return informationPanel;
	}
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	public ActionBar getActionBar() {
		return actionBar;
	}
	public Game getGame() {
		return game;
	}
}

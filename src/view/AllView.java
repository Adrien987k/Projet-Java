package view;

import javax.swing.JFrame;

import game.Game;

public abstract class AllView extends MyObservable implements View {
	public abstract void setLastActionButtonSelected(ActionButton lastActionButtonSelected);
	public abstract ActionType getLastActionSelected();
	public abstract void setLastActionSelected(ActionType actionType);
	public abstract ActionButton getLastActionButtonSelected();
	public abstract JFrame getFrame();
	public abstract void switchToDefaultAction();
	public abstract InformationPanel getInformationPanel();
	public abstract GamePanel getGamePanel();
	public abstract ActionBar getActionBar();
	public abstract Game getGame();
}

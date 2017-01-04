package view;

import javax.swing.JFrame;

import game.Game;

public abstract class AllView extends MyObservable implements View {
	public abstract void setCurrentAction(ActionType actionType);
	public abstract ActionType getCurrentAction();
	public abstract JFrame getFrame();
	public abstract void switchToDefaultAction();
	public abstract InformationPanel getInformationPanel();
	public abstract GamePanel getGamePanel();
	public abstract ActionBar getActionBar();
	public abstract Game getGame();
}

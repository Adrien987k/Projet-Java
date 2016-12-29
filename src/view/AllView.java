package view;

import javax.swing.JFrame;

public abstract class AllView extends MyObservable implements View {
	public void setCurrentAction(ActionType actionType) {
	}
	public ActionType getCurrentAction() {
		return null;
	}
	public JFrame getFrame() {
		return null;
	}
	public void switchToDefaultAction() {
		
	}
	public InformationPanel getInformationPanel() {
		return null;
	}
	public GamePanel getGamePanel() {
		return null;
	}
}

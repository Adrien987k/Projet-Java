package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

class ButtonListener implements ActionListener {
	AllView view;
	ActionButton actionButton;
	public ButtonListener(AllView view,ActionButton actionButton) {
		this.view = view;
		this.actionButton = actionButton;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.setCurrentAction(actionButton.getActionType());
		System.out.println(actionButton.getActionTitle());
	}
}
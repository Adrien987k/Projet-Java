package view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class ActionButton extends JButton implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color DEFAULT_COLOR = Color.WHITE;
	
	private Agent informationAgent = new Agent();
	private ActionType actionType;
	  
	  /*pour rajouter un logo différent pour chque bouton*/
	  //private Image img;

	  public ActionButton(ActionType actionType){
	    super(actionType.getTitle());
	    setBackground(actionType.getColor());
	    this.actionType	= actionType;
	    addMouseListener(this);
	  }
	  
	public String getActionTitle() {
		return actionType.getTitle();
	}

	public String getActionDescription() {
		return actionType.getDescription();
	}
	  
	public ActionType getActionType() {
		return actionType;
	}

	public Agent getAgent() {
		return informationAgent;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		informationAgent.addChange(new ChangeDescription(actionType));
		informationAgent.notifyObserver();
		setBackground(getActionType().getColor().brighter());
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(getActionType().getColor());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}

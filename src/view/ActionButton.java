package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ActionButton extends JButton implements MouseListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Color DEFAULT_COLOR = Color.WHITE;
	public static final int X = 100;
	public static final int Y = 100;
	
	private ActionBar actionBar;
	

	private ActionType actionType;
	private int usesLeft;
	private Color currentColor = DEFAULT_COLOR;
	private ImageIcon icon;

	public ActionButton() {
		/*
		 * Use for the default_button,
		 * when no button has been selected (when the game starts)
		 * when the selected button turned off because the user can't use it anymore
		 */
		
	}
	
	private void loadActionType(ActionType actionType){
		this.actionType	= actionType;
	    setPreferredSize(new Dimension(X,Y));
		   
	    icon = Texture.createIcon(actionType.getIconPath());
	    
	    if(getUsesLeft() > 0) {
	    	if(icon == null)
	    		setText(getActionTitle() + " " + getUsesLeft());
	    	else
	    		setText("" + getUsesLeft());
	    } else {
	    	if(icon == null)
	    		setText(getActionTitle());
	    }
	    setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
	    setIcon(icon);
		    
	    addMouseListener(this);
	}
	
	public ActionButton(ActionBar actionBar, ActionType actionType){
	    super();
	    this.actionBar = actionBar;
	    loadActionType(actionType);
	}
		  
	public ActionButton(ActionBar actionBar, ActionType actionType, int uses){
	    super();
	    this.actionBar = actionBar;
	    this.usesLeft = uses;
	    if(usesLeft == 0)
	    	setEnabled(false);
	    setBackground(DEFAULT_COLOR);
	    loadActionType(actionType);
	}
		 
	public void decUsesLeft() {
		if(getUsesLeft() > 0)
			setUsesLeft(getUsesLeft() -1);
		if(getUsesLeft() == 0) { 
			getActionBar().getView().switchToDefaultAction();
			isLast(false);
			setEnabled(false);
		}
		setText("" + getUsesLeft());
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
	
	public ActionBar getActionBar() {
		return actionBar;
	} 
	
	public int getUsesLeft() {
		return usesLeft;
	}
	public void setUsesLeft(int usesLeft) {
		this.usesLeft = usesLeft;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}
	public Color setCurrentColor(Color color) {
		return this.currentColor = color;
	}	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		AllView view = getActionBar().getView();
		view.getInformationPanel().getActionDescription().setText(getActionType().getDescription());
		setBackground(getCurrentColor().darker());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(getCurrentColor());
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	public void isLast(boolean isLast) {
		if(isLast) setCurrentColor(actionType.getColor());
		else setCurrentColor(DEFAULT_COLOR);
		setBackground(getCurrentColor());
	}
}

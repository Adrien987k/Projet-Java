package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
		//Use for the default_button, 
		//when no button has been selected (when the game starts)
		//when the selected button turned off because the user can't use it anymore
		
	}
	  @Deprecated
	  public ActionButton(ActionType actionType){
	    super(actionType.getTitle());
	    setBackground(actionType.getColor());
	    this.actionType	= actionType;
	    addMouseListener(this);
	  }
	  @Deprecated
	  public ActionButton(ActionType actionType, String imagePath){
		    super();
		    //setBackground(actionType.getColor());
		    this.actionType	= actionType;
		    setPreferredSize(new Dimension(X,Y));
		    try {
		        this.icon = new ImageIcon(ImageIO.read(new File(imagePath)));
		        setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
		        setIcon(icon);
		      } catch (IOException e) {
		        setText(getActionTitle());
		      }
		    
		    addMouseListener(this);
		  }
	  @Deprecated
	  public ActionButton(ActionBar actionBar, ActionType actionType, String imagePath){
		    super();
		    //setBackground(actionType.getColor());
		    this.actionBar = actionBar;
		    this.actionType	= actionType;
		    setPreferredSize(new Dimension(X,Y));
		    setBackground(DEFAULT_COLOR);
		    try {
		        this.icon = new ImageIcon(ImageIO.read(new File(imagePath)));
		        setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
		        setIcon(icon);
		      } catch (IOException e) {
		        setText(getActionTitle());
		      }
		    
		    addMouseListener(this);
		  }
	  public ActionButton(ActionBar actionBar, ActionType actionType, String imagePath, int uses){
		    super();
		    //setBackground(actionType.getColor());
		    this.actionBar = actionBar;
		    this.actionType	= actionType;
		    this.usesLeft = uses;
		    if(usesLeft == 0)
		    	setEnabled(false);
		    setPreferredSize(new Dimension(X,Y));
		    setBackground(DEFAULT_COLOR);
		    try {
		        this.icon = new ImageIcon(ImageIO.read(new File(imagePath)));
		        setPreferredSize(new Dimension(icon.getIconWidth(),icon.getIconHeight()));
		        setIcon(icon);
		      } catch (IOException e) {
		        setText(getActionTitle());
		      }

		    addMouseListener(this);
		  }

	public void decUsesLeft() {
		if(getUsesLeft() > 0)
			setUsesLeft(getUsesLeft() -1);
		if(getUsesLeft() <= 0) { 
			getActionBar().getView().switchToDefaultAction();
			isLast(false);
			setEnabled(false);
		}
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
		if(isLast) setCurrentColor(Color.YELLOW);
		else setCurrentColor(DEFAULT_COLOR);
		setBackground(getCurrentColor());
	}
}

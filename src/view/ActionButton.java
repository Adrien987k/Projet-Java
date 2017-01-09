/**
 * ActionButton is the button used to play the game.
 * We recommend to associate this button to an action bar
 * 	and even to create the button directly in the action bar.
 * 
 * @author Arnaud
 */

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ActionButton extends JButton implements MouseListener {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * The default background color
	 */
	private static final Color DEFAULT_COLOR = Color.WHITE;
	
	/**
	 * The prefered width
	 */
	public static final int X = 100;
	
	/**
	 * The prefered height
	 */
	public static final int Y = 100;
	
	/**
	 * The actionBar it belongs to 
	 */
	private ActionBar actionBar;
	
	/**
	 * The action when used
	 */
	private ActionType actionType;
	
	/**
	 * Number of uses.
	 * Set this field to a negative value to grant unlimited uses.
	 */
	private int usesLeft;
	
	/**
	 * The current background color.
	 * The background color may change during the game,
	 * 	usually because of mouse interactions.
	 * 
	 */
	private Color currentColor = DEFAULT_COLOR;
	
	/**
	 * The icon of the button.
	 * If set to null, the action's tittle will be displayed instead.
	 */
	private ImageIcon icon;

	/**
	 * Create an empty button with no action or title.
	 * This constructor should be called only to create a default_button.
	 * default_button is used in the graphic interface when no button has been selected,
	 * 	when the game starts or when the selected button turned off because the user can't use it anymore.
	 */
	public ActionButton() {
		super();
	}
	
	/**
	 * Create a button in the specified action bar with the specified action.
	 * A mouseListener is associated.
	 * Please check the loadActionType method for more details.
	 * @param actionBar The action bar it belongs to.
	 * @param actionType The desired action.
	 */
	public ActionButton(ActionBar actionBar, ActionType actionType){
	    super();
	    this.actionBar = actionBar;
	    this.usesLeft = -1;
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
	
	/**
	 * Set an actionType to the button.
	 * If the action has an icon associated, try to read it.
	 * If not, the action tittle will be displayed.
	 * The number of uses is displayed on the right if the action has a limited amount of uses.
	 * Associate a mouseListener wich is used to update the information panel and the background color.
	 * Check methods below or the information panel for more details.
	 * @param actionType
	 */
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
	
	
	/**
	 * Decrements the usesLeft counter.
	 * This is method is called when a validated action has been done.
	 * Updates the field usesLeft and disable the button if there is no use left.
	 */
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
	
	/*GETTERS AND SETTERS*/
	
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
	
	/*END OF GETTERS AND SETTER*/
	
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	/**
	 * Set the description and change the background color.
	 * Set the description in the information panel to the action description of this button.
	 * Change the background color in a darker color.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		AllView view = getActionBar().getView();
		view.getInformationPanel().getActionDescription().setText(getActionType().getDescription());
		setBackground(getCurrentColor().darker());
	}

	/**
	 * Set the background color to the default one.
	 * This method is used when the background has been darkened.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(getCurrentColor());
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
	
	/**
	 * Update the visual of the button if it is the last button.
	 * The last button have a special background color if isLast is true.
	 * If isLast is false, the background color is set with the currentColor.
	 * @param isLast
	 */
	public void isLast(boolean isLast) {
		if(isLast) setCurrentColor(actionType.getColor());
		else setCurrentColor(DEFAULT_COLOR);
		setBackground(getCurrentColor());
	}
}

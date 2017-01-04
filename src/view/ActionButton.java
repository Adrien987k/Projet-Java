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
	@SuppressWarnings("unused")
	private static final Color DEFAULT_COLOR = Color.WHITE;
	public static final int X = 100;
	public static final int Y = 100;
	
	private ActionBar actionBar;
	

	private ActionType actionType;
	
	  private ImageIcon icon;

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
	  public ActionButton(ActionBar actionBar, ActionType actionType, String imagePath){
		    super();
		    //setBackground(actionType.getColor());
		    this.actionBar = actionBar;
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
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		AllView view = getActionBar().getView();
		view.getInformationPanel().getActionDescription().setText(getActionType().getDescription());
		//TODO mettre en surbrillance
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//TODO enlever la surbrillance
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}

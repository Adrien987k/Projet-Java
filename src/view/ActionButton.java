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
	
	private Agent informationAgent = new Agent();
	private Agent timeAgent = new Agent();
	private Agent addLemmingAgent = new Agent();
	private Agent genocideAgent = new Agent();
	
	

	private ActionType actionType;
	
	  /*pour rajouter un logo différent pour chque bouton*/
	  private ImageIcon icon;

	  @Deprecated
	  public ActionButton(ActionType actionType){
	    super(actionType.getTitle());
	    setBackground(actionType.getColor());
	    this.actionType	= actionType;
	    addMouseListener(this);
	  }
	  
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
	  
	public void notifiyAgents() {
		getTimeAgent().notifyObserver();
		getAddLemmingAgent().notifyObserver();
		getGenocideAgent().notifyObserver();
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

	public Agent getAddLemmingAgent() {
		return addLemmingAgent;
	}
	public Agent getInformationAgent() {
		return informationAgent;
	}
	public Agent getTimeAgent() {
		return timeAgent;
	}
	public Agent getGenocideAgent() {
		return genocideAgent;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		informationAgent.addChange(new ChangeAction(actionType));
		informationAgent.notifyObserver();
//		setBackground(getActionType().getColor().brighter());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//setBackground(getActionType().getColor());
		
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

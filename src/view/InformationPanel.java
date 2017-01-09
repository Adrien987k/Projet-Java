package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * InformationPanel is a panel used to display various informations about the game and recents actions.
 * @author Arnaud
 *
 */
public class InformationPanel extends JPanel implements MyObserver {
	
	private static final long serialVersionUID = 1L;
	/**
	 * The description of the button pointed by the mouse
	 */
	private JLabel actionDescription;
	/**
	 * The label corresponding to the number of dead lemmings
	 */
	private JLabel deadLemmings;
	/**
	 * The label corresponding to the number of free lemmings
	 */
	private JLabel freeLemmings;
	/**
	 * the label corresponding to the number of remaining leemmings
	 */
	private JLabel remainingLemmings;
	/**
	 * The view it belongs to;
	 */
	private AllView view;
	
	/**
	 * The number of dead leemmings
	 */
	private int nbDeadLemmings = 0;
	/**
	 * the number of free leemmings
	 */
	private int nbFreeLemmings = 0;
	/**
	 * the number of remaining leemmings
	 */
	private int nbRemainingLemmings = 0;
	
	/**
	 * The prefered width of the panel
	 */
	public static final int DEFAULT_WIDTH = 300;
	/**
	 * The prefered length of the panel
	 */
	public static final int DEFAULT_LENGTH = 500;
	
	/**
	 * Creates an empty panel.
	 * @param view
	 */
	public InformationPanel(AllView view) {
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_LENGTH));
		this.view = view;
	}
	
	/**
	 * Creates a default information panel wich displays various informations to improve game ergonomy.
	 * @param view The view it belongs to.
	 * @return The created information panel.
	 */
	public static InformationPanel createDefaultInformationPanel(AllView view ) {
		InformationPanel informationPanel = new InformationPanel(view);
		informationPanel.setLayout(new GridLayout(10,1));

		informationPanel.deadLemmings = new JLabel("Dead lemmings: " + Integer.toString(informationPanel.nbDeadLemmings));
		informationPanel.add(informationPanel.deadLemmings,BorderLayout.CENTER);
		informationPanel.freeLemmings = new JLabel("Free lemmings: " + Integer.toString(informationPanel.nbFreeLemmings));
		informationPanel.add(informationPanel.freeLemmings);
		informationPanel.remainingLemmings = new JLabel("Remaining lemmings: " + Integer.toString(informationPanel.nbFreeLemmings));
		informationPanel.add(informationPanel.remainingLemmings);
		informationPanel.actionDescription = new JLabel("Nothing.");
		informationPanel.add(informationPanel.actionDescription,BorderLayout.SOUTH);

		return informationPanel;
	}
	
	/* GETTERS AND SETTERS */
	public AllView getView() {
		return view;
	}
	public JLabel getActionDescription() {
		return actionDescription;
	}
	
	public JLabel getDeadLemmings() {
		return deadLemmings;
	}
	
	public void setDeadLemmings(int deadLemmings) {
		this.deadLemmings.setText("Lemmings dead: " + Integer.toString(deadLemmings));
	}

	public JLabel getFreeLemmings() {
		return freeLemmings;
	}
	
	public void setFreeLemmings(int freeLemmings) {
		this.freeLemmings.setText("Lemmings free: " + Integer.toString(freeLemmings));
	}
	
	public JLabel getRemainingLemmings() {
		return remainingLemmings;
	}
	
	public void setRemainingLemmings(int remainingLemmings) {
		this.remainingLemmings.setText("Lemmings remmaining: " + Integer.toString(remainingLemmings)); 
	}
	
	public int getNbDeadLemmings() {
		return nbDeadLemmings;
	}
	
	public void setNbDeadLemmings(int nbDeadLemmings) {
		this.nbDeadLemmings = nbDeadLemmings;
	}

	public int getNbFreeLemmings() {
		return nbFreeLemmings;
	}
	public void setNbFreeLemmings(int nbFreeLemmings) {
		this.nbFreeLemmings = nbFreeLemmings;
	}
	
	public int getNbRemainingLemmings() {
		return nbRemainingLemmings;
	}
	public void setNbRemainingLemmings(int nbRemainingLemmings) {
		this.nbRemainingLemmings = nbRemainingLemmings;
	}

	/* END OF GETTERS AND SETTERS */
	
	/**
	 * Update informations given by Agents in GameMap.
	 * If the game is ended, display an optionDialog to quit the game or to load the next level.
	 */
	@Override
	public void update(List<Change> changes) {
		for(Change change:changes) {
			
			setDeadLemmings(change.getNbDeadLemmings());
			setNbDeadLemmings(change.getNbDeadLemmings());
			setFreeLemmings(change.getNbFreeLemmings());
			setNbFreeLemmings(change.getNbFreeLemmings());
			setRemainingLemmings(change.getNbRemainingLemmings());
			
			if(!( change.getRunning())) {
				boolean isVictory = false;
				if(change.getLevelParameters().get("objective").intValue() <= getNbFreeLemmings() )
					isVictory = true;
				String[] options = new String[] {"Next level","Quit game"};
				
				//valeur de retour du choix
				int option = JOptionPane.showOptionDialog(null, 
						new ScoreBoard(getNbDeadLemmings(), getNbFreeLemmings(), isVictory),
			    	      isVictory ? "Victory !" : "Game Over",
			    	      JOptionPane.DEFAULT_OPTION,
			    	      JOptionPane.PLAIN_MESSAGE,
			    	      null,
			    	      options,
			    	      options[1]);
				if(option == 1) {
					view.getGame().closeGame();
					view.getFrame().dispose();
				}

			}
		}
	}
	

}

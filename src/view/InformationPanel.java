package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationPanel extends JPanel implements MyObserver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel actionDescription;
	private JLabel deadLemmings;
	private JLabel freeLemmings;
	private JLabel remainingLemmings;
	private AllView view;
	
	private int nbDeadLemmings = 0;
	private int nbFreeLemmings = 0;
	private int nbRemainingLemmings = 0;
	

	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_LENGTH = 500;
	
	public InformationPanel(AllView view) {
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_LENGTH));
		this.view = view;
	}
	
	
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

	@Override
	public void update(List<? extends AbsChange> changes) {
		for(AbsChange change:changes) {
			setDeadLemmings(((ChangeData) change).getNbDeadLemmings());
			setFreeLemmings(((ChangeData) change).getNbFreeLemmings());
			setRemainingLemmings(((ChangeData) change).getNbRemainingLemmings());
		}
	}
	

}

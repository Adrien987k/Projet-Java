package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InformationPanel extends JPanel implements MyObserver {
	private JLabel actionDescription;
	private JLabel deadLemmings;
	private JLabel freeLemmings;
	private JButton testButton;
	private AllView view;
	
	private int nbDeadLemmings = 0;
	private int nbFreeLemmings = 0;
	
	public static final int DEFAULT_WIDTH = 300;
	public static final int DEFAULT_LENGTH = 500;
	
	public InformationPanel(AllView view) {
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_LENGTH));
		
		this.view = view;
		
	}
	
	
	public static InformationPanel createDefaultInformationPanel(AllView view ) {
		InformationPanel informationPanel = new InformationPanel(view);
		informationPanel.setLayout(new GridLayout(4,1));
		
		
		informationPanel.testButton = new JButton("Test");
		informationPanel.add(informationPanel.testButton, BorderLayout.NORTH);
		informationPanel.deadLemmings = new JLabel("Dead lemmings: " + Integer.toString(informationPanel.nbDeadLemmings));
		informationPanel.add(informationPanel.deadLemmings,BorderLayout.CENTER);
		informationPanel.freeLemmings = new JLabel("Free lemmings: " + Integer.toString(informationPanel.nbFreeLemmings));
		informationPanel.add(informationPanel.freeLemmings);
		informationPanel.actionDescription = new JLabel("Nothing.");
		informationPanel.add(informationPanel.actionDescription,BorderLayout.SOUTH);
		
		informationPanel.getButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				informationPanel.getButton().setBackground(informationPanel.getView().getCurrentAction().getColor());
				informationPanel.getActionDescription().setText(informationPanel.getView().getCurrentAction().getDescription());
				informationPanel.getView().getFrame().repaint();
				informationPanel.getView().switchToDefaultAction();
			}
		});
			
		return informationPanel;
	}
	public AllView getView() {
		return view;
	}
	public JLabel getActionDescription() {
		return actionDescription;
	}
	public JButton getButton() {
		return testButton;
	}
	
	public JLabel getDeadLemmings() {
		return deadLemmings;
	}
	public void setDeadLemmings(JLabel deadLemmings) {
		this.deadLemmings = deadLemmings;
	}

	public JLabel getFreeLemmings() {
		return freeLemmings;
	}
	public void setFreeLemmings(JLabel freeLemmings) {
		this.freeLemmings = freeLemmings;
	}


	@Override
	public void update(List<? extends AbsChange> changes) {
		for(AbsChange change:changes) {
			getActionDescription().setText(((ChangeDescription) change).getActionType().getDescription());
		}
	}

}

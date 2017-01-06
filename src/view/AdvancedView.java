package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.Game;

public class AdvancedView extends AllView {
	
	@SuppressWarnings("unused")
	private static final ActionType DEFAULT_ACTION = ActionType.NONE;
	private static final ActionButton DEFAULT_BUTTON = new ActionButton();

	private Game game;
	private JFrame frame;
	private GamePanel gamePanel;
	private ActionBar actionBar;
	private InformationPanel informationPanel;
	private ActionType lastActionSelected;
	private ActionButton lastActionButtonSelected;
	
	
	public AdvancedView(Game game, int scale) {
		super();
		this.game = game;
		frame = createAdvancedView(game, scale);
		establishConnexions(game);
	}
	
	private JFrame createAdvancedView(Game game, int scale) {
		frame = new JFrame("Lemmings");
		gamePanel = new GamePanel(game, scale,this);
		registerObserver(gamePanel);
		frame.add(gamePanel, BorderLayout.CENTER);
		
		actionBar = ActionBar.createDefaultActionBar(this);
		frame.add(actionBar,BorderLayout.SOUTH);
		
		informationPanel = InformationPanel.createDefaultInformationPanel(this);
		frame.add(informationPanel,BorderLayout.EAST);
		
		switchToDefaultAction();
		frame.setMinimumSize(new Dimension(game.getWidth() + informationPanel.getWidth(),
								game.getHeight() + actionBar.getHeight()));
		frame.pack();

		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		gamePanel.requestFocusInWindow();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
	}
	
	
	public void establishConnexions(Game game) {
		game.registerObserver(this);
		
		//actionBar.getDescriptionAgent().registerObserver(getInformationPanel().getDescriptionAgent());
		//getInformationPanel().getDescriptionAgent().registerObserver(informationPanel);
	
	}

	@Override
	public ActionType getLastActionSelected() {
		return lastActionSelected;
	}
	
	@Override
	public JFrame getFrame() {
		return frame;
	}
	
	@Override
	public InformationPanel getInformationPanel() {
		return informationPanel;
	}
	
	@Override
	public GamePanel getGamePanel() {
		return gamePanel;
	}
	
	@Override
	public ActionBar getActionBar() {
		return actionBar;
	}
	
	@Override
	public Game getGame() {
		return game;
	}
	
	@Override
	public void switchToDefaultAction() {
		setLastActionButtonSelected(DEFAULT_BUTTON);
		setLastActionSelected(ActionType.NONE);
		informationPanel.getActionDescription().setText(ActionType.NONE.getDescription());
	}              
	
	@Override
	public void setLastActionSelected(ActionType lastActionSelected) {
		this.lastActionSelected = lastActionSelected;
	}

	public ActionButton getLastActionButtonSelected() {
		return lastActionButtonSelected;
	}
	
	public void setLastActionButtonSelected(ActionButton lastActionButtonSelected) {
		this.lastActionButtonSelected = lastActionButtonSelected;
	}
	
	@Override
	public void update(List<? extends AbsChange> changes) {
		addAllChanges(changes);
		notifyObserver();
	}

}

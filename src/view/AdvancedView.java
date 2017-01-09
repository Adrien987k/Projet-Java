package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.Game;

/**
 * AdvancedView is GUI wich provide a complete set up to play correctly the game. 
 * @author Arnaud
 *
 */
public class AdvancedView extends AllView {
	
	/**
	 * The default action.
	 * Can be used instead of null when there is no action selected
	 * 	or when you need an actionless field.
	 */
	@SuppressWarnings("unused")
	private static final ActionType DEFAULT_ACTION = ActionType.NONE;
	
	/**
	 * The default button.
	 * Can be used instead of null when there is no button selected,
	 * 	when the game starts
	 * 	or when the previous button has been disabled.
	 */
	private static final ActionButton DEFAULT_BUTTON = new ActionButton();
	
	/**
	 * Create a default advance view for the specified game with the specified scale.
	 * @param game The game it belongs to.
	 * @param scale The scale of the display.
	 * 			Larger is the parameter, large is the size of the window.
	 */
	public AdvancedView(Game game, int scale) {
		super();
		this.game = game;
		frame = createAdvancedView(game, scale);
		establishConnexions(game);
	}
	
	/**
	 * Create the window for the specified game and the specified scale.
	 * The frame created will contain all the GUI, such as a game panel,
	 * 	an action bar and an information panel.
	 * @param game The game it belongs to.
	 * @param scale	The scale of the display.
	 * 			Larger is the parameter, large is the size of the window.
	 * @return The created frame.
	 */
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
	
	/**
	 * Associate all required links between observers and observables.
	 * 	In this particular case, the game observes this object.
	 * @param game
	 */
	public void establishConnexions(Game game) {
		game.registerObserver(this);
	}
	
	
	@Override
	public void switchToDefaultAction() {
		setLastActionButtonSelected(DEFAULT_BUTTON);
		setLastActionSelected(ActionType.NONE);
		informationPanel.getActionDescription().setText(ActionType.NONE.getDescription());
	}              
	

	@Override
	public void update(List<Change> changes) {
		addAllChanges(changes);
		notifyObserver();
	}

}


/*
//TODO: Cr�er une classe pour la barre d'action et la barre d'info
 * Ajouter la mise � jour des descriptions quand on passe la souris dessus
 * Trouver une m�thode pour �viter le switch
 * Regarder si c'est possible d'utiliser Observable/Observer
 * 	pour gamePanel + actionBar + infoBar en m�me temps
 * Revoir peut �tre les changements pour y ajouter ceux avec des actionType
 * 	ou les infoData
*/
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.Game;

public class AdvancedView extends AllView {
	
	private static final ActionType DEFAULT_ACTION = ActionType.NONE;

	private JFrame frame;
	private GamePanel gamePanel;
	private ActionBar actionBar;
	private InformationPanel informationPanel;
	private ActionType currentAction;
	
	
	public AdvancedView(int x, int y, Game game, int scale) {
		frame = createAdvancedView(x, y, game, scale);
		establishConnexions(game);
	}
	
	private JFrame createAdvancedView(int x, int y, Game game, int scale) {
		frame = new JFrame("Game " + 0);
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
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);	
		gamePanel.requestFocusInWindow();
		frame.setVisible(true);
		return frame;
	}
	
	public void establishConnexions(Game game) {
		game.registerObserver(this);
		
		actionBar.getActionAgent().registerObserver(getInformationPanel().getDescriptionAgent());
		getInformationPanel().getDescriptionAgent().registerObserver(informationPanel);
	
	}
	
	/* Cr�er un bouton effectuant l'action donn�e en param�tre */
	public ActionButton createActionButton(ActionType actionType) {
		ActionButton button = new ActionButton(actionType);
		button.addActionListener(new ButtonListener(this,button));
		return button;
	}
	@Override
	public void setCurrentAction(ActionType actionType) {
		currentAction = actionType;
	}
	@Override
	public ActionType getCurrentAction() {
		return currentAction;
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
	/* A chaque fois qu'une action est bien faite,
	 * on remet l'action actuelle par d�faut.
	 */
	@Override
	public void switchToDefaultAction() {
		setCurrentAction(DEFAULT_ACTION);
		informationPanel.getActionDescription().setText(ActionType.NONE.getDescription());
	}                    

	@Override
	public void update(List<? extends AbsChange> changes) {
		addAllChanges(changes);
		notifyObserver();
	}

}

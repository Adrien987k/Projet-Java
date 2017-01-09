package view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.Game;

/**
 * BasicView is a primitive, it's not enought to play correctly to the game.
 * This view doesn't have any action bar or information panel.
 * It only displays the game panel with the map.
 * @author Arnaud
 *
 */
@Deprecated
public class BasicView extends AllView {
	
	public BasicView(int x, int y, Game game, int scale) {
		frame = createBasicView(x, y, game, scale);
		game.registerObserver(this);
	}
	
	private JFrame createBasicView(int x, int y, Game game, int scale) {
		frame = new JFrame("Game");
		gamePanel = new GamePanel(game, scale,this);
		registerObserver(gamePanel);
		frame.add(gamePanel);
		frame.pack();
		frame.setLocation(x, y);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);	
		gamePanel.requestFocusInWindow();
		frame.setVisible(true);
		return frame;
	}
	
	
	@Override
	public void update(List<Change> changes) {
		addAllChanges(changes);
		notifyObserver();
	}

	@Override
	public ActionType getLastActionSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void switchToDefaultAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InformationPanel getInformationPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GamePanel getGamePanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionBar getActionBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game getGame() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastActionButtonSelected(ActionButton lastActionButtonSelected) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActionButton getLastActionButtonSelected() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastActionSelected(ActionType actionType) {
		// TODO Auto-generated method stub
		
	}

}

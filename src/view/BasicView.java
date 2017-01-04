package view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.Game;

public class BasicView extends AllView {
	
	private JFrame frame;
	private GamePanel gamePanel;
	
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
	public void update(List<? extends AbsChange> changes) {
		addAllChanges(changes);
		notifyObserver();
	}

	@Override
	public void setCurrentAction(ActionType actionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ActionType getCurrentAction() {
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

}

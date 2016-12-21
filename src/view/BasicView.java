package view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.Game;

public class BasicView extends MyObservable implements View {
	
	private JFrame frame;
	private GamePanel gamePanel;
	
	public BasicView(int x, int y, Game game, int scale) {
		frame = createBasicView(x, y, game, scale);
		game.registerObserver(this);
	}
	
	private JFrame createBasicView(int x, int y, Game game, int scale) {
		frame = new JFrame("Game");
		gamePanel = new GamePanel(game, scale);
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

}

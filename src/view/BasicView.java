package view;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import game.Game;

public class BasicView extends MyObservable implements Renderer {
	private JFrame frame;
	
	public BasicView(int x, int y, Game game, int scale) {
		frame = createBasicView(x,y,game,scale);
		game.registerObserver(this);
	}
	
	private JFrame createBasicView(int x,int y,Game game, int scale) {
		GamePanel gamePanel;	
		JFrame frame = new JFrame("Snake");
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

	public void render(ChangeGraphics cg) {
		
	}

}

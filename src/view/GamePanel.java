package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.List;

import javax.swing.JComponent;

import component.Coordinate;
import game.Game;

public class GamePanel extends JComponent implements MyObserver{
	
	private BufferedImage img;
	
	private EnumMap<Type,Color> color;

	private int scale;

	public GamePanel(Game game, int scale) {
		this.scale = scale;
		int width = game.getWidth() * scale;
		int height = game.getHeight() * scale;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(new Dimension(width, height));
		color = new EnumMap<>(Type.class);
		color.put(Type.SIMPLE_DESTRUCTIBLE, Color.LIGHT_GRAY);
		color.put(Type.SIMPLE_INDESTRUCTIBLE, Color.BLACK);
		color.put(Type.VOID, Color.WHITE);
		color.put(Type.LAVA, Color.RED);
		color.put(Type.BOMB, Color.ORANGE);
		color.put(Type.TP, Color.BLUE);
		color.put(Type.START, Color.GREEN);
		color.put(Type.END, Color.GREEN);
		color.put(Type.AGAIN, Color.YELLOW);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

	@Override
	public void update(List<? extends AbsChange> changes) {
		Graphics g = img.getGraphics();
		
		Coordinate cd;
		List<Type> types;
		for(AbsChange c:changes) {
			cd = c.getCoordinate();
			types = ( (ChangeGraphics) c).getChangeType();
			
			for(Type t: types) {
				g.setColor(color.get(t));
				g.fillRect(cd.getY() * scale, cd.getX() * scale, scale, scale);
			}
			
			
		}
		
		//g.setColor(Color.BLUE);
		//g.fillRect(0, scale, scale, scale);
		
		g.dispose();
		repaint();
		
	}

}

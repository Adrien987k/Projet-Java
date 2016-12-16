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
				//TODO ajouter un truc avec scale
				g.fillRect(cd.getX(), cd.getY(), cd.getX(), cd.getY());
			}
		}
		
		g.dispose();
		repaint();
		
	}

}

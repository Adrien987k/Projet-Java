package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.List;

import javax.swing.JComponent;

import component.Coordinate;
import game.Game;

public class GamePanel extends JComponent implements MyObserver, Renderer, MouseListener {
	
	private static final long serialVersionUID = 1L;

	private BufferedImage img;
	private Graphics g;
	
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
		color.put(Type.WALKER, Color.CYAN);
		addMouseListener(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<? extends AbsChange> changes) {
		render((List<ChangeGraphics>) changes);
	}
	
	public void render(List<ChangeGraphics> changes){
		g = img.getGraphics();
		Coordinate cd;
		List<Type> types;
		for(ChangeGraphics c : changes) {
			cd = c.getCoordinate();
			types = c.getChangeType();
			
			for(Type t : types) {
				g.setColor(color.get(t));
				g.fillRect(cd.getY() * scale, cd.getX() * scale, scale, scale);
			}
		}
		g.dispose();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("x: " + e.getX()/scale + " y: "+ e.getY()/scale);
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}

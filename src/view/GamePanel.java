package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JComponent;

import States.State;
import component.Coordinate;
import game.Game;

public class GamePanel extends JComponent implements MyObserver, Renderer, MouseListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;

	private BufferedImage img;
	private Graphics g;
	private AllView view;
	private Point cursorPoint;
	private int scale;
	
	private int width;
	private int height;
	private boolean init = true;

	public GamePanel(Game game, int scale, AllView view) {
		this.view = view;
		this.scale = scale;
		width = game.getWidth() * scale;
		height = game.getHeight() * scale;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(new Dimension(width, height));
		
		cursorPoint = new Point(0,0);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public AllView getView() {
		return view;
	}
	
	public void notInit(){
		init = false;
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.GREEN);
		g.drawRect((int) cursorPoint.getY(), (int) cursorPoint.getX(), scale, scale);
	}

	@Override
	public void update(List<Change> changes) {
		render(changes);
	}
	
	@Override
	public void render(List<Change> changes) {
		Coordinate coordinate;
		List<Type> types;
		for(Change c : changes) {
			coordinate = c.getCoordinate();
			types = c.getChangeType();
			
			for(Type type : types) {
				draw(type, coordinate);
			}
		}
	}
	
	public void draw(Type type, Coordinate coordinate){
		g = img.getGraphics();
		g.drawImage(Texture.createTexture(type.getImagePath()),
				coordinate.getY() * scale, coordinate.getX() * scale, scale, scale, null);
		
		g.dispose();
		if(!init) repaint();
	}
	
	public void drawBackground(){
		g = img.getGraphics();
		g.drawImage(Texture.createTexture("data\\img\\defaultTexturePack\\wallpaper.png"),
				0, 0 , width, height, null);

		g.dispose();
		repaint();
	}
	
	public Point getCursorPoint() {
		return cursorPoint;
	}
	
	public void refreshCursor() {
		g.setColor(Color.GREEN);
		g.drawRect((int)cursorPoint.getY(), (int)cursorPoint.getX(), scale, scale);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		State currentState = getView().getLastActionSelected().getState();
		if(currentState != null){
			if(getView().getGame().getGameMap().changeStateHere(new Coordinate(e.getY()/scale,e.getX()/scale), currentState))
				getView().getLastActionButtonSelected().decUsesLeft();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		getCursorPoint().setLocation((e.getY()/scale)*scale, (e.getX()/scale)*scale);
		repaint();
	}
	
}

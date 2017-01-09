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

/**
 * GamePanel is a component where the map is displayed.
 * The game is represented by a buffered image updated over time.
 * There is a cursor wich highlights the case pointed by the mouse.
 * @author Arnaud
 *
 */
public class GamePanel extends JComponent implements MyObserver, Renderer, MouseListener, MouseMotionListener {
	
	private static final long serialVersionUID = 1L;
	/**
	 *	The bufferedImage wich represents the map. 
	 */
	private BufferedImage img;
	/**
	 * Graphics are used as intermediary to get the graphics of the buffered image
	 */
	private Graphics g;
	/**
	 * The view it belongs to.
	 */
	private AllView view;
	/**
	 * The up left point of the cursor.
	 */
	private Point cursorPoint;
	
	/**
	 * Pixel magnification setting
	 */
	private int scale;
	
	private int width;
	private int height;
	private boolean init = true;

	/**
	 * Creates the gamePanel in the specified view with the specified scale
	 * Dimensions are readed from the game parameters.
	 * MouseListener and MouseMotionListener are linked here between this object and himself. 
	 * @param game The game do diplay.
	 * @param scale Pixel magnification setting.
	 * @param view The view it belongs to.
	 */
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
	
	/**
	 * 
	 * @return The view it belongs to.
	 */
	public AllView getView() {
		return view;
	}
	
	public void notInit(){
		init = false;
	}
		
	/**
	 * This method draws the map, then adds the mouse cursor.
	 */
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
	
	/**
	 * This method repaint every case wich has been changed recently.
	 * It's automatically called when the method update is called.
	 */
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
	
	/**
	 * Draws on the specified coordinate the image of the specified type.
	 * The image is created with the @see Texture class.
	 * Please check the documentation for more details.
	 * @param type The type of texture to display
	 * @param coordinate The target coordinate 
	 */
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
	
	/**
	 * 
	 * @return The coordinate of the cursor.
	 */
	public Point getCursorPoint() {
		return cursorPoint;
	}
	
	/**
	 * Repaint the cursor at the right coordinate.
	 * This method does not change the coordinates, it only repaint it.
	 * It's usually called just after a modification.
	 */
	public void refreshCursor() {
		g.setColor(Color.GREEN);
		g.drawRect((int)cursorPoint.getY(), (int)cursorPoint.getX(), scale, scale);
	}

	/**
	 * Try to change the state of the first lemming found in the case.
	 * If there is no lemming on the case, nothing append and the action is still selected.
	 */
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

	/**
	 * This method is used to update the cursor position.
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		getCursorPoint().setLocation((e.getY()/scale)*scale, (e.getX()/scale)*scale);
		repaint();
	}
	
}

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.List;

import javax.swing.JComponent;

import States.State;
import component.Coordinate;
import game.Game;

public class GamePanel extends JComponent implements MyObserver, Renderer, MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;

	private BufferedImage img;
	private Graphics g;
	private AllView view;
	private EnumMap<Type,Texture> texture;
	private Point cursorPoint;
	private int scale;

	public GamePanel(Game game, int scale, AllView view) {
		this.view = view;
		this.scale = scale;
		int width = game.getWidth() * scale;
		int height = game.getHeight() * scale;
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		setPreferredSize(new Dimension(width, height));
		
		texture = new EnumMap<>(Type.class);
		texture.put(Type.SIMPLE_DESTRUCTIBLE,Texture.SD);
		texture.put(Type.SIMPLE_INDESTRUCTIBLE,Texture.SI);
		texture.put(Type.AGAIN,Texture.AGAIN);
		texture.put(Type.START,Texture.START);
		texture.put(Type.END,Texture.END);
		texture.put(Type.TP,Texture.TP);
		texture.put(Type.BOMB,Texture.BOMB);
		texture.put(Type.LAVA,Texture.LAVA);
		texture.put(Type.VOID,Texture.VOID);
		texture.put(Type.WALKER,Texture.WALKER);
		texture.put(Type.BOMBER,Texture.BOMBER);
		texture.put(Type.BLOCKER,Texture.BLOCKER);
		texture.put(Type.CARPENTER,Texture.CARPENTER);
		texture.put(Type.DIGGER,Texture.DIGGER);
		texture.put(Type.TUNNELER,Texture.TUNNELER);
		texture.put(Type.PARACHUTIST,Texture.PARACHUTIST);
		texture.put(Type.CLIMBER,Texture.CLIMBER);
		
		cursorPoint = new Point(0,0);
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public AllView getView() {
		return view;
	}
		
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.GREEN);
		g.drawRect((int)cursorPoint.getY(), (int)cursorPoint.getX(), scale, scale);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(List<? extends AbsChange> changes) {
		render((List<ChangeGraphics>) changes);
	}
	
	@Override
	public void render(List<ChangeGraphics> changes) {
		g = img.getGraphics();
		Coordinate cd;
		List<Type> types;
		for(ChangeGraphics c : changes) {
			cd = c.getCoordinate();
			types = c.getChangeType();
			
			for(Type t : types) {
				g.drawImage(texture.get(t).getImage(), cd.getY() * scale, cd.getX() * scale, scale, scale,null);
			}
		}
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
		System.out.println("x: " + e.getY()/scale + " y: "+ e.getX()/scale);
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

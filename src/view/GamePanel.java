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

import States.State;
import component.Coordinate;
import game.Game;

public class GamePanel extends JComponent implements MyObserver, Renderer, MouseListener {
	
	private static final long serialVersionUID = 1L;

	private BufferedImage img;
	private Graphics g;
	private AllView view;
	private EnumMap<Type,Color> color;
	private EnumMap<Type,Texture> texture;

	private int scale;

	private Agent mouseAgent = new Agent();
	
	public GamePanel(Game game, int scale, AllView view) {
		this.view = view;
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
		color.put(Type.BLOCKER,new Color(70,130,180));
		color.put(Type.BOMBER,new Color(130,130,180));
		color.put(Type.CARPENTER, new Color(244,164,96));
		color.put(Type.CLIMBER,new Color(124,252,0));
		color.put(Type.DIGGER, new Color(210,105,42));
		color.put(Type.PARACHUTIST, new Color(0,0,128));
		color.put(Type.TUNNELER, new Color(105,105,105));
		
		texture = new EnumMap<>(Type.class);
		texture.put(Type.SIMPLE_DESTRUCTIBLE,Texture.SD);
		texture.put(Type.SIMPLE_INDESTRUCTIBLE,Texture.SI);
		texture.put(Type.AGAIN,Texture.AGAIN);
		texture.put(Type.START,Texture.START);
		addMouseListener(this);
	}

	public AllView getView() {
		return view;
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
	
	@Override
	public void render(List<ChangeGraphics> changes){
		g = img.getGraphics();
		Coordinate cd;
		List<Type> types;
		for(ChangeGraphics c : changes) {
			cd = c.getCoordinate();
			types = c.getChangeType();
			
			for(Type t : types) {
				if(t == Type.SIMPLE_DESTRUCTIBLE)
					g.drawImage(texture.get(t).getImage(), cd.getY() * scale, cd.getX() * scale, scale, scale,null);
				else if(t == Type.SIMPLE_INDESTRUCTIBLE)
					g.drawImage(texture.get(t).getImage(), cd.getY() * scale, cd.getX() * scale, scale, scale,null);
				else if(t == Type.AGAIN)
					g.drawImage(texture.get(t).getImage(), cd.getY() * scale, cd.getX() * scale, scale, scale,null);
				else if(t == Type.START)
					g.drawImage(texture.get(t).getImage(), cd.getY() * scale, cd.getX() * scale, scale, scale,null);
				else {
					g.setColor(color.get(t));
					g.fillRect(cd.getY() * scale, cd.getX() * scale, scale, scale);
				}
			}
		}
		g.dispose();
		repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("x: " + e.getY()/scale + " y: "+ e.getX()/scale);
		State currentState = getView().getCurrentAction().getState();
		if(currentState != null){
			getMouseAgent().addChangeToAgent(new ChangeStateHere(new Coordinate(e.getY()/scale,e.getX()/scale), currentState));
			getMouseAgent().notifyObserver();
		}	
		//getView().switchToDefaultAction();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
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
	
	public Agent getMouseAgent() {
		return mouseAgent;
	}
}

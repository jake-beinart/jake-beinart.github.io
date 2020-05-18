package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{
	private Handler handler;
	private GameObject player;
	private Color col;
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		
		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ID.Player) {
				player = handler.object.get(i);
			}
		}
		
		velX = 2;
		velY = 9;
		col = new Color(102,255,102);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float)Math.sqrt((x - player.getX()) * (x - player.getX()) + (y - player.getY()) * (y - player.getY()));
		velX = (int) ((-1/distance) * diffX *3);
		velY = (int) ((-1/distance) * diffY *3);

		if (y <= 0 || y >= Game.HEIGHT - 48) {
			velY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		
		handler.addObject(new Trail((int)x,(int) y, ID.Trail,col,24,24,.05f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int)x,(int) y, 24, 24);
	}
	
}

package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class BasicEnemy extends GameObject {

	private Handler handler;
	private Color col;
	Random r = new Random();
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		velX = 5;
		velY = 5;
		
		col = new Color(255,r.nextInt(100),r.nextInt(100));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if (y <= 0 || y >= Game.HEIGHT - 48) {
			velY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail,col,16,16,.1f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect(x, y, 16, 16);
	}
	
}

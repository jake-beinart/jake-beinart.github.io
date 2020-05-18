package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class SporadicEnemy extends GameObject {

	private Handler handler;
	private Color col;
	Random r = new Random();
	Boolean flagX = true;
	Boolean flagY = true;
	private int timer = 0;
	
	public SporadicEnemy(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		velX = 10;
		velY = 10;
		
		col = new Color(230 + r.nextInt(25),230 + r.nextInt(25),190);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		timer++ ;
		if (timer % 80 == 0) {
			velX = r.nextInt(20) - 8;
		}
		
		if (timer % 80 == 0) {
			velY = r.nextInt(20) - 8;
		} 

		
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

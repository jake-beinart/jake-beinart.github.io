package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

	private Handler handler;
	private Color col;
	Random r1 = new Random();
	
	Random r = new Random();
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		velX = (r.nextInt(5 - -5) + -5);
		velY = 5;
		
		col = new Color(255,r1.nextInt(100),r1.nextInt(100));
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		/*
		if (y <= 0 || y >= Game.HEIGHT - 48) {
			velY *= -1;
		}
		if (x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}*/
		
		if(y >= Game.HEIGHT) {
			handler.removeObject(this);
		}
		
		handler.addObject(new Trail(x, y, ID.Trail,col,16,16,.1f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}
	
}

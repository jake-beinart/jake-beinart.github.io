package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet2 extends GameObject {

	private Handler handler;
	Random r = new Random();
	private Color col;
	Random r1 = new Random();
	
	public EnemyBossBullet2(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		velX = (r.nextInt(5 - -5) + -5);
		velY = (r.nextInt(5 - -5) + -5);
		if (velX == 0) {
			velX = 2;
		}
		if (velY == 0) {
			velY = 2;
		}
		
		col = new Color(r1.nextInt(125),r1.nextInt(125),255);
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
		g.setColor(Color.blue);
		g.fillRect(x, y, 16, 16);
	}
	
}

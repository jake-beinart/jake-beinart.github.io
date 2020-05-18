package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss3 extends GameObject {

	private Handler handler;
	private int timer = 150;
	private int timer2 = 80;
	private int timer3 = 0;
	Random r = new Random();
	
	public EnemyBoss3(int x, int y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		velX = 0;
		velY = 2;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,64,64);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		/*
		if (timer <= 0 && timer2 > 0) {
			velY = 0;
		} else {*/
			timer--;
		//}
		
		if (timer <= 0) {
			timer2 --;
		}
		
		if (timer2 == 1) {
			velX = 2;
			velY = 2;
		}
		if (timer2 <= 0) {
			
			timer3++;
			
			if (y <= 0 || y >= Game.HEIGHT - 48) {
				velY *= -1;
			}
			
			
			
			int spawn = r.nextInt(120);
			if (spawn == 0) {
				handler.addObject(new SporadicEnemy(x + 48,y + 48,ID.SporadicEnemy,handler));
			}
		}
		

		//handler.addObject(new EnemyBossBullet(x,y,ID.BasicEnemy,handler));
		
		/*
		if (y <= 0 || y >= Game.HEIGHT - 48) {
			velY *= -1;
		} */
		if (x <= 0 || x >= Game.WIDTH - 96) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail,Color.yellow,96,96,.02f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 16, 16);
	}
	
}

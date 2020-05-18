package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss2 extends GameObject {

	private Handler handler;
	private int timer = 70;
	private int timer2 = 50;
	private int timer3 = 90;
	Random r = new Random();
	
	public EnemyBoss2(int x, int y, ID id, Handler handler) {
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
		
		if (timer <= 0) {
			velY = 0;
		} else {
			timer--;
		}
		
		if (timer <= 0) {
			timer2 --;
		}
		
		if (timer2 <= 0) {
			if (velX == 0) {
				velX = 4;
			} 
			if (velX >0) {
				velX += .01;
			} else if (velX <0) {
				velX -= .01;
			}
			timer3 --;
			//int spawn = r.nextInt(15);
			if (timer3 % 5 == 0 && timer3 > 0) {
				handler.addObject(new EnemyBossBullet2(x + 48,y + 48,ID.EnemyBossBullet2,handler));
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
		
		handler.addObject(new Trail(x, y, ID.Trail,Color.blue,96,96,.02f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(x, y, 16, 16);
	}
	
}

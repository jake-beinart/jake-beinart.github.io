package cs1302;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject {
	
	Random r = new Random();
	Handler handler;
	
	public Player(int x, int y, ID id,Handler handler) {
		super(x,y,id);
		this.handler = handler;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}

	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x,  0,  Game.WIDTH - 46);
		y = Game.clamp(y,  0,  Game.HEIGHT - 69);
		
		collision();
	}
	
	private void collision() {
		for (int i = 0; i < handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			
			if ( temp.getId() == ID.BasicEnemy || temp.getId() == ID.FastEnemy || temp.getId() == ID.SmartEnemy || temp.getId() == ID.EnemyBossBullet || temp.getId() == ID.EnemyBossBullet2) {
				if (getBounds().intersects(temp.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			if (temp.getId() == ID.SporadicEnemy) {
				if (getBounds().intersects(temp.getBounds())) {
					HUD.HEALTH -= 3;
				}
			}
			if (temp.getId() == ID.EnemyBoss || temp.getId() == ID.EnemyBoss2 || temp.getId() == ID.EnemyBoss3) {
				if (getBounds().intersects(temp.getBounds())) {
					HUD.HEALTH -= 100;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		if(id == ID.Player) g.setColor(Color.white);
		g.fillRect((int)x,(int) y, 32, 32);
	}
	
	

	
}

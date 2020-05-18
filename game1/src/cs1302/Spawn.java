package cs1302;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private int scoreKeep = 0;
	private Random r = new Random();
	
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		if (scoreKeep >= 300) {
			scoreKeep = 0;
			hud.setLevel((int) (hud.getLevel() + 1));

			if (hud.getLevel() == 2) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			} else if(hud.getLevel() == 3) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			} else if (hud.getLevel() == 4) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			} else if (hud.getLevel() == 5) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			} else if (hud.getLevel() == 6) {
				handler.clearEnemys();
				handler.addObject(new EnemyBoss((Game.WIDTH/2) - 48, -120,ID.EnemyBoss, handler));
			} else if (hud.getLevel() == 9) {
				handler.clearEnemys();
			} else if (hud.getLevel() == 10) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.SmartEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.FastEnemy, handler));
			} else if(hud.getLevel() == 12) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			} else if(hud.getLevel() == 13) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.FastEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.FastEnemy, handler));
			} else if (hud.getLevel() == 16) {
				handler.clearEnemys();
				handler.addObject(new EnemyBoss2((Game.WIDTH/2) - 48, -120,ID.EnemyBoss2, handler)); 
			} else if (hud.getLevel() == 19) {
				handler.clearEnemys();
			} else if(hud.getLevel() == 20) {
				handler.addObject(new SporadicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.SporadicEnemy, handler));
				handler.addObject(new SporadicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.SporadicEnemy, handler));
				handler.addObject(new SporadicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.SporadicEnemy, handler));
				handler.addObject(new SporadicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.SporadicEnemy, handler));
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.SmartEnemy, handler));
			} else if(hud.getLevel() == 22) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.FastEnemy, handler));
			} else if(hud.getLevel() == 24) {
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.FastEnemy, handler));
			}  else if (hud.getLevel() == 26) {
				handler.clearEnemys();
				handler.addObject(new EnemyBoss3((Game.WIDTH/2) - 48, -120,ID.EnemyBoss3, handler)); 
			} else if (hud.getLevel() == 29) {
				handler.clearEnemys();
			} 
		}
	}
}


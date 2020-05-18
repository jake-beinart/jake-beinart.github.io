package cs1302;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * This is the Game Class which builds the base of the game.
 */
public class Game extends Canvas implements Runnable {
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	
	public static boolean paused = false;
	
	public static int highscore;
	private Handler handler;
	private Random r;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	public static Shop shop;
	
	public enum STATE {
		Menu,
		Game, 
		Help,
		End,
		Shop
	};
	
	public static STATE gameState = STATE.Menu;
	
	/**
	 * This is the Game constructor.
	 */
	public Game() {
		handler = new Handler();
		hud = new HUD(100,255,0,1,0);
		shop = new Shop(this,handler, hud);
		menu = new Menu(this, handler, hud);
		KeyInput k1 = new KeyInput(handler, this);
		this.addKeyListener(k1);
		this.addMouseListener(menu);
		this.addMouseListener(shop);
		
		spawner = new Spawn(handler, hud);
		new Window(WIDTH,HEIGHT, "Survivor!",this);	
		r = new Random();
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32,HEIGHT/2-32,ID.Player,handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
		} else if (gameState == STATE.Menu){
			for (int i = 0; i < 15; i ++) {
				handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.MenuParticle, handler));
			}
		}
	}
	
	
	/**
	 * This is the start method.
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();	
		running = true;
	}
	
	/**
	 * This is the stop method.
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This is the run method.
	 */
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) { 
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; 
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) { 
				timer += 1000;
			//	System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {

		if (gameState == STATE.Game ) {
			if (!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
				if (HUD.HEALTH <= 0) {
					int cscore = (int)hud.getLevel();
					if (cscore > highscore) {
						highscore = (int)hud.getLevel();
					}
					HUD.HEALTH = 100;
					gameState = STATE.End;
					handler.clearEnemys();
					for (int i = 0; i < 15; i ++) {
						handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 50),r.nextInt(Game.HEIGHT - 50),ID.MenuParticle, handler));
					}
				}
			}
		} else if (gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help) {
			handler.tick();
			menu.tick();
		}
	}


	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.PINK);
		g.fillRect(0,0, WIDTH, HEIGHT);
		
		
		if (paused) {
			g.setColor(Color.red);
			g.drawString("PAUSED", 10, 100);
		}
		
		if (gameState == STATE.Game) {
			hud.render(g);	
			handler.render(g);
		} else if(gameState == STATE.Shop) {
			shop.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
			handler.render(g);
		} 
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if (var >= max) {
			return var = max;
		} else if (var <= min) {
			return var = min;
		} else {
			return var;
		}	
	}
	
	public static int getHighScore() {
		return highscore;
	}
	
	public static void main(String[] args) { 
	
		new Game();
		  

	}
}


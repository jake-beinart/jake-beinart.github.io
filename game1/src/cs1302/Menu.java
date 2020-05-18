package cs1302;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import cs1302.Game.STATE;

public class Menu extends MouseAdapter {
	 
	private Game game;
	private Handler handler;
	private Random r = new Random();
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if (game.gameState == STATE.Menu) {
			//play
			if (mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));		
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			}
			
			//help
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			//quit
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}
		

		//back
		if (game.gameState == STATE.Help ) {
			if (mouseOver(mx, my, 210, 350, 200,64)) {
				game.gameState = STATE.Menu;
				return;
			}
		}
		
		if (game.gameState == STATE.End ) {
			if (mouseOver(mx, my, 210, 350, 200,64)) {
				game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				Shop.B1 = 2000;
				Shop.B2 = 1000;
				Shop.B3 = 1500;
				handler.speed = 5;
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));		
				handler.clearEnemys();
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			}
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Menu;
				Shop.B1 = 2000;
				Shop.B2 = 1000;
				Shop.B3 = 1500;
				handler.speed = 5;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
		
	}
	
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} 
		}
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if (game.gameState == STATE.Menu) {
		
		Font fnt = new Font("Calibri", 1 , 60);
		Font fnt2 = new Font("Calibri", 1, 30);
		
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		((Graphics2D) g).setStroke(new java.awt.BasicStroke(4));
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Survivor", 205, 90);
		
		g.setFont(fnt2);
		g.setColor(Color.blue);
		g.drawRect(210, 150, 200, 64);
		g.setColor(Color.black);
		g.drawString("Play", 280, 190);
		
		g.setColor(Color.red);
		g.drawRect(210, 250, 200, 64);
		g.setColor(Color.black);
		g.drawString("Help", 280, 290);
		
		g.setColor(Color.white);
		g.drawRect(210, 350, 200, 64);
		g.setColor(Color.black);
		g.drawString("Quit", 280, 390);
		
		g.setFont(fnt2);
		g.setColor(Color.black);
		g.drawString("High Score : " + Game.getHighScore(), 10, 25);
	} else if (game.gameState == STATE.Help) {
		Font fnt = new Font("Calibri", 1 , 50);
		Font fnt2 = new Font("Calibri", 1, 30);
		Font fnt3 = new Font("Calibri", 1, 24);
		
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		((Graphics2D) g).setStroke(new java.awt.BasicStroke(4));
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Help", 250, 90);
		
		g.setFont(fnt3);
		g.setColor(Color.black);
		g.drawString("Use the arrow keys to move the player and dodge enemies!",25, 200);
		g.drawString("Press the P key to pause the game.",150, 220);
		
		g.setFont(fnt2);
		g.setColor(Color.red);
		g.drawRect(210, 350, 200, 64);
		g.setColor(Color.black);
		g.drawString("Back", 275, 390);
	} else if (game.gameState == STATE.End) {
		Font fnt = new Font("Calibri", 1 , 50);
		Font fnt2 = new Font("Calibri", 1, 30);
		Font fnt3 = new Font("Calibri", 3, 40);
		
		java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
		((Graphics2D) g).setStroke(new java.awt.BasicStroke(4));
		g.setFont(fnt);
		g.setColor(Color.red);
		g.drawString("GAME OVER", 190, 90);
		
		g.setFont(fnt3);
		g.setColor(Color.black);
		g.drawString("You lost at level " + (int)hud.getLevel(),160, 220);
		
		g.setFont(fnt2);
		g.setColor(Color.blue);
		g.drawRect(210, 250, 200, 64);
		g.setColor(Color.black);
		g.drawString("Main Menu", 240, 290);
		
		g.setFont(fnt2);
		g.setColor(Color.red);
		g.drawRect(210, 350, 200, 64);
		g.setColor(Color.black);
		g.drawString("Try Again?", 250, 390);
		}
	}
}

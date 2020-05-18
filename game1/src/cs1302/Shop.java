package cs1302;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import cs1302.Game.STATE;

public class Shop extends MouseAdapter {
	
	Handler handler;
	private HUD hud;
	public static int B1 = 2000;
	public static int B2 = 1000;
	public static int B3 = 1500;
	Random r = new Random();
	Game game;
	
	public Shop(Game game, Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
		this.game = game;
	}
	
	public void render(Graphics g) {
		((Graphics2D) g).setStroke(new java.awt.BasicStroke(4));
		
		g.setColor(Color.white);
		g.setFont(new Font("Calibri", 1 ,48));
		g.drawString("Game Menu", Game.WIDTH/2 - 135, 60);
		
		g.setFont(new Font("Calibri", 1 ,12));
		g.drawString("Upgrade Health", 110, 120);
		g.drawString("Cost :" + B1, 110, 140);
		g.drawRect(100, 100, 100, 80);
		
		g.setFont(new Font("Calibri", 1 ,12));
		g.drawString("Upgrade Speed", 260, 120);
		g.drawString("Cost :" + B2, 260, 140);
		g.drawRect(250, 100, 100, 80);
		
		g.setFont(new Font("Calibri", 1 ,12));
		g.drawString("Upgrade Health", 410, 120);
		g.drawString("Cost :" + B3, 410, 140);
		g.drawRect(400, 100, 100, 80);
		
		Font fnt4 = new Font("Calibri", 1, 20);
		g.setFont(fnt4);
		g.setColor(Color.white);
		g.drawString("points to spend : ", Game.WIDTH/2 - 110, 220);
		g.setColor(new Color(0,150,0));
		g.drawString("" + hud.getScore(), Game.WIDTH/2 + 50, 220);
		Font fnt5 = new Font("Calibri", 0, 17);
		g.setFont(fnt5);
		g.setColor(Color.black);
		g.drawString("press space to resume", 10, 20);
		
		Font fnt2 = new Font("Calibri", 1, 30);
		
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
	
	private boolean mouseOver(int mx, int my,int x, int y, int width, int height) {
		if (mx > x && mx < x + width) {
			if (my > y && my < y + height) {
				return true;
			} 
		}
		return false;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//box 1
		if (mx >= 100 & mx <= 200) {
			if (my >= 100 && my <= 180) {
				if (hud.getScore() >= B1) {
					hud.setScore(hud.getScore() - B1);
					B1 += 1000;
					hud.bounds += 20;
					HUD.HEALTH = (100 + (hud.bounds/2));
				}
			}
		}
		
		//box 2
		if (mx >= 250 & mx <= 350) {
			if (my >= 100 && my <= 180) {
				if (hud.getScore() >= B2) {
					hud.setScore(hud.getScore() - B2);
					B2 += 500;
					handler.speed ++;
					}
				}
			}
				
		//box 3
		if (mx >= 400 & mx <= 500) {
			if (my >= 100 && my <= 180) {
				if (hud.getScore() >= B3) {
					hud.setScore(hud.getScore() - B3);
					HUD.HEALTH = (100 + (hud.bounds/2));
					B3 += 250;
				}
			}
		}
		
		if (Game.gameState == STATE.Shop) {
			if (mouseOver(mx, my, 210, 350, 200,64)) {
				B1 = 2000;
				B2 = 1000;
				B3 = 1500;
				handler.clearEnemys();
				handler.speed = 5;
				int cscore = (int)hud.getLevel();
				if (cscore > game.highscore) {
					game.highscore = (int)hud.getLevel();
				}
				HUD.HEALTH = 100;
				Game.gameState = STATE.Game;
				hud.setLevel(1);
				hud.setScore(0);
				hud.resetBounds();
				handler.addObject(new Player(Game.WIDTH/2-32,Game.HEIGHT/2-32,ID.Player,handler));		
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH -50),r.nextInt(Game.HEIGHT -50),ID.BasicEnemy, handler));
			}
			if (mouseOver(mx, my, 210, 250, 200, 64)) {
				B1 = 2000;
				B2 = 1000;
				B3 = 1500;
				hud.resetBounds();
				handler.clearEnemys();
				handler.speed = 5;
				int cscore = (int)hud.getLevel();
				if (cscore > game.highscore) {
					game.highscore = (int)hud.getLevel();
				}
				HUD.HEALTH = 100;
				game.gameState = STATE.Menu;
				hud.setLevel(1);
				hud.setScore(0);
			}
		}
	}
}

package cs1302;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int HEALTH = 100;
	private int greenValue = 255;
	private int score = 0;
	private int level = 1;
	public int bounds = 0;
	
	public HUD (int health, int gv, int sc, int lev, int bds) {
		HEALTH = health;
		greenValue = gv;
		score = sc;
		level = lev;
		bounds = bds;
	}
	
	public void resetBounds() {
		bounds = 0;
	}
	
	public void tick() {
		
		HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds/2));
		greenValue = HEALTH * 2;
		greenValue = Game.clamp(greenValue,0, 255);
		score ++;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200 + bounds, 32);
		g.setColor(new Color(75,greenValue,0));
		g.fillRect(15, 15,HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200 + (bounds), 32);
		
		Font fnt2 = new Font("Calibri", 1 , 14);
		g.setFont(fnt2);
		g.drawString("Points: " + score, 10, 64);
		g.drawString("Level: " + level, 10, 80);
		g.setColor(Color.white);
		g.drawString(("Press Space for Menu/Upgrades"), 10, 96);
		
		if (level == 1) {
			Font fnt = new Font("Calibri", 1 , 50);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Wave 1", 225, 90);
			
		} else if(level == 9) {
			Font fnt = new Font("Calibri", 1 , 50);
			g.setFont(fnt);
			g.setColor(Color.CYAN);
			g.drawString("Wave 2", 225, 90);
		} else if(level == 19) {
			Font fnt = new Font("Calibri", 1 , 50);
			g.setFont(fnt);
			g.setColor(Color.yellow);
			g.drawString("Wave 3", 225, 90);
		} else if(level == 29) {
			Font fnt = new Font("Calibri", 1 , 50);
			g.setFont(fnt);
			g.setColor(Color.DARK_GRAY);
			g.drawString("Wave 4", 225, 90);
		}
	}
	
	public void setScore(int score ) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public float getLevel() {
		return level;
	}
	
	public void setLevel(int f) {
		this.level = f;
	}
}

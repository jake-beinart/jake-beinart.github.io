package cs1302;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import cs1302.Game.STATE;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	Game game;
	
	public KeyInput(Handler handler,Game game) {
		this.handler = handler;
		
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0;i <handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ID.Player) {
				if (key == KeyEvent.VK_UP) {
					temp.setVelY(-handler.speed);
					keyDown[0] = true;
				}
				if (key == KeyEvent.VK_DOWN) {
					temp.setVelY(handler.speed);
					keyDown[1] = true;

				}
				if (key == KeyEvent.VK_RIGHT) {
					temp.setVelX(handler.speed);
					keyDown[2] = true;

				}
				if (key == KeyEvent.VK_LEFT) {
					temp.setVelX(-handler.speed);
					keyDown[3] = true;

				}
			}
		}
		if (key == KeyEvent.VK_P) {
			if (game.gameState == STATE.Game) {
				if (Game.paused) {
					Game.paused = false;
				} else {
					Game.paused = true;
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
		
		if (key == KeyEvent.VK_SPACE) {
			if (Game.gameState == STATE.Game) {
				Game.gameState = STATE.Shop;
			} else if (Game.gameState == STATE.Shop){
				Game.gameState = STATE.Game;
			}
		}
			
		
	}
	
	public void keyTyped(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println(key);
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0;i <handler.object.size();i++) {
			GameObject temp = handler.object.get(i);
			if (temp.getId() == ID.Player) {
				if (key == KeyEvent.VK_UP) {
					keyDown[0] = false;
					//temp.setVelY(0);
				}
				if (key == KeyEvent.VK_DOWN) {
					keyDown[1] = false;
					//temp.setVelY(0);
				}
				if (key == KeyEvent.VK_RIGHT) {
					keyDown[2] = false;
					//temp.setVelX(0);
				}
				if (key == KeyEvent.VK_LEFT) {
					keyDown[3] = false;
					//temp.setVelX(0);
				}
			
				if(!keyDown[0] && !keyDown[1]) {
					temp.setVelY(0);
				}
				if(!keyDown[2] && !keyDown[3]) {
					temp.setVelX(0);
				} 
			}
		}
	}
}
				
	

package cs1302;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public int speed = 5;
	
	public void tick() {
		for (int i = 0; i < object.size();i++) {
			GameObject temp = object.get(i);
			temp.tick();
		}
	}
	
	public void render(Graphics g) {
		try {
			for (int i = 0; i < object.size(); i++) {
				if (object.get(i)!= null) {
					GameObject temp = object.get(i);
					temp.render(g);
				}
			}
		} catch (Exception e) {
			return;
		}
	}
	
	public void clearEnemys() {
		for (int i = 0; i < object.size(); i++) {
			GameObject temp = object.get(i);

			if (temp.getId() != ID.Player ) {
				object.clear();
				if (Game.gameState != Game.STATE.End && Game.gameState != Game.STATE.Shop) {
					addObject(new Player(temp.getX(),temp.getY(),ID.Player,this));
				} 
			}
		}
	}
	
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object ) {
		this.object.remove(object);
	}
	
}

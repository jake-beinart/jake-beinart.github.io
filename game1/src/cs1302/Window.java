package cs1302;

import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This is the window class to create the window of the game.
 */
public class Window extends Canvas {

	private static final long serialVersionUID = 1L;
	
	public Window(int width, int height, String title,Game game) { 	
	JFrame frame = new JFrame(title);
	
	frame.setPreferredSize(new Dimension(width, height));
	frame.setMaximumSize(new Dimension(width, height));
	frame.setMinimumSize(new Dimension(width, height));
	
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.add(game);
	frame.setVisible(true);
	game.start();
	}
}

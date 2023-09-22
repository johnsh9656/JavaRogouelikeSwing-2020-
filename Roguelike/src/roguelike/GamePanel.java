package roguelike;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

// this script is responsible for the paint method and listening for keyboard input
public class GamePanel extends JPanel {
		private Graphics2D graph2D;
		private boolean hasStarted = false;
		
		// constructor sets the panel and adds keyboard
		public GamePanel() {
			this.setPreferredSize(new Dimension(640, 512));
			
			this.setVisible(true);
			this.setFocusable(true);
			this.setLayout(null);
			
			this.addKeyListener(new Keyboard());
		}
		
		@Override
	    public void paint(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			if (!hasStarted) {
				Gameplay.startGame(g2d);
	        	hasStarted = true;
	        }
	        graph2D = g2d;
	        GameGUI.drawRoom(g2d, Gameplay.getCurrentRoom().getAllTiles());
	        GameGUI.drawPickups(g2d);
	        GameGUI.drawPlayer(g2d);
	        GameGUI.drawGoblins(g2d);
	    }
		
		private class Keyboard extends KeyAdapter {
			@Override
			public void keyReleased(KeyEvent e) {
				int key = e.getKeyCode();
				
				// checks for wasd or arrow keys to move the player
				if (Gameplay.inGame()) {
					if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
						Player.move(0, -64, graph2D);
					}
					else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
						Player.move(-64, 0, graph2D);
					}
					else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
						Player.move(0, 64, graph2D);
					}
					else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
						Player.move(64, 0, graph2D);
					}
				}
				// repaint() paints over the panel using the paint method
				repaint();
			}
		}
	
}

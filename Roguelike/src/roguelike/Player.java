package roguelike;

import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

// the Player class stores the information of the player, including
// position, movement, health, damage, score and dice
public class Player {
	private static int posX = 0;
	private static int posY = 0;
	private static int health = 50;
	private static int score = 100;
	private static int lastXDir = 1;
	private static List<Dice> dice = new ArrayList<Dice>();
	private static boolean isDead = false;
	
	// move the player if the position is available
	public static void move(int dirX, int dirY, Graphics2D g2d) {
		if (Gameplay.checkPosition(posX + dirX, posY + dirY)) {
			// change lastXDir depending on which way the player moved horizontally
			if (dirX > 0) {
				lastXDir = 1;
			} else if (dirX < 0) {
				lastXDir = -1;
			}
			
			boolean goblinInTheWay = false;
			for (Goblin g : Gameplay.getCurrentRoom().getGoblins()) {
				if (posX + dirX == g.getPosX() && posY + dirY == g.getPosY()) {
					goblinInTheWay = true;
					Gameplay.checkForFight(g);
				}
			}
			
			if (!goblinInTheWay) {
				posX += dirX;
				posY += dirY;
				GameGUI.drawPlayer(g2d);
				Gameplay.addRounds();
				Gameplay.checkIfSpecialPos(posX, posY);
				Gameplay.checkForPickup();
			}
		}
	}
	
	
	public static void addDie(Dice die) {
		dice.add(die);
	}
	
	public static List<Dice> getDice() {
		return dice;
	}
	
	public static int getDamage() {
		int damage = 0;
		for (Dice d : dice) {
			damage += d.rollDie();
		}
		return damage;
	}
	
	public static void takeDamage(int damage) {
		health -= damage;
		
		if (health <= 0) {
			Die();
		} 
	}
	
	private static void Die() {
		health = 0;
		isDead = true;
	}
	
	public static int getHealth() {
		return health;
	}
	
	public static void addToScore(int num) {
		score += num;
		GameGUI.setScoreLabel(score);
	}
	
	public static int getScore() {
		return score;
	}
	
	public static int getLastXDir() {
		return lastXDir;
	}
	
	public static void setPos(int x, int y) {
		posX = x;
		posY = y;
	}
	
	public static int getPosX() {
		return posX;
	}
	
	public static int getPosY() {
		return posY;
	}
	
	public static boolean isDead() {
		return isDead;
	}
}

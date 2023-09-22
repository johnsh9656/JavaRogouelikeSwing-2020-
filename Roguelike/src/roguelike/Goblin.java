package roguelike;

import java.awt.Graphics2D;
import java.util.Random;

// this class is responsible for each goblin
// this includes position, health, movement, damage, and health
public class Goblin {
	private int posX = 0;
	private int posY = 0;
	private int maxHealth;
	private int health = 5;
	private int maxDamage = 5;
	private int score = 0;
	private int lastXDir = 1;
	private boolean isDead;
	
	// constructor sets position, health, damage and score
	public Goblin(int x, int y) {
		posX = x;
		posY = y;
		
		Random r = new Random();
		int result = r.nextInt(6);
		maxHealth = (Gameplay.getRoomIndex() * 2) + result;
		health = maxHealth;
		maxDamage = (Gameplay.getRoomIndex() + result);
		score = maxHealth * 5;
		isDead = false;
	}
	
	// movement of the goblin is very basic
	// The movement could be improved but I am keeping it simple to avoid risking more bugs
	// that I may not have time to test, and also to avoid complicating the experience for the player
	public void move(int playerX, int playerY, Graphics2D g2d) {
		int xDif = 0, yDif = 0;
		
		if (playerY > posY && Gameplay.checkPosition(posX, posY + 64)) {
			yDif = 64;
		}
		else if (playerY < posY && Gameplay.checkPosition(posX, posY - 64)) {
			yDif = -64;
		}
		else if (playerX > posX && Gameplay.checkPosition(posX + 64, posY)) {
			xDif = 64;
			lastXDir = 1;
		}
		else if (playerX < posX && Gameplay.checkPosition(posX - 64, posY)) {
			xDif = -64;
			lastXDir = -1;
		}
		
		// checks if the goblins new position is already the position of a goblin
		for (Goblin g : Gameplay.getCurrentRoom().getGoblins()) {
			if (posX + xDif == g.getPosX() && posY + yDif == g.getPosY()) {
				xDif = 0; yDif = 0;
			}
		}
		// do not move over the player if the player is in reach
		if (Player.getPosX() == posX + xDif && Player.getPosY() == posY + yDif) {
			xDif = 0; yDif = 0;
		}
		
		posX += xDif;
		posY += yDif;
		
		GameGUI.drawGoblins(g2d);
		Gameplay.checkForFight(this);
	}
	
	public void takeDamage(int damage) {
		health -= damage;
		
		if (health <= 0) {
			Die();
		}
	}
	
	public int getDamage() {
		Random r = new Random();
		int result = r.nextInt(maxDamage) + 1;
		
		return result;
	}
	
	private void Die() {
		health = 0;
		isDead = true;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLastXDir() {
		return lastXDir;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public boolean isDead() {
		return isDead;
	}
}

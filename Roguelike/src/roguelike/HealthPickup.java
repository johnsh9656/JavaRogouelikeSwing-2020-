package roguelike;

import java.util.Random;

// the health pickup adds a random amount of health to the player between minHealing and maxHealing
public class HealthPickup {
	private int posX, posY;
	private int healing, maxHealing = 30, minHealing = 10;
	private boolean pickedUp = false;
	
	// constructor sets position and healing amount
	public HealthPickup(int x, int y) {
		posX = x;
		posY = y;
		Random r = new Random();
		healing = r.nextInt(maxHealing - minHealing + 1) + minHealing + (Gameplay.getRoomIndex() * 2);
	}
	
	public boolean pickedUp() {
		return pickedUp;
	}
	
	public void pickUp() {
		pickedUp = true;
	}
	
	public int getHealing() {
		return healing;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
}
package roguelike;

// the die pickup adds a die to the player when it is picked up
//	(when the player is at the same position as the die pickup)
public class DiePickup {
	private int posX;
	private int posY;
	private Dice die;
	private boolean pickedUp = false;
	
	// constructor sets position and creates a 6-sided die
	public DiePickup(int x, int y) {
		posX = x;
		posY = y;
		die = new Dice(6);
	}
	
	public void pickUp() {
		pickedUp = true;
	}
	
	public boolean pickedUp() {
		return pickedUp;
	}
	
	public Dice getDie() {
		return die;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
}

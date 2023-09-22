package roguelike;

import java.util.Random;

// script for one die that can be rolled
public class Dice {
	// this is a variable so dice with more sides can be added given more time
	private int sides;
	
	// constructor sets # of sides
	public Dice(int s) {
		sides = s;
	}
	
	// returns random number between 1 and the # of sides
	public int rollDie() {
		Random r = new Random();
		int result = r.nextInt(sides) + 1;
		
		return result;
	}
	
}

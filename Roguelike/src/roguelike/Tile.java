package roguelike;

import java.awt.Image;

// the Tile class stores the information for one tile
// each tile has a value which determines the image of the sprite
public class Tile {
	private int value = 0;
	private int posX = 0;
	private int posY = 0;
	private Image sprite;
	
	// constructor sets the value
	public Tile(int i) {
		value = i;
		
		// set the sprite depending on the value
		if (value == 1) {
			sprite = Textures.getFloorTile();
		} else if (value == 2) {
			sprite = Textures.getWall();
		} else if (value == 3) {
			sprite = Textures.getFloorRight();
		} else if (value == 4) {
			sprite = Textures.getDoor();
		} else if (value == 5) {
			sprite = Textures.getFloorLeft();
		} else if (value == 6) {
			sprite = Textures.getFloorDown();
		} else if (value == 7) {
			sprite = Textures.getCorner1();
		} else if (value == 8) {
			sprite = Textures.getCorner2();
		}
		else {
			sprite = Textures.getNullTile();
		}
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public int getValue() {
		return value;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public void setPosition(int x, int y) {
		posX = x;
		posY = y;
	}
}

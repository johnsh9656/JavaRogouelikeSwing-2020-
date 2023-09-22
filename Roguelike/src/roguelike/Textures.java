package roguelike;

import java.awt.Image;
import javax.swing.ImageIcon;

// the Textures class retrieves all the image files and allows all other classes to get them
public class Textures {
	private static Image playerLeft;
	private static Image playerRight;
	private static Image goblinLeft;
	private static Image goblinRight;
	private static Image floorTile;
	private static Image nullTile;
	private static Image wall;
	private static Image floorRight;
	private static Image floorLeft;
	private static Image floorDown;
	private static Image floorCorner1;
	private static Image floorCorner2;
	private static Image dieSprite;
	private static Image healthPotion;
	private static Image door;

	// retrieve Images from images folder
	public static void initTextures() {
		playerLeft = new ImageIcon("src/images/player_left.png").getImage();
		playerRight = new ImageIcon("src/images/player_right.png").getImage();
		goblinLeft = new ImageIcon("src/images/goblin_left.png").getImage();
		goblinRight = new ImageIcon("src/images/goblin_right.png").getImage();
		floorTile = new ImageIcon("src/images/floor_tile.png").getImage();
		nullTile = new ImageIcon("src/images/null_tile.png").getImage();
		wall = new ImageIcon("src/images/wall.png").getImage();
		floorRight = new ImageIcon("src/images/floor_right.png").getImage();
		floorLeft = new ImageIcon("src/images/floor_left.png").getImage();
		floorDown = new ImageIcon("src/images/floor_down.png").getImage();
		floorCorner1 = new ImageIcon("src/images/floor_bottom_left.png").getImage();
		floorCorner2 = new ImageIcon("src/images/floor_bottom_right.png").getImage();
		dieSprite = new ImageIcon("src/images/die.png").getImage();
		healthPotion = new ImageIcon("src/images/health_potion.png").getImage();
		door = new ImageIcon("src/images/door.png").getImage();
	}
	
	// get method for each Image
	public static Image getPlayerLeft() {
		return playerLeft;
	}
	public static Image getPlayerRight() {
		return playerRight;
	}
	public static Image getGoblinLeft() {
		return goblinLeft;
	}
	public static Image getGoblinRight() {
		return goblinRight;
	}
	public static Image getFloorTile() {
		return floorTile;
	}
	public static Image getFloorRight() {
		return floorRight;
	}
	public static Image getFloorLeft() {
		return floorLeft;
	}
	public static Image getFloorDown() {
		return floorDown;
	}
	public static Image getCorner1() {
		return floorCorner1;
	}
	public static Image getCorner2() {
		return floorCorner2;
	}
	public static Image getNullTile() {
		return nullTile;
	}
	public static Image getWall() {
		return wall;
	}
	public static Image getDieSprite() {
		return dieSprite;
	}
	public static Image getHealthPotion() {
		return healthPotion;
	}
	public static Image getDoor() {
		return door;
	}
	public static ImageIcon getPlayerIcon() {
		return new ImageIcon("src/images/player_large.png");
	}
	public static ImageIcon getGoblinIcon() {
		return new ImageIcon("src/images/goblin_large.png");
	}
}

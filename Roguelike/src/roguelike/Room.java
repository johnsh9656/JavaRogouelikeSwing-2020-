package roguelike;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// the Room class stores the layout and contents of a room
// each room can have one die pickup, one health pickup, 
// and as many goblins as the # of floor tiles - 8
public class Room {

	private Tile[][] allRows = new Tile[8][10];
	private List<Tile> floorTiles = new ArrayList<Tile>();
	private List<Goblin> goblins = new ArrayList<Goblin>();
	private List<Integer> takenX = new ArrayList<Integer>();
	private List<Integer> takenY = new ArrayList<Integer>();
	private DiePickup diePickup;
	private HealthPickup healthPickup;
	// values of tiles that are considered floor
	private int[] walkTiles = {1, 3, 4, 5, 6, 7, 8};
	
	public int enterPosX, enterPosY, exitPosX, exitPosY;
	
	// constructor takes in a room file and converts it to tiles, 
	// and generates goblins, die and health pickups
	public Room(File f) throws FileNotFoundException {
		// read room file and create room
		Scanner s = new Scanner(f);
		
		int yPos = 0;
		for (int i = 0; i < 8; i++) {
			int xPos = 0;
			String line = s.nextLine();
			
			for (int j = 0; j < 10; j++) {
				int num = Character.getNumericValue(line.charAt(j));
				Tile newTile = new Tile(num);
				newTile.setPosition(xPos, yPos);
				
				// checks if tile is a floor tile
				for (int l = 0; l < walkTiles.length; l++) {
					if (newTile.getValue() == walkTiles[l]) {
						floorTiles.add(newTile);
					}
				}
				
				allRows[i][j] = newTile;
				xPos += 64;
			}
			yPos += 64;
		}
		enterPosX = s.nextInt() * 64;
		enterPosY = s.nextInt() * 64;
		exitPosX = s.nextInt() * 64;
		exitPosY = s.nextInt() * 64;
		
		takenX.add(enterPosX);
		takenY.add(enterPosY);
		takenX.add(exitPosX);
		takenY.add(exitPosY);
		
		generateGoblins();
		generateDiePickup();
		generateHealthPickup();
	}
	
	private void generateGoblins() {
		int roomIndex = Gameplay.getRoomIndex();
		Random r = new Random();
		int goblinCount = r.nextInt(roomIndex + 1);
		
		for (int i = 0; i < goblinCount && i < floorTiles.toArray().length - 8; i++) {
			boolean appPos = false;
			
			int rIndex = r.nextInt(floorTiles.toArray().length);
			Tile rFloor = floorTiles.get(rIndex);
			
			while (!appPos) {
				appPos = true;
				for (int j = 0; j < takenX.toArray().length; j++) {
					if (takenX.get(j) == rFloor.getPosX() && takenY.get(j) == rFloor.getPosY()) {
						appPos = false;
					}
				}
				if (!appPos) {
					rIndex = r.nextInt(floorTiles.toArray().length);
					rFloor = floorTiles.get(rIndex);
				}
			}
			takenX.add(rFloor.getPosX());
			takenY.add(rFloor.getPosY());
			goblins.add(new Goblin(rFloor.getPosX(), rFloor.getPosY()));
		}
	}
	
	private void generateDiePickup() {
		Random r = new Random();
		int diceRan = (r.nextInt(25) + 1) + ((r.nextInt(22) + 1) * Gameplay.getRoomIndex());
		
		if (diceRan > 50) {
			int rIndex = r.nextInt(floorTiles.toArray().length);
			Tile rFloor = floorTiles.get(rIndex);
			
			boolean appPos = false;
			while (!appPos) {
				appPos = true;
				for (int j = 0; j < takenX.toArray().length; j++) {
					if (takenX.get(j) == rFloor.getPosX() && takenY.get(j) == rFloor.getPosY()) {
						appPos = false;
					}
				}
				if (!appPos) {
					rIndex = r.nextInt(floorTiles.toArray().length);
					rFloor = floorTiles.get(rIndex);
				}
			}
			takenX.add(rFloor.getPosX());
			takenY.add(rFloor.getPosY());
			diePickup = new DiePickup(rFloor.getPosX(), rFloor.getPosY());
		} else {
			diePickup = null;
		}
	}
	
	private void generateHealthPickup() {
		Random r = new Random();
		int healthRan = (r.nextInt(25) + 1) + ((r.nextInt(22) + 1) * Gameplay.getRoomIndex());
		if (healthRan > 50) {
			int rIndex = r.nextInt(floorTiles.toArray().length);
			Tile rFloor = floorTiles.get(rIndex);
			
			boolean appPos = false;
			while (!appPos) {
				appPos = true;
				for (int j = 0; j < takenX.toArray().length; j++) {
					if (takenX.get(j) == rFloor.getPosX() && takenY.get(j) == rFloor.getPosY()) {
						appPos = false;
					}
				}
				if (!appPos) {
					rIndex = r.nextInt(floorTiles.toArray().length);
					rFloor = floorTiles.get(rIndex);
				}
			}
			takenX.add(rFloor.getPosX());
			takenY.add(rFloor.getPosY());
			healthPickup = new HealthPickup(rFloor.getPosX(), rFloor.getPosY());
		} else {
			healthPickup = null;
		}
	}
	
	public Tile[][] getAllTiles() {
		return allRows;
	}
	
	public List<Tile> getFloorTiles() {
		return floorTiles;
	}
	
	public List<Goblin> getGoblins() {
		return goblins;
	}
	
	public DiePickup getDiePickup() {
		return diePickup;
	}
	
	public HealthPickup getHealthPickup() {
		return healthPickup;
	}
	
	public boolean isEnterPos(int x, int y) {
		if (x == enterPosX && y == enterPosY) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isExitPos(int x, int y) {
		if (x == exitPosX && y == exitPosY) {
			return true;
		} else {
			return false;
		}
	}
	
}

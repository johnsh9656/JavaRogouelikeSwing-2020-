package roguelike;

import java.awt.Graphics2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// the Gameplay class is the main class responsible for the gameplay loop and logic
public class Gameplay {
	private static List<Room> rooms = new ArrayList<Room>();
	private static File[] roomFiles = new File[10];
	private static Graphics2D g2d;
	
	private static int roomIndex = 0;
	private static int rounds = 0;
	
	private static boolean inGame = false;
	
	public static void startGame(Graphics2D g) {
		g2d = g;
		initRoomFiles();
		createRoom();
		Player.addDie(new Dice(6));
		Player.addDie(new Dice(6));
		inGame = true;
	}
	
	// initialize the room files
	private static void initRoomFiles() {
		roomFiles[0] = new File("src/rooms/room1");
		roomFiles[1] = new File("src/rooms/room2");
		roomFiles[2] = new File("src/rooms/room3");
		roomFiles[3] = new File("src/rooms/room4");
		roomFiles[4] = new File("src/rooms/room5");
		roomFiles[5] = new File("src/rooms/room6");
		roomFiles[6] = new File("src/rooms/room7");
		roomFiles[7] = new File("src/rooms/room8");
		roomFiles[8] = new File("src/rooms/room9");
		roomFiles[9] = new File("src/rooms/room10");
	}
	
	// creates a new room using a random room file
	private static void createRoom() {
		Random r = new Random();
		int random = r.nextInt(roomFiles.length - 0) + 0;
		try {
			Room newRoom = new Room(roomFiles[random]);
			rooms.add(newRoom);
			GameGUI.drawRoom(g2d, newRoom.getAllTiles());
			Player.setPos(newRoom.enterPosX, newRoom.enterPosY);
			GameGUI.drawPlayer(g2d);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// returns true if the given position is = to a floor tile of the current room
	public static boolean checkPosition(int x, int y) {
		List<Tile> floor = getCurrentRoom().getFloorTiles();
		boolean b = false;
		
		for (Tile t : floor) {
			if (x == t.getPosX() && y == t.getPosY()) {
				b = true;
			}
		}
		return b;
	}
	
	// checks if the given position is the enter or exit position and proceeds accordingly
	public static void checkIfSpecialPos(int x, int y) {
		// if the position = the enter position, generate the previous room
		// else if the position = the exit position, generate the next room
		if (rooms.get(roomIndex).isEnterPos(x, y)) {
			if (roomIndex != 0) {
				roomIndex--;
				GameGUI.setRoomsExploredLabel(rooms.toArray().length);
				GameGUI.setCurrentRoomLabel(roomIndex + 1);
				GameGUI.drawRoom(g2d, rooms.get(roomIndex).getAllTiles());
				Player.setPos(rooms.get(roomIndex).exitPosX, rooms.get(roomIndex).exitPosY);
				GameGUI.drawPlayer(g2d);
			}
		} else if (rooms.get(roomIndex).isExitPos(x, y)) {
			roomIndex++;
			
			// if the next room is a new room, create a room
			if (roomIndex == rooms.toArray().length) {
				createRoom();
				Player.addToScore((roomIndex + 1) * 10);
				GameGUI.setRoomsExploredLabel(rooms.toArray().length);
				GameGUI.setCurrentRoomLabel(roomIndex + 1);
			} else {
				GameGUI.setRoomsExploredLabel(rooms.toArray().length);
				GameGUI.setCurrentRoomLabel(roomIndex + 1);
				GameGUI.drawRoom(g2d, rooms.get(roomIndex).getAllTiles());
				Player.setPos(rooms.get(roomIndex).enterPosX, rooms.get(roomIndex).enterPosY);
				GameGUI.drawPlayer(g2d);
			}
		}
	}
	
	public static void addRounds() {
		rounds++;
		GameGUI.setRoundsLabel(rounds);
		moveGoblins();
	}
	
	public static void moveGoblins() {
		for (Goblin g : getCurrentRoom().getGoblins()) {
			g.move(Player.getPosX(), Player.getPosY(), g2d);
		}
	}
	
	public static void checkAllForFight() {
		for (Goblin gob : getCurrentRoom().getGoblins()) {
			checkForFight(gob);
		}
	}
	
	public static void checkForFight(Goblin g) {
		// stop fights with goblins from another room as you leave by checking that Goblin g is of the new room
		boolean rightRoom = false;
		for (Goblin gb : getCurrentRoom().getGoblins()) {
			if (gb == g) {
				rightRoom = true;
			}
		}
		
		if (rightRoom) {
			int gobX = g.getPosX();
			int gobY = g.getPosY();
			
			// start fight if the goblin is 1 tile next to the player
			boolean close1 = (Player.getPosX() - gobX == 64 && Player.getPosY() - gobY == 0);
			boolean close2 = (Player.getPosX() - gobX == -64 && Player.getPosY() - gobY == 0);
			boolean close3 = (Player.getPosX() - gobX == 0 && Player.getPosY() - gobY == 64);
			boolean close4 = (Player.getPosX() - gobX == 0 && Player.getPosY() - gobY == -64);
			
			if (close1 || close2 || close3 || close4) {
				startFight(g);
			}
		}
	}
	
	private static void startFight(Goblin g) {
		inGame = false;
		GameGUI.startFightGUI(g);
	}
	
	public static void endFight(Goblin g) {
		inGame = true;
		Player.addToScore(g.getScore());
	}
	
	public static void checkForPickup() {
		int playerX = Player.getPosX();
		int playerY = Player.getPosY();
		
		// check for die pickup
		if (getCurrentRoom().getDiePickup() != null) {
			DiePickup d = getCurrentRoom().getDiePickup();
			if (!d.pickedUp()) {
				int dieX = d.getPosX();
				int dieY = d.getPosY();
				
				if (playerX == dieX && playerY == dieY) {
					Player.addDie(d.getDie());
					getCurrentRoom().getDiePickup().pickUp();
					Player.addToScore(50);
					GameGUI.setD6Label(Player.getDice().toArray().length);
				}
			}
		}
		// check for health pickup
		if (getCurrentRoom().getHealthPickup() != null) {
			HealthPickup h = getCurrentRoom().getHealthPickup();
			if (!h.pickedUp()) {
				int pickX = h.getPosX();
				int pickY = h.getPosY();
				
				if (playerX == pickX && playerY == pickY) {
					Player.takeDamage(-h.getHealing());
					getCurrentRoom().getHealthPickup().pickUp();
					Player.addToScore(50);
					GameGUI.setHealthLabel();
				}
			}
		}
	}
	
	public static boolean inGame() {
		return inGame;
	}
	
	public static int getRoomIndex() {
		return roomIndex;
	}
	
	public static Room getCurrentRoom() {
		return rooms.get(roomIndex);
	}
	
}

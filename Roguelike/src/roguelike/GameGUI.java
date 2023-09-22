package roguelike;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// this script is responsible for the frames and their components,
// as well as drawing the room, player, enemies and pickups
public class GameGUI extends JFrame {
	private static GamePanel gamePanel;
	
	// stats frame components
	private static JFrame statsFrame;
	private static JLabel statsTitleLabel;
	private static JLabel roundsLabel;
	private static JLabel healthLabel;
	private static JLabel roomsExploredLabel;
	private static JLabel currentRoomLabel;
	private static JLabel scoreLabel;
	private static JLabel d6Label;
	private static JLabel controlsTitleLabel;
	private static JLabel controlsLabel;
	
	// fight frame components
	private static JFrame fightFrame;
	private static JLabel playerHealthLabel;
	private static JLabel enemyHealthLabel;
	private static JLabel playerImage;
	private static JLabel goblinImage;
	private static JLabel playerDamageTakenLabel;
	private static JLabel enemyDamageTakenLabel;
	private static JButton rollButton;
	
	// score frame components
	private static JFrame scoreFrame;
	private static JLabel finalScoreLabel;
	private static JButton quitButton;
	
	private static Goblin currentEnemy;
	
	// sets the frames and gamePanel
	public GameGUI() {
		gamePanel = new GamePanel();
		
		this.add(gamePanel);
		this.setIconImage(Textures.getDieSprite());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Roguelike Masterpiece");
		this.setBounds(500, 250, 640, 512);
		this.setVisible(true);
		this.pack();
		
		statsFrame = new JFrame();
		statsFrame.setIconImage(Textures.getDieSprite());
		statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		statsFrame.setResizable(false);
		statsFrame.setBounds(360, 250, 150, 300);
		statsFrame.getContentPane().setBackground(Color.BLACK);
		statsFrame.setVisible(true);
		statsFrame.setLayout(null);
		
		fightFrame = new JFrame("Fight!");
		fightFrame.setIconImage(Textures.getDieSprite());
		fightFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fightFrame.setResizable(false);
		fightFrame.setBounds(1150, 401, 500, 400);
		fightFrame.setLayout(null);
		fightFrame.getContentPane().setBackground(Color.BLACK);
		fightFrame.setVisible(false);
		
		scoreFrame = new JFrame("Thanks for playing!");
		scoreFrame.setIconImage(Textures.getDieSprite());
		scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scoreFrame.setResizable(false);
		scoreFrame.setBounds(525, 300, 350, 200);
		scoreFrame.setLayout(null);
		scoreFrame.getContentPane().setBackground(Color.BLACK);
		scoreFrame.setVisible(false);
		
		initGUI();
	}
	
	// initialize the GUI of the frames
	private void initGUI() {
		// stats window gui
		statsTitleLabel = new JLabel("STATS");
		statsFrame.add(statsTitleLabel);
		statsTitleLabel.setBounds(5, 5, 200, 30);
		statsTitleLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		statsTitleLabel.setForeground(Color.RED);
		statsTitleLabel.setVisible(true);
		
		roundsLabel = new JLabel("Rounds: 0");
		statsFrame.add(roundsLabel);
		roundsLabel.setBounds(5, 30, 200, 30);
		roundsLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		roundsLabel.setForeground(Color.RED);
		roundsLabel.setVisible(true);
		
		healthLabel = new JLabel("Health: 50");
		statsFrame.add(healthLabel);
		healthLabel.setBounds(5, 55, 200, 30);
		healthLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		healthLabel.setForeground(Color.RED);
		healthLabel.setVisible(true);
		
		roomsExploredLabel = new JLabel("Rooms Explored: 1");
		statsFrame.add(roomsExploredLabel);
		roomsExploredLabel.setBounds(5, 80, 200, 30);
		roomsExploredLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		roomsExploredLabel.setForeground(Color.RED);
		roomsExploredLabel.setVisible(true);
		
		currentRoomLabel = new JLabel("Current Room: 1");
		statsFrame.add(currentRoomLabel);
		currentRoomLabel.setBounds(5, 105, 200, 30);
		currentRoomLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		currentRoomLabel.setForeground(Color.RED);
		currentRoomLabel.setVisible(true);
		
		scoreLabel = new JLabel("Score: 100");
		statsFrame.add(scoreLabel);
		scoreLabel.setBounds(5, 130, 200, 30);
		scoreLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		scoreLabel.setForeground(Color.RED);
		scoreLabel.setVisible(true);
		
		d6Label = new JLabel("D6's: 2");
		statsFrame.add(d6Label);
		d6Label.setBounds(5, 155, 200, 30);
		d6Label.setFont(new Font("Verdana", Font.ITALIC, 12));
		d6Label.setForeground(Color.RED);
		d6Label.setVisible(true);
		
		controlsTitleLabel = new JLabel("CONTROLS");
		statsFrame.add(controlsTitleLabel);
		controlsTitleLabel.setBounds(5, 190, 200, 30);
		controlsTitleLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		controlsTitleLabel.setForeground(Color.RED);
		controlsTitleLabel.setVisible(true);
		
		controlsLabel = new JLabel("WASD / Arrow Keys");
		statsFrame.add(controlsLabel);
		controlsLabel.setBounds(5, 215, 200, 30);
		controlsLabel.setFont(new Font("Verdana", Font.ITALIC, 12));
		controlsLabel.setForeground(Color.RED);
		controlsLabel.setVisible(true);
		
		// fight window gui
		playerHealthLabel = new JLabel("50");
		fightFrame.add(playerHealthLabel);
		playerHealthLabel.setBounds(340, 225, 150, 40);
		playerHealthLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
		playerHealthLabel.setForeground(Color.WHITE);
		playerHealthLabel.setVisible(true);
		
		enemyHealthLabel = new JLabel("20 / 20");
		fightFrame.add(enemyHealthLabel);
		enemyHealthLabel.setBounds(100, 225, 150, 40);
		enemyHealthLabel.setFont(new Font("Verdana", Font.ITALIC, 14));
		enemyHealthLabel.setForeground(Color.WHITE);
		enemyHealthLabel.setVisible(true);
		
		playerImage = new JLabel();
		fightFrame.add(playerImage);
		playerImage.setBounds(275, 50, 192, 192);
		playerImage.setIcon(Textures.getPlayerIcon());
		playerImage.setVisible(true);
		
		goblinImage = new JLabel();
		fightFrame.add(goblinImage);
		goblinImage.setBounds(28, 50, 192, 192);
		goblinImage.setIcon(Textures.getGoblinIcon());
		goblinImage.setVisible(true);
		
		playerDamageTakenLabel = new JLabel();
		fightFrame.add(playerDamageTakenLabel);
		playerDamageTakenLabel.setBounds(325, 250, 150, 40);
		playerDamageTakenLabel.setFont(new Font("Verdana", Font.ITALIC, 11));
		playerDamageTakenLabel.setForeground(Color.RED);
		playerDamageTakenLabel.setVisible(true);
		
		enemyDamageTakenLabel = new JLabel();
		fightFrame.add(enemyDamageTakenLabel);
		enemyDamageTakenLabel.setBounds(75, 250, 150, 40);
		enemyDamageTakenLabel.setFont(new Font("Verdana", Font.ITALIC, 11));
		enemyDamageTakenLabel.setForeground(Color.RED);
		enemyDamageTakenLabel.setVisible(true);
		
		rollButton = new JButton("Roll");
		fightFrame.add(rollButton);
		rollButton.setBounds(220, 300, 60, 30);
		rollButton.setVisible(true);
		// adds button functionality
		rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// if player and enemy are alive, else if someone is dead
                if (!currentEnemy.isDead() && !Player.isDead()) {
                	rollAndDamage();
                } else {
                	fightFrame.dispose();
                	if (Player.isDead()) {
                		gameOverScreen();
                	} else {
                		playerDamageTakenLabel.setText("");
                		enemyDamageTakenLabel.setText("");
                		
                		// remove this goblin instance from the current rooms list of goblins
                		Gameplay.getCurrentRoom().getGoblins().remove(currentEnemy);
                		
                		Gameplay.endFight(currentEnemy);
                		currentEnemy = null;
                		
                		// checks for another fight before player can move
                		Gameplay.checkAllForFight();
                	}
                	repaint();
                }
            }
        });
		
		// score frame
		finalScoreLabel = new JLabel("High Score: 0");
		scoreFrame.add(finalScoreLabel);
		finalScoreLabel.setBounds(20, 20, 200, 30);
		finalScoreLabel.setFont(new Font("Verdana", Font.BOLD, 24));
		finalScoreLabel.setForeground(Color.RED);
		finalScoreLabel.setVisible(true);
		
		quitButton = new JButton("Quit");
		scoreFrame.add(quitButton);
		quitButton.setBounds(20, 100, 80, 30);
		quitButton.setVisible(true);
		// adds button functionality
		quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// close the game
                scoreFrame.dispose();
                dispose();
        		statsFrame.dispose();
            }
        });
	}
	
	// deal damage to the player and enemy
	private static void rollAndDamage() {
		// damage player
        int damageTaken = currentEnemy.getDamage();
        Player.takeDamage(damageTaken);
        
        // damage enemy
        int damageToEnemy = Player.getDamage();
        currentEnemy.takeDamage(damageToEnemy);
        
        // set health labels
		setGoblinHealthFightLabel();
		setHealthLabel();
		
		// set damage labels
		playerDamageTakenLabel.setText("You took " + damageTaken + " damage!");
		enemyDamageTakenLabel.setText("You dealt " + damageToEnemy + " damage!");
		
		if (currentEnemy.isDead()) {
			rollButton.setText("OK");
		}
	}
	
	public static void setRoundsLabel(int num) {
		roundsLabel.setText("Rounds: " + Integer.toString(num));
	}
	
	public static void setRoomsExploredLabel(int num) {
		roomsExploredLabel.setText("Rooms Explored: " + Integer.toString(num));
	}
	
	public static void setCurrentRoomLabel(int num) {
		currentRoomLabel.setText("Current Room: " + Integer.toString(num));
	}
	
	public static void startFightGUI(Goblin g) {
		fightFrame.setVisible(true);
		currentEnemy = g;
		setPlayerHealthFightLabel();
		setGoblinHealthFightLabel();
	}
	
	private static void setPlayerHealthFightLabel() {
		playerHealthLabel.setText(""+Player.getHealth());
	}
	
	private static void setGoblinHealthFightLabel() {
		enemyHealthLabel.setText(currentEnemy.getHealth() + " / " + currentEnemy.getMaxHealth());
	}
	
	public static void setHealthLabel() {
		healthLabel.setText("Health: " + Player.getHealth());
		setPlayerHealthFightLabel();
	}
	
	public static void setScoreLabel(int score) {
		scoreLabel.setText("Score: " + score);
	}
	
	public static void setD6Label(int num) {
		d6Label.setText("D6's: " + num);
	}
	
	private void gameOverScreen() {
		finalScoreLabel.setText("Score: " + Player.getScore());
		scoreFrame.setVisible(true);
	}
	
	// draw the current room using g2d tile by tile
	public static void drawRoom(Graphics2D g2d, Tile[][] tiles) {
		// for every tile in a column, draw each tile in its row
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 10; j++) {
				g2d.drawImage(tiles[i][j].getSprite(), tiles[i][j].getPosX(), tiles[i][j].getPosY(), gamePanel);
			}
		}
	}
	
	// draw the player using g2d facing in the correct horizontal direction
	public static void drawPlayer(Graphics2D g2d) {
		int x = Player.getPosX();
		int y = Player.getPosY();
		
		// checks if player last moved in the + or - x direction and assigns correct image
		Image img;
		if (Player.getLastXDir() == 1) {
			img = Textures.getPlayerRight();
		} else {
			img = Textures.getPlayerLeft();
		}
		
		g2d.drawImage(img, x, y, gamePanel);
	}
	
	// draw each goblin in the current room using g2d facing in the correct horizontal direction
	public static void drawGoblins(Graphics2D g2d) {
		// cycles through each goblin in the current room
		for (Goblin g : Gameplay.getCurrentRoom().getGoblins()) {
			int x = g.getPosX();
			int y = g.getPosY();
			
			// checks if the goblin last moved in the + or - x direction and assigns correct image
			Image img;
			if (g.getLastXDir() == 1) {
				img = Textures.getGoblinRight();
			} else {
				img = Textures.getGoblinLeft();
			}
			
			g2d.drawImage(img, x, y, gamePanel);
		}
	}
	
	// draw the die and health pickup if the current room has them
	public static void drawPickups(Graphics2D g2d) {
		if (Gameplay.getCurrentRoom().getDiePickup() != null) {
			DiePickup d = Gameplay.getCurrentRoom().getDiePickup();
			// only draw if die has not been picked up
			if (!d.pickedUp()) {
				int x = d.getPosX();
				int y = d.getPosY();
				g2d.drawImage(Textures.getDieSprite(), x, y, gamePanel);
			}
		}
		if (Gameplay.getCurrentRoom().getHealthPickup() != null) {
			HealthPickup h = Gameplay.getCurrentRoom().getHealthPickup();
			// only draw if health pickup has not been picked up
			if (!h.pickedUp()) {
				int x = h.getPosX();
				int y = h.getPosY();
				g2d.drawImage(Textures.getHealthPotion(), x, y, gamePanel);
			}
		}
	}
}

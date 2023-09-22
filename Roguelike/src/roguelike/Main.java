package roguelike;

// the Main class starts the game by initializing the textures and creating a new instance of GameGUI
public class Main {
	public static void main(String[] args) {
		Textures.initTextures();
		new GameGUI();
	}
}

package IOOuterActive;

import IOOuterActive.controller.GameController;
import IOOuterActive.game.Game;

import java.io.IOException;
import java.util.Map;

public class
Main {
    public static void main(String[] args) throws IOException {

        GameController controller = new GameController();
        controller.playGame();

    }
}

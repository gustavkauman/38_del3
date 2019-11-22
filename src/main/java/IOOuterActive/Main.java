package IOOuterActive;

import IOOuterActive.controller.GameController;
import IOOuterActive.game.Game;

import java.io.IOException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        GameController controller = new GameController();
        controller.playGame();

        /*
        DiceCup dg = new DiceCup(6,2);
        System.out.println(dg.getSum());
        dg.throwDice();
        System.out.println(dg.getSum());


        Language lang = new Language();
        try {
            lang.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> text = lang.getOutput();
        System.out.println(text.get("TestKey"));
        System.out.println(text.get("NyLinje"));
        System.out.println(text.get("Spillehallen"));







        Game game = new Game();
        game.playGame();
         */
    }
}

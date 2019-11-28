package IOOuterActive.controller;

import IOOuterActive.entities.Field;
import IOOuterActive.entities.GameBoard;
import IOOuterActive.entities.Player;
import IOOuterActive.entities.PropertyField;
import IOOuterActive.game.Game;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class R11 {

    @Test
    public void testerR11_1 () throws IOException {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();

        p1.addMoney(1000);

        Player[] arrP = new Player[] {p1, p2, p3, p4};

        GameBoard gb = new GameBoard();
        Player actual = Game.getWinner(arrP, gb);

        assertEquals(p1, actual);
    }

    @Test
    public void testerR11_2 () throws IOException {
        Player p1 = new Player();
        Player p2 = new Player();
        Player p3 = new Player();
        Player p4 = new Player();

        p1.addMoney(1000);

        Player[] arrP = new Player[] {p1, p2, p3, p4};

        GameBoard gb = new GameBoard();
        Player actual = Game.getWinner(arrP, gb);

        Field[] fArr = gb.getFields();
        p1.purchaseField((PropertyField) fArr[1], 1000);

        assertEquals(p1, actual);
    }
}

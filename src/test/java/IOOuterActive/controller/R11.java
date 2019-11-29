package IOOuterActive.controller;

import IOOuterActive.entities.Field;
import IOOuterActive.entities.GameBoard;
import IOOuterActive.entities.Player;
import IOOuterActive.entities.PropertyField;
import IOOuterActive.game.Game;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing the R11. The test consists of two parts. The first part tests who the winner
 * in the case that a player has more money than the rest of the players.
 *
 * The second part test who the player is when two players have the same amount of money
 * but one of them has more properties.
 *
 * @author Moamal Abdul-Mahdi
 * @Version 1.0
 */

public class R11 {
    /**
     * First part
     */
    @Test
    public void testerR11_1 () {
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

    /**
     * Second part
     */

    @Test
    public void testerR11_2 () {
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

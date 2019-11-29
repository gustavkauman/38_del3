package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    /**
     * Testing the Player class. Only two methods are being tested on the basis of Getter and Setter
     * methods test worth.
     *
     * @author Moamal Abdul-Mahdi
     * @Version 1.0
     */


    /**
     * Testing the methods that modifies the amount of money a player have
     */
    @Test
    public void addMoney() {
        Player p = new Player();
        p.setMoney(2);
        p.addMoney(2);
        assertEquals(4, p.getMoney());
    }

    /**
     * Testing the method that disqualifies a player if his/her money is below 0
     */
    @Test
    public void isFallit() {
        Player p = new Player();
        p.setMoney(0);
        p.addMoney(-1);
        Boolean d = p.isFallit();
        Assert.assertEquals(true, d);
    }
    /**
     * Testing the method a player uses to purchase a property
     */
    @Test
    public void purchaseField() {
        Player p = new Player();
        GameBoard gameBoard = new GameBoard();
        PropertyField f = (PropertyField) gameBoard.getFields()[4];

        p.setMoney(1000);
        p.getCurrentField();

        p.purchaseField(f,500);

        int actual = p.getMoney();

        Assert.assertEquals(500,actual);
    }
    /**
     * Testing the method for paying and receiving rent. It tests player's balance after paying rent, and
     * another player's balance after receiving rent.
     */
    @Test
    public void payRent() {
        Player p1 = new Player();
        Player p2 = new Player();

        GameBoard gameBoard = new GameBoard();
        PropertyField f = (PropertyField) gameBoard.getFields()[4];

        p1.setMoney(1000);
        p2.setMoney(1000);

        f.setOwner(p2);
        p1.payRent(f, 2);

        int actual1 = p1.getMoney();
        int actual2 = p2.getMoney();

        Assert.assertEquals(998, actual1);
        Assert.assertEquals(1002, actual2);
    }
}
package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    /**
     * Testing the Player class. Only two methods are being tested on the basis og Getter and Setter
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
     * Testing the method that disqualifies a player if his/her money is 0 or below
     */

    @Test
    public void isFallit() {
        Player p = new Player();
        p.setMoney(0);
        p.addMoney(-1);
        Assert.assertTrue(p.isFallit()==true);
    }
}
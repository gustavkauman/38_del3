package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void addMoney() {
        Player p = new Player();
        p.setMoney(2);
        p.addMoney(2);
        assertEquals(4, p.getMoney());
    }

    @Test
    public void isFallit() {
        Player p = new Player();
        p.setMoney(0);
        p.addMoney(-1);
        Assert.assertTrue(p.isFallit()==true);
    }
}
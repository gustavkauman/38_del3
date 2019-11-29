package IOOuterActive.game;

import IOOuterActive.entities.Player;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TC03 {

    @Test
    public void r05_check_two_moves() {
        Player player = new Player();
        player.setCurrentField(20);
        Game.checkStartPassed(7, player);
        Game.checkStartPassed(5, player);
        int actual = player.getCurrentField();
        int expected = 8;
        Assert.assertEquals(expected, actual);
    }

}
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



    @Test
    public void r14_check_if_same_position_after_1_full_board_rotation() {
        Player player = new Player();
        player.setCurrentField(20);
        player.setMoney(10);
        Game.checkStartPassed(24, player);
        int actual = player.getCurrentField();
        int expected = 20;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void r14_check_same_position_after_1000_full_board_rotations() {
        Player player = new Player();
        player.setCurrentField(20);
        for (int i = 0; i <= 1000; i++) {
            Game.checkStartPassed(24, player);
        }
        int actual = player.getCurrentField();
        int expected = 20;
        Assert.assertEquals(expected, actual);
    }


}
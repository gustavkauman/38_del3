package IOOuterActive.controller;

import IOOuterActive.entities.Player;
import IOOuterActive.game.Game;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class TC02 {

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
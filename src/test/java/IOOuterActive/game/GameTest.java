package IOOuterActive.game;

import IOOuterActive.entities.Player;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class GameTest {

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
    public void r06_check_start_passed() {
        Player player = new Player();
        player.setCurrentField(20);
        boolean actual = Game.checkStartPassed(4, player);
        boolean expected = true;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void r06_check_money_on_start() {
        Player player = new Player();
        player.setCurrentField(20);
        player.setMoney(10);
        Game.checkStartPassed(4, player);
        int actual = player.getMoney();
        int expected = 12;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void r06_check_money_passing_start_1000_times() {
        Player player = new Player();
        player.setCurrentField(20);
        player.setMoney(10);
        for (int i = 0; i <= 1000; i++) {
            Game.checkStartPassed(24, player);
        }
        int actual = player.getMoney();
        int expected = 2012;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void r06_check_no_money_not_on_or_passing_start() {
        Player player = new Player();
        player.setCurrentField(20);
        player.setMoney(10);
        Game.checkStartPassed(3, player);
        int actual = player.getMoney();
        int expected = 10;
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
package IOOuterActive.game;

import IOOuterActive.entities.Field;
import IOOuterActive.entities.GameBoard;
import IOOuterActive.entities.Player;
import IOOuterActive.entities.PropertyField;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

/**
 * Tests the functional requirement R01
 *
 * @author David Mikkelsen
 */
public class TC01 {


    /**
     * Tests whether a player is able to move around all the 23 fields of the gameboard and if the current field
     * corresponds to the actual field on the gameboard
     */
    @Test
    public void TestMoveBoard(){

        Player player = new Player();
        GameBoard board = new GameBoard();

        for(int i = 0; i < 24; i++){

            player.setCurrentField(i);
            int actual = player.getCurrentField();
            try {
                PropertyField d = (PropertyField) board.getFields()[i];
                Assert.assertEquals(i, d.getId());
            } catch (ClassCastException e) {

            }


        }
    }

    /**
     * Tests whether a player is able to purchase a property-field on the gameboard
     */
    @Test
    public void TestBuyProperty(){

        Player player = new Player();
        GameBoard board = new GameBoard();


        player.purchaseField((PropertyField) board.getFields()[4],1);
        PropertyField[] actual = board.getFieldsOwnedByPlayer(player);
        PropertyField[] expected = {(PropertyField)board.getFields()[4]};
        Assert.assertArrayEquals(expected,actual);


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








}

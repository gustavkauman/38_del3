package IOOuterActive.game;

import IOOuterActive.entities.Field;
import IOOuterActive.entities.GameBoard;
import IOOuterActive.entities.Player;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

/**
 * Tests the functional requirement R01
 *
 * @author David Mikkelsen
 */
public class TC01 {


    /**
     * Tests whether a player is able to move around all the 23 fields of the gameboard
     */
    @Test
    public void TestMoveBoard(){

        Player player = new Player();
        GameBoard board = new GameBoard();

        for(int i = 0; i < 24; i++){

            player.setCurrentField(i);
            int actual = player.getCurrentField();
            Assert.assertEquals(i,actual);
            //todo -- Ved ikke hvordan man bruger arrayList. Vil gerne tilgå felterne, så jeg kan tjekke om spilleren lander på felterne

        }
    }

    /**
     * Tests whether a player is able to purchase a property-field on the gameboard
     */
    @Test
    public void TestBuyProperty(){

        Player player = new Player();
        GameBoard board = new GameBoard();

        player.purchaseField(); //todo -- Kunne være fedt at tilgå propertyfields i arraylisten, så jeg kan købe feltet
        board.getFieldsOwnedByPlayer(player); //todo -- igen arraylist

        //Tanken er at jeg får det felt spilleren har købt og så tjekker overens med hvad vi forventer (assertEquals)

    }








}

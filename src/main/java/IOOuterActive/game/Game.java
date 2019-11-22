package IOOuterActive.game;

import IOOuterActive.entities.Die;
import IOOuterActive.entities.GameBoard;
import IOOuterActive.entities.Player;
import IOOuterActive.entities.PropertyField;

public class Game {


    /**
     *
     * @param dices
     * @param player
     * @param gb
     * @author Gustav Utke Kauman
     */
    public static void gameLogic(Die[] dices, Player player, GameBoard gb) {

        // Check if player is in jail
        if (player.getCurrentField() == 6) {

            if (player.getJailCards() > 0) {
                player.setJailCards(player.getJailCards() - 1);
            } else {
                player.addMoney(-1);
            }

        }

        // Flyt spilleren på pladen
        int currentFieldIndex = player.getCurrentField();
        int endFieldIndex = currentFieldIndex;

        for (Die die : dices) {

            for (int i = 0; i <= die.getFaceValue(); i++) {

                if (endFieldIndex == 23) {
                    endFieldIndex = 0;
                    player.addMoney(2);
                } else {
                    endFieldIndex++;
                }

            }

        }

        player.setCurrentField(endFieldIndex);

        if ( gb.getFields()[endFieldIndex] instanceof PropertyField) {
            PropertyField field = (PropertyField) gb.getFields()[endFieldIndex];

            if ( field.getOwner() != null) {
                // Feltet er ejet af en

                if (field.getOwner() != player) {
                    // Field is owned by another player. player needs to pay rent.
                    player.addMoney( field.getPrice() * (-1) );
                    field.getOwner().addMoney(field.getPrice());
                }

            } else {
                // Field is not currently owned. player has to buy it
                player.addMoney( field.getPrice() * (-1) );
                field.setOwner(player);

            }

        } else if ( endFieldIndex == 18) {
            // Player has landed on "Go to jail"-field
            player.setCurrentField(6);
            player.setJailed(true);
        }

    }

    /**
     * Loops through all players and returns the winner with the highest balance
     * @param players
     * @return Player
     * @author Gustav Utke Kauman
     * @version 1.0
     */
    public static Player getWinner(Player[] players) {

        Player currentWinningPlayer = new Player();
        currentWinningPlayer.setMoney(0);
        for (Player player : players) {

            if (player.getMoney() > currentWinningPlayer.getMoney()) {
                currentWinningPlayer = player;
            }

        }

        return currentWinningPlayer;

    }


}

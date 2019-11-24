package IOOuterActive.game;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.entities.*;

public class Game {

    /**
     *
     * @param dices
     * @param player
     * @param gb
     * @author Gustav Utke Kauman
     */
    public static void gameLogic(Die[] dices, Player player, GameBoard gb, CardBundle cardBundle, Player[] players, MatadorJuniorGUI out) {

        // Check if player is in jail
        if (player.getCurrentField() == 6 && player.isJailed()) {

            if (player.getJailCards() > 0) {
                player.setJailCards(player.getJailCards() - 1);
            } else {
                player.addMoney(-1);
            }

        }

        // Begin player move
        int currentFieldIndex = player.getCurrentField();
        int endFieldIndex = currentFieldIndex;

        for (Die die : dices) {

            for (int i = 1; i <= die.getFaceValue(); i++) {

                if (endFieldIndex == 23) {
                    endFieldIndex = 0;
                    player.addMoney(2);
                } else {
                    endFieldIndex++;
                }

            }

        }

        // Move player to field
        player.setCurrentField(endFieldIndex);
        out.updateGUIGameBoard(players);
        // End player move

        if ( gb.getFields()[endFieldIndex] instanceof PropertyField) {
            PropertyField field = (PropertyField) gb.getFields()[endFieldIndex];

            if ( field.getOwner() != null) {
                // Field is currently owned by a player

                if (field.getOwner() != player) {
                    // Field is owned by another player. player needs to pay rent.

                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        player.addMoney(field.getPrice() * (-2));
                        field.getOwner().addMoney((field.getPrice() * 2));
                    } else {
                        player.addMoney( field.getPrice() * (-1) );
                        field.getOwner().addMoney(field.getPrice());
                    }

                }

            } else {
                // Field is not currently owned. player has to buy it
                out.showMessageByKey("FieldIsNotOwned");
                player.addMoney( field.getPrice() * (-1) );
                field.setOwner(player);

            }

        } else if (gb.getFields()[endFieldIndex] instanceof ChanceField) {

            drawCard(player, cardBundle, players, out);

        } else if ( endFieldIndex == 18) {
            // Player has landed on "Go to jail"-field
            player.setCurrentField(6);
            player.setJailed(true);
        }

    }

    private static void drawCard(Player currentPlayer, CardBundle cardBundle, Player[] players, MatadorJuniorGUI out){
        Card drawnCard = cardBundle.getCard();

        out.showMessage(drawnCard.getText());

        switch (drawnCard.getName()){
            case "GiveToCar":
                //Insert logic here
                players[0].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out);
                break;
            case "GiveToShip":
                players[1].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out);
                break;
            case "GiveToCat":
                if(players.length > 2)
                    players[2].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out);
                break;
            case "GiveToDog":
                if(players.length > 3)
                    players[3].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out);
                break;
            case "GoToStart":
                checkStartPassed(24 - currentPlayer.getCurrentField(), currentPlayer);
                break;
            case "Move5":
                int chosenNumberOfMoves;
                //TODO: int chosenNumberOfMoves = guiController ...
                //checkStartPassed(chosenNumberOfMoves);
                //TODO: Udfør feltets funktion
                break;
            case "OrangeFree":

                break;
            case "MoveOrCard":
                boolean move = false;
                //TODO: move = guiController getUserSelection ...
                if(move){
                    checkStartPassed(1, currentPlayer);
                    //TODO: Udfør feltets funktion
                } else {
                    drawCard(currentPlayer, cardBundle, players, out);
                }
                break;
            case "Overeating":
                currentPlayer.addMoney(-2);
                break;
            case "OrangeOrGreenFree":

                break;
            case "LightblueFree":

                break;
            case "GetOutOfJail":
                currentPlayer.setJailCards(currentPlayer.getJailCards() + 1);
                break;
            case "MoveToStrand":
                checkStartPassed(23 - currentPlayer.getCurrentField(), currentPlayer);
                //TODO: Udfør feltets funktion
                break;
            case "Birthday":
                for(Player player : players){
                    if(player != currentPlayer){
                        player.addMoney(-1);
                        currentPlayer.addMoney(1);
                    }
                }
                break;
            case "PinkOrDarkblueFree":

                break;
            case "Homework":
                currentPlayer.addMoney(2);
                break;
            case "RedFree":

                break;
            case "Skatepark":
                int movedFields;
                if(currentPlayer.getCurrentField() < 10){
                    movedFields = 10 - currentPlayer.getCurrentField();
                } else {
                    movedFields = 24 - (currentPlayer.getCurrentField() - 10);
                }
                checkStartPassed(movedFields, currentPlayer);
                //TODO: Udfør feltets funktion.
                break;
            case "LightblueOrRedFree":

                break;
            case "BrownOrYellowFree":
                break;
        }
    }

    private static void checkStartPassed(int fieldsMoved, Player currentPlayer) {
        if(currentPlayer.getCurrentField() + fieldsMoved < 24){
            currentPlayer.setCurrentField(currentPlayer.getCurrentField() + fieldsMoved);
            //TODO: Update GUI
        } else {
            System.out.println("Du passerede START og modtager $2.");
            currentPlayer.addMoney(2);
            currentPlayer.setCurrentField(currentPlayer.getCurrentField() + fieldsMoved - 24);
            //TODO: Update GUI
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

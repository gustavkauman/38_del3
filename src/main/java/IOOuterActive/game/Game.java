package IOOuterActive.game;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Game {

    /**
     * Handles what has to happen to the player when rolling given values with the Dices.
     * @param dices
     * @param player
     * @param gb
     * @author Gustav Utke Kauman
     */
    public static void gameLogic(Die[] dices, Player player, GameBoard gb, CardBundle cardBundle, Player[] players, MatadorJuniorGUI out) {

        // Check if player is in jail and if so do the associated actions
        if (player.getCurrentField() == 6 && player.isJailed()) {

            if (player.getJailCards() > 0) {
                player.setJailCards(player.getJailCards() - 1);
            } else {
                player.addMoney(-1);
            }

        }

        // Check if the player has a chance card that has to be fulfilled first and then to the associated actions
        if (player.isPlayerSpecificCard()) {

            out.showMessageByKey("PlayerHasPlayerSpecificCard");
            int currentFieldIndex = player.getCurrentField();
            int origPos = currentFieldIndex;
            boolean forceBuy = false;

            while (true) {

                currentFieldIndex++;

                // Player reached last field. Resetting index
                if (currentFieldIndex == 24)
                    currentFieldIndex = 0;

                try {

                    if (currentFieldIndex == origPos) {
                        currentFieldIndex = origPos + 1;
                        forceBuy = true;
                    }

                    PropertyField field = (PropertyField) gb.getFields()[currentFieldIndex];
                    if (field.getOwner() == null && ! forceBuy) {

                        player.purchaseField(field);
                        player.setCurrentField(currentFieldIndex);
                        break;

                    } else if (forceBuy) {

                        field.getOwner().addMoney(field.getPrice());
                        player.purchaseField(field);
                        break;

                    }

                } catch (ClassCastException e) {

                }

            }

            player.setPlayerSpecificCard(false);

        }

        // Begin player move
        int currentFieldIndex = player.getCurrentField();
        int endFieldIndex = currentFieldIndex;

        for (Die die : dices) {

            for (int i = 1; i <= die.getFaceValue(); i++) {

                if (endFieldIndex == 23) {
                    endFieldIndex = 0;
                    player.addMoney(2);
                    out.showMessageByKey("PassedStartField");
                } else {
                    endFieldIndex++;
                }

            }

        }

        // Move player to field
        player.setCurrentField(endFieldIndex);
        out.updateGUIGameBoard(players);
        // End player move

        // Handle what happens when the player lands a given field
        handleField(player, gb, cardBundle, players, out, endFieldIndex);

        // Check if player has gone "fallit"
        if (player.getMoney() < 0)
            player.setFallit(true);

    }

    /**
     * Handle the actions related with landing on a given field.
     * @param player
     * @param gb
     * @param cardBundle
     * @param players
     * @param out
     * @param endFieldIndex
     * @author Gustav Utke Kauman
     */
    private static void handleField(Player player, GameBoard gb, CardBundle cardBundle, Player[] players, MatadorJuniorGUI out, int endFieldIndex) {
        if ( gb.getFields()[endFieldIndex] instanceof PropertyField) {
            PropertyField field = (PropertyField) gb.getFields()[endFieldIndex];

            if ( field.getOwner() != null) {
                // Field is currently owned by a player

                if (field.getOwner() != player) {
                    // Field is owned by another player. player needs to pay rent.

                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        player.payRent(field, 2);
                    } else {
                        player.payRent(field, 1);
                    }

                }

            } else {
                // Field is not currently owned. player has to buy it
                out.showMessageByKey("FieldIsNotOwned");
                player.purchaseField(field);
            }

        } else if (gb.getFields()[endFieldIndex] instanceof ChanceField) {

            drawCard(player, cardBundle, players, out, gb);

        } else if ( endFieldIndex == 18) {
            // Player has landed on "Go to jail"-field
            out.showMessageByKey("LandedOnGoToJail");
            player.setCurrentField(6);
            player.setJailed(true);
        }
    }

    /**
     * Method to draw a chance card from the card bundle and then do the associated action.
     * @param currentPlayer
     * @param cardBundle
     * @param players
     * @param out
     * @param gb
     * @auther Tim Jakobsen, Gustav Utke Kauman
     */
    private static void drawCard(Player currentPlayer, CardBundle cardBundle, Player[] players, MatadorJuniorGUI out, GameBoard gb){
        Card drawnCard = cardBundle.getCard();

        out.showChanceCard(drawnCard.getText());

        switch (drawnCard.getName()){

            case "GiveToCar":
                players[0].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out, gb);
                break;
            case "GiveToShip":
                players[1].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out, gb);
                break;
            case "GiveToCat":
                if(players.length > 2)
                    players[2].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out, gb);
                break;
            case "GiveToDog":
                if(players.length > 3)
                    players[3].setPlayerSpecificCard(true);
                drawCard(currentPlayer, cardBundle, players, out, gb);
                break;
            case "GoToStart":
                if (checkStartPassed(24 - currentPlayer.getCurrentField(), currentPlayer))
                    out.showMessageByKey("PassedStartField");
                break;
            case "Move5":
                int chosenNumberOfMoves = out.getUserInteger("Vælg et antal felter at rykke frem", 1,5);
                checkStartPassed(chosenNumberOfMoves, currentPlayer);
                out.updateGUIGameBoard(players);
                handleField(currentPlayer, gb, cardBundle, players, out, currentPlayer.getCurrentField());
                break;
            case "OrangeFree":

                PropertyField field = gb.getFieldByColor(currentPlayer.getCurrentField(), "orange");

                if (field.getOwner() == null) {
                    currentPlayer.purchaseField(field, 0);
                } else if (field.getOwner() != currentPlayer) {
                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        currentPlayer.payRent(field, 2);
                    } else {
                        currentPlayer.payRent(field, 1);
                    }
                }

                currentPlayer.setCurrentField(field.getId());

                break;
            case "MoveOrCard":
                boolean move = false;
                String selection = out.waitForUserSelection("Vælg om du vil flytte et felt frem eller trække et nyt chancekort", "Flyt 1 felt", "Træk nyt kort");

                if (selection.equals("Flyt 1 felt"))
                    move = true;

                if(move){
                    if (checkStartPassed(1, currentPlayer))
                        out.showMessageByKey("PassedStartField");
                    handleField(currentPlayer, gb, cardBundle, players, out, currentPlayer.getCurrentField());
                } else {
                    drawCard(currentPlayer, cardBundle, players, out, gb);
                }
                break;
            case "Overeating":
                currentPlayer.addMoney(-2);
                break;
            case "OrangeOrGreenFree":

                field = gb.getFieldByColor(currentPlayer.getCurrentField(), "orange", "green");

                if (field.getOwner() == null) {
                    currentPlayer.purchaseField(field, 0);
                } else if (field.getOwner() != currentPlayer) {
                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        currentPlayer.payRent(field, 2);
                    } else {
                        currentPlayer.payRent(field, 1);
                    }
                }

                currentPlayer.setCurrentField(field.getId());

                break;
            case "LightblueFree":

                field = gb.getFieldByColor(currentPlayer.getCurrentField(), "lightblue");

                if (field.getOwner() == null) {
                    currentPlayer.purchaseField(field, 0);
                } else if (field.getOwner() != currentPlayer) {
                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        currentPlayer.payRent(field, 2);
                    } else {
                        currentPlayer.payRent(field, 1);
                    }
                }

                currentPlayer.setCurrentField(field.getId());

                break;
            case "GetOutOfJail":
                currentPlayer.setJailCards(currentPlayer.getJailCards() + 1);
                break;
            case "MoveToStrand":
                if (checkStartPassed(23 - currentPlayer.getCurrentField(), currentPlayer))
                    out.showMessageByKey("PassedStartField");
                handleField(currentPlayer, gb, cardBundle, players, out, currentPlayer.getCurrentField());
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

                field = gb.getFieldByColor(currentPlayer.getCurrentField(), "pink", "blue");

                if (field.getOwner() == null) {
                    currentPlayer.purchaseField(field, 0);
                } else if (field.getOwner() != currentPlayer) {
                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        currentPlayer.payRent(field, 2);
                    } else {
                        currentPlayer.payRent(field, 1);
                    }
                }

                currentPlayer.setCurrentField(field.getId());

                break;
            case "Homework":
                currentPlayer.addMoney(2);
                break;
            case "RedFree":

                field = gb.getFieldByColor(currentPlayer.getCurrentField(), "red");

                if (field.getOwner() == null) {
                    currentPlayer.purchaseField(field, 0);
                } else if (field.getOwner() != currentPlayer) {
                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        currentPlayer.payRent(field, 2);
                    } else {
                        currentPlayer.payRent(field, 1);
                    }
                }

                currentPlayer.setCurrentField(field.getId());

                break;
            case "Skatepark":
                int movedFields;
                if(currentPlayer.getCurrentField() < 10){
                    movedFields = 10 - currentPlayer.getCurrentField();
                } else {
                    movedFields = 24 - (currentPlayer.getCurrentField() - 10);
                }
                if (checkStartPassed(movedFields, currentPlayer))
                   out.showMessageByKey("PassedStartField");
                handleField(currentPlayer, gb, cardBundle, players, out, currentPlayer.getCurrentField());
                break;
            case "LightblueOrRedFree":

                field = gb.getFieldByColor(currentPlayer.getCurrentField(), "lightblue", "red");

                if (field.getOwner() == null) {
                    currentPlayer.purchaseField(field, 0);
                } else if (field.getOwner() != currentPlayer) {
                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        currentPlayer.payRent(field, 2);
                    } else {
                        currentPlayer.payRent(field, 1);
                    }
                }

                currentPlayer.setCurrentField(field.getId());

                break;
            case "BrownOrYellowFree":

                field = gb.getFieldByColor(currentPlayer.getCurrentField(), "brown", "yellow");

                if (field.getOwner() == null) {
                    currentPlayer.purchaseField(field, 0);
                } else if (field.getOwner() != currentPlayer) {
                    out.showMessageByKey("FieldIsAlreadyOwnedByAnotherPlayer");
                    if (gb.fieldsAreOwnedBySamePlayer(gb.getFieldsByColor(field.getColor()))) {
                        out.showMessageByKey("PlayerOwnsOtherFieldInSameColor");
                        currentPlayer.payRent(field, 2);
                    } else {
                        currentPlayer.payRent(field, 1);
                    }
                }

                currentPlayer.setCurrentField(field.getId());

                break;
        }
    }


    /**
     * Checks if the player has passed the start-field if moven a given no of fields.
     * @param fieldsMoved
     * @param currentPlayer
     * @return boolean
     * @author Tim Jakobsen
     */
    public static boolean checkStartPassed(int fieldsMoved, Player currentPlayer) {
        if(currentPlayer.getCurrentField() + fieldsMoved < 24){
            currentPlayer.setCurrentField(currentPlayer.getCurrentField() + fieldsMoved);
            return false;
        } else {
            currentPlayer.addMoney(2);
            currentPlayer.setCurrentField(currentPlayer.getCurrentField() + fieldsMoved - 24);
            return true;
        }
    }

    /**
     * Loops through all players and returns the winner with the highest balance or the one with most property value if more than 1 player has the highest balance.
     * @param players : Player[]
     * @param gb : GameBoard
     * @return Player
     * @author Gustav Utke Kauman
     * @version 1.1
     */
    public static Player getWinner(Player[] players, GameBoard gb) {

        List<Player> finalists = new ArrayList<Player>();
        int maxBalance = 0;
        for (Player player : players) {

            if (player.getMoney() > maxBalance) {
                finalists.clear();
                finalists.add(player);
            } else if (player.getMoney() == maxBalance) {
                finalists.add(player);
            }

        }

        if (finalists.size() == 1) {
            return finalists.get(0);
        } else {

            int maxSum = 0;
            for (Player player : finalists) {
                PropertyField[] ownedFields = gb.getFieldsOwnedByPlayer(player);

                int sum = 0;
                for (PropertyField field : ownedFields) {
                    sum += field.getPrice();
                }

                if (sum < maxSum) {
                    finalists.remove(player);
                } else {
                    maxSum = sum;
                }

            }

            return finalists.get(0);

        }

    }


}

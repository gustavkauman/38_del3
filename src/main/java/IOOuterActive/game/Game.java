package IOOuterActive.game;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.entities.*;

import java.util.ArrayList;
import java.util.List;

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

        if (player.isPlayerSpecificCard()) {

            out.showMessageByKey("PlayerHasPlayerSpecificCard");
            int currentFieldIndex = player.getCurrentField();

            while (true) {

                try {

                    PropertyField field = (PropertyField) gb.getFields()[++currentFieldIndex];
                    if (field.getOwner() == null) {

                        field.setOwner(player);
                        player.setCurrentField(currentFieldIndex);
                        break;

                    }

                } catch (ClassCastException e) {
                    currentFieldIndex++;
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

        handleField(player, gb, cardBundle, players, out, endFieldIndex);

        // Check if player has gone "fallit"
        if (player.getMoney() < 0)
            player.setFallit(true);

    }

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

                break;
            case "MoveOrCard":
                boolean move = false;
                String selection = out.waitForUserSelection("Vælg om du vil flytte et felt frem eller trække et nyt chancekort", "Flyt 1 felt", "Træk nyt kort");

                if (selection.equals("Flyt 1 felt"))
                    move = true;

                if(move){
                    if (checkStartPassed(1, currentPlayer))
                        out.showMessageByKey("PassedStartField");
                    //TODO: Udfør feltets funktion
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

                break;
        }
    }

    private static boolean checkStartPassed(int fieldsMoved, Player currentPlayer) {
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
     * Loops through all players and returns the winner with the highest balance
     * @param players
     * @return Player
     * @author Gustav Utke Kauman
     * @version 1.0
     */
    public static Player getWinner(Player[] players, GameBoard gb) {

        List<Player> finalists = new ArrayList<Player>();
        int maxBalance = 0;
        for (Player player : players) {

            if (player.getMoney() > maxBalance) {
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

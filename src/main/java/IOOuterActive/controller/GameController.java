package IOOuterActive.controller;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.Language;
import IOOuterActive.entities.*;
import gui_fields.*;
import gui_main.GUI;

import java.io.IOException;
import java.util.ArrayList;

public class GameController {

    private Language lang;
    private GameBoard gb;
    private MatadorJuniorGUI out;
    private CardBundle cardBundle = new CardBundle();
    private Player[] players;

    public GameController() throws IOException {
        this.lang = new Language();
        this.lang.readFile();
        this.gb = new GameBoard();
        this.cardBundle.createCards();

        GUI gui = createGUIBoard();
        out = new MatadorJuniorGUI(gui, lang);
    }

    public void playGame() {

        createPlayers();

        for(int i = 0; i < players.length; i++){
            System.out.println("Navn: " + players[i].getName() + "\nAlder: " + players[i].getAge() + "\n------");
        }


        out.beginGame();

    }

    private void createPlayers() {

        out.showMessage("AntalSpillere");
        int numberOfPlayers = out.getUserInteger("Indtast antal spillere", 2, 4);
        players = new Player[numberOfPlayers];

        for(int i = 0; i < players.length; i++){
            players[i] = new Player();

            out.showMessage("OpretSpillerNavn");
            String name = out.getUserString("Indtast navn");
            players[i].setName(name);

            out.showMessage("OpretSpillerAlder");
            int age = out.getUserInteger("Indtast alder", 3, 150);
            players[i].setAge(age);
        }

        Player[] sortedPlayers = new Player[players.length];

        for(int i = 0; i < players.length; i++){
            int pos = 0;
            for(int j = 0; j < players.length; j++){
                if(players[i].getAge() > players[j].getAge()){
                    pos++;
                }
            }

            sortedPlayers[pos] = players[i];

        }

        players = sortedPlayers;

    }

    /**
     * Method to create the GUI board with the correct fields
     *
     * @author Gustav Utke Kauman
     * @version 1.0
     * @return GUI
     */
    private GUI createGUIBoard() {

        Field[] fields = gb.getFields();
        GUI_Field[] guiFields = new GUI_Field[fields.length];

        for (int i = 0; i < fields.length; i++) {

            if (i == 0) {
                guiFields[i] = new GUI_Start();
            }

            if (fields[i] instanceof PropertyField) {

                PropertyField field = (PropertyField) fields[i];

                guiFields[i] = new GUI_Street();
                guiFields[i].setTitle(fields[i].getName());
                guiFields[i].setSubText(String.valueOf(field.getPrice()));

            } else if (fields[i] instanceof ChanceField) {

                guiFields[i] = new GUI_Chance();
                guiFields[i].setTitle(fields[i].getName());

            } else if (fields[i] instanceof PrisonField) {

                guiFields[i] = new GUI_Jail();
                guiFields[i].setTitle("Prison");

            } else {

                guiFields[i] = new GUI_Street();
                guiFields[i].setTitle(fields[i].getName());
                guiFields[i].setSubText("");

            }

        }

        return new GUI(guiFields);

    }

    private void drawCard(Player currentPlayer){
        Card drawnCard = cardBundle.getCard();
        System.out.println(drawnCard.getText());

        switch (drawnCard.getName()){
            case "GiveToCar":
                //Insert logic here
                players[0].setPlayerSpecificCard(true);
                drawCard(currentPlayer);
                break;
            case "GiveToShip":
                players[1].setPlayerSpecificCard(true);
                drawCard(currentPlayer);
                break;
            case "GiveToCat":
                if(players.length > 2)
                    players[2].setPlayerSpecificCard(true);
                drawCard(currentPlayer);
                break;
            case "GiveToDog":
                if(players.length > 3)
                    players[3].setPlayerSpecificCard(true);
                drawCard(currentPlayer);
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
                    drawCard(currentPlayer);
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

    private void checkStartPassed(int fieldsMoved, Player currentPlayer) {
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

}

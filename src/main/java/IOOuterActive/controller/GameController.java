package IOOuterActive.controller;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.Language;
import IOOuterActive.entities.*;
import IOOuterActive.game.Game;
import gui_fields.*;
import gui_main.GUI;

import java.awt.*;
import java.io.IOException;

public class GameController {

    private Language lang;
    private GameBoard gb;
    private MatadorJuniorGUI out;
    private CardBundle cardBundle = new CardBundle();
    private Player[] players;
    private DiceCup dc = new DiceCup(2,6);

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
        out.updateGUIGameBoard(this.players);
        out.beginGame();

        int playerIndex = 0;

        while (true) {

            Player player = players[playerIndex];

            out.waitingForShuffle();
            out.waitForUserPress();

            // Players shuffle DiceCup
            dc.throwDice();
            out.showDices(this.dc.getDices());

            // Gamelogic does its thing
            Game.gameLogic(this.dc.getDices(), player, this.gb, this.cardBundle, this.players, this.out);

            // Update GUI GameBoard
            out.updateGUIGameBoard(this.players);

            // Check if a player is "fallit", so the game needs to end
            if (player.isFallit()) {
                Player winner = Game.getWinner(this.players, this.gb);
                out.showMessage(String.format(lang.getOutputMessage("Winner"), winner.getName()));
                break;
            }

            // Update playerIndex to the next player
            playerIndex = nextPlayer(playerIndex);

        }

        out.showMessageByKey("EndGame");
        System.exit(0);

    }

    private void createPlayers() {

        out.showMessageByKey("AntalSpillere");
        int numberOfPlayers = out.getUserInteger("Indtast antal spillere", 2, 4);
        players = new Player[numberOfPlayers];

        for(int i = 0; i < players.length; i++){
            players[i] = new Player();

            out.showMessageByKey("OpretSpillerNavn");
            String name = out.getUserString("Indtast navn");
            players[i].setName(name);

            out.showMessageByKey("OpretSpillerAlder");
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

        for (Player player : players) {
            switch (players.length) {
                case 2:
                    player.setMoney(20);
                    break;
                case 3:
                    player.setMoney(18);
                    break;
                case 4:
                    player.setMoney(16);
                    break;
            }
        }

        out.createGUICars(this.players);

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
                guiFields[i].setBackGroundColor(field.getBoardColor());

            } else if (fields[i] instanceof ChanceField) {

                guiFields[i] = new GUI_Chance();
                guiFields[i].setTitle(fields[i].getName());

            } else if (fields[i] instanceof PrisonField) {

                guiFields[i] = new GUI_Jail();
                guiFields[i].setSubText(fields[i].getName());

            } else {

                guiFields[i] = new GUI_Street();
                guiFields[i].setTitle(fields[i].getName());
                guiFields[i].setSubText("");

            }

        }

        return new GUI(guiFields);

    }

    private int nextPlayer(int playerIndex) {
        return (++playerIndex % this.players.length);
    }

}

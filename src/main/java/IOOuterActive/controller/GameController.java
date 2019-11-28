package IOOuterActive.controller;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.Language;
import IOOuterActive.entities.*;
import IOOuterActive.game.Game;
import gui_fields.*;
import gui_main.GUI;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

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

    /**
     * Plays the game and handles the appropriate steps in a round.
     * @author Gustav Utke Kauman
     */
    public void playGame() {

        // Create the players and update the GUI board accordingly.
        createPlayers();
        out.updateGUIGameBoard(this.players);

        // Show begin game message
        out.beginGame();

        // Set first player
        int playerIndex = 0;

        // Loop until a player has gone "fallit"
        while (true) {

            // Get currnet player
            Player player = players[playerIndex];

            // Show messages related to getting the user to shuffle the dice cup
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

        // Show the end game message and close the application
        out.showMessageByKey("EndGame");
        System.exit(0);

    }

    /**
     * Asks questions related to generating the players, generates them and sorts them by age.
     * Then gives each player the correct amount of money corresponding to the amount of players in the game.
     * @author Tim Jakobsen, Gustav Utke Kauman
     */
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

        Arrays.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getAge() - o2.getAge();
            }
        });

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

    }

    /**
     * Creates the GUI board with the correct fields that is currently in the game.
     * @author Gustav Utke Kauman
     * @version 1.0
     * @return GUI
     */
    private GUI createGUIBoard() {

        Field[] fields = gb.getFields();
        GUI_Field[] guiFields = new GUI_Field[fields.length];

        // Loop through all fields and create them as GUI fields that can be shown in the GUI.
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

    /**
     * Calculate and return the next player that has their round
     * @param playerIndex
     * @return index of next player.
     * @author Gustav Utke Kauman, with inspiration by code created by Mads Nyborg in lectures in DTU course 02312/02314 (Fall 2019)
     */
    private int nextPlayer(int playerIndex) {
        return (++playerIndex % this.players.length);
    }

}

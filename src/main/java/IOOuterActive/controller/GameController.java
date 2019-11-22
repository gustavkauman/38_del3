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
    private Player[] players;

    public GameController() throws IOException {
        this.lang = new Language();
        this.lang.readFile();
        this.gb = new GameBoard();

        GUI gui = createGUIBoard();
        out = new MatadorJuniorGUI(gui, lang);
    }

    public void playGame() {

        createPlayers();




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

}

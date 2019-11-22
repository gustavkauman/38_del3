package IOOuterActive.controller;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.Language;
import IOOuterActive.entities.*;
import gui_fields.*;
import gui_main.GUI;

import java.io.IOException;

public class GameController {

    private Language lang;
    private GameBoard gb;
    private MatadorJuniorGUI out;

    public GameController() throws IOException {
        this.lang = new Language();
        this.lang.readFile();
        this.gb = new GameBoard();

        GUI gui = createGUIBoard();
        out = new MatadorJuniorGUI(gui, lang);
    }

    public void playGame() {

        out.beginGame();

    }

    /**
     * Method to create the GUI board with the correct fields
     *
     * @author Gustav Utke Kauman
     * @version 1.0
     * @return GUI
     */
    private GUI createGUIBoard() {

        //TODO update last class name to the appropriate one.

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

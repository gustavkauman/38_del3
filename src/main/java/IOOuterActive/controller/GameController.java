package IOOuterActive.controller;

import IOOuterActive.GUI.MatadorJuniorGUI;
import IOOuterActive.Language;
import IOOuterActive.entities.ChanceField;
import IOOuterActive.entities.Field;
import IOOuterActive.entities.PrisonField;
import IOOuterActive.entities.PropertyField;
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

        Field[] fields = gb.getFieldsInfo()?;
        GUI_Field[] guiFields = new GUI_Field[fields.length];

        for (int i = 0; i < fields.length; i++) {

            if (fields[i] instanceof PropertyField) {

                guiFields[i] = new GUI_Street();
                guiFields[i].setTitle(fields[i].getName());
                guiFields[i].setSubText(fields[i].getPrice());

            } else if (fields[i] instanceof ChanceField) {

                guiFields[i] = new GUI_Chance();
                guiFields[i].setTitle(fields[i].getName());

            } else if (fields[i] instanceof PrisonField) {

                guiFields[i] = new GUI_Jail();
                guiFields[i].setTitle("Prison");

            } else if (fields[i] instanceof Start) {

                guiFields[i] = new GUI_Start();

            }

        }

        return new GUI(guiFields);

    }

}

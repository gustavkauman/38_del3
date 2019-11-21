package IOOuterActive.GUI;

import IOOuterActive.Language;
import gui_fields.GUI_Car;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;

public class MatadorJuniorGUI {

    private GUI gui;
    private GUI_Field[] fields;
    private GUI_Player[] players;
    private GUI_Car[] cars;
    private Language lang;

    public MatadorJuniorGUI(GUI gui, Language lang) {
        this.gui = gui;
        this.fields = gui.getFields();
        this.lang = lang;
    }

    public void beginGame() {
        this.gui.showMessage(lang.getOutputMessage("beginGameMessage"));
    }

}

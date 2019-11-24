package IOOuterActive.GUI;

import IOOuterActive.Language;
import IOOuterActive.entities.Player;
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

    public void showMessage(String key){
        gui.showMessage(lang.getOutputMessage(key));
    }

    public String getUserString(String msg){
        return gui.getUserString(msg);
    }

    public int getUserInteger(String msg){
        return gui.getUserInteger(msg);
   }

    public int getUserInteger(String msg, int min, int max){
        return gui.getUserInteger(msg, min, max);
    }

    public void createGUICars(Player[] p) {
        players = new GUI_Player[p.length];
        cars = new GUI_Car[p.length];

        for (int i = 0; i < p.length; i++) {
            cars[i] = new GUI_Car();
            players[i] = new GUI_Player(p[i].getName(), p[i].getMoney(), cars[i]);
            gui.addPlayer(players[i]);
        }

        for (GUI_Player player : players) {
            fields[0].setCar(player, true);
        }

    }

    public void updateGUIGameBoard() {

    }


}

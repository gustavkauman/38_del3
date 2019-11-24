package IOOuterActive.GUI;

import IOOuterActive.Language;
import IOOuterActive.entities.Die;
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

    public void showMessageByKey(String key){
        gui.showMessage(lang.getOutputMessage(key));
    }

    public void showMessage(String text) {
        gui.showMessage(text);
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

    public void createGUICars(Player[] players) {
        this.players = new GUI_Player[players.length];
        cars = new GUI_Car[players.length];

        for (int i = 0; i < players.length; i++) {
            cars[i] = new GUI_Car();
            this.players[i] = new GUI_Player(players[i].getName(), players[i].getMoney(), cars[i]);
            gui.addPlayer(this.players[i]);
        }

        for (GUI_Player player : this.players) {
            fields[0].setCar(player, true);
        }

    }

    public void updateGUIGameBoard(Player[] players) {

        for (GUI_Field field : fields) {
            if (field != null) {
                field.removeAllCars();
            }
        }

        for (int i = 0; i < players.length; i++) {

            fields[players[i].getCurrentField()].setCar(this.players[i], true);
            this.players[i].setBalance(players[i].getMoney());

        }

    }

    public void showDices(Die[] dices) {
        gui.setDice(dices[0].getFaceValue(), dices[1].getFaceValue());
    }

    public void waitingForShuffle() {
        gui.showMessage(lang.getOutputMessage("WaitingForShuffle"));
    }

    public void waitForUserPress() {
        gui.getUserButtonPressed("","ENTER");
    }


}

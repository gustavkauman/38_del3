package IOOuterActive.entities;

public class Player {

    //Attributes
    private String name;
    private Account account = new Account();
    private boolean jailed = false, fallit = false, playerSpecificCard = false;
    private int jailCards = 0, currentField = 0;

    public int getCurrentField() {
        return currentField;
    }

    public void setCurrentField(int currentField) {
        this.currentField = currentField;
    }

    public boolean isPlayerSpecificCard() {
        return playerSpecificCard;
    }

    public void setPlayerSpecificCard(boolean playerSpecificCard) {
        this.playerSpecificCard = playerSpecificCard;
    }

    public int getJailCards() {
        return jailCards;
    }

    public void setJailCards(int jailCards) {
        this.jailCards = jailCards;
    }

    public boolean isJailed() {
        return jailed;
    }

    public void setJailed(boolean jailed) {
        this.jailed = jailed;
    }

    public boolean isFallit() {
        return fallit;
    }

    public void setFallit(boolean fallit) {
        this.fallit = fallit;
    }

    //Returns the players name
    String getName() {
        return name;
    }

    //Sets player's name via input
    public void setName(String input) {
        name = input;
    }

    //Sets players account balance to the value of the input
    public void setMoney(int amount) {
        account.setMoney(amount);
    }

    //Returns current balance of the players account
    public int getMoney() {
        return account.getMoney();
    }

    public void addMoney(int amount){
        if((account.getMoney() - amount) < 0){
            fallit = true;
            account.setMoney(0);
        } else {
            account.addMoney(amount);
        }
    }
}




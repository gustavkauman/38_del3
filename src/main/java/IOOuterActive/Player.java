package IOOuterActive;

public class Player {

    //Attributes
    private String name;
    private Account account = new Account();
    private boolean jailed = false, fallit = false;

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
    void setName(String input) {
        name = input;
    }

    //Sets players account balance to the value of the input
    void setMoney(int amount) {
        account.setMoney(amount);
    }

    //Returns current balance of the players account
    int getMoney() {
        return account.getMoney();
    }

    void addMoney(int amount){
        if((account.getMoney() - amount) < 0){
            fallit = true;
            account.setMoney(0);
        } else {
            account.addMoney(amount);
        }
    }
}




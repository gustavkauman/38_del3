package IOOuterActive;

class Account {

    private int money;

    //Returns the current balance of money
    int getMoney() {
        return money;
    }

    //Sets the balance to the value of the input
    void setMoney(int amount) {
        this.money = amount;
    }

    void addMoney(int amount){
        money += amount;
    }
}

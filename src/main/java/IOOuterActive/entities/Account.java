package IOOuterActive.entities;

class Account {

    private int money;

    /**
     * @return Returns the current balance of money
     */
    int getMoney() {
        return money;
    }

    /**
     * This code takes an integer and set the money to that.
     * @param amount in
     */

    void setMoney(int amount) {
        this.money = amount;
    }


    /**
     * This code takes an integer and adds it to the money that already exits.
     * @param amount int
     */
    void addMoney(int amount){
        money += amount;
    }
}

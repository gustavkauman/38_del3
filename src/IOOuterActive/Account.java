package IOOuterActive;

import IOOuterActive.Languages.Language;

class Account {
    private Language language = new Language();
    private int money;

    //Returns the current balance of money
    int getMoneySum() {
        return money;
    }

    //Sets the balance to the value of the input
    void setMoney(int amount) {
        this.money = amount;
    }

    //Adds the value of the input to the balance and returns a confirmation message
    String addMoney(int money) {
        this.money += money;
        language.languageOutput(17);
        return money + "$";
    }

    //Subtracts the value of the input from the balance and returns a confirmation message
    //The method also makes sure that the balance cannot be negative.
    String subtractMoney(int money) {

        if (this.money < money) {
            this.money = 0;
            language.languageOutput(18);
            return money + "$";
        } else {
            this.money -= money;
            language.languageOutput(16);
            return money + "$";
        }
    }

    //Returns a string with a message about the account balance
    int toStringBalance(int money) {
        language.languageOutput(15);
        return money;
    }

}

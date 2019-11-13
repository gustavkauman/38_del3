package IOOuterActive;

import IOOuterActive.Languages.Language;

class Player {
    private Language language = new Language();

    //Attributes
    private String name;
    private Account account = new Account();
    private boolean turn;

    boolean getTurn() {
        return turn;
    }

    void setTurn(boolean turn) {
        this.turn = turn;
    }

    //Returns the players name
    String getPName() {
        return name;
    }

    //Sets player's name via input
    void setPName(String input) {
        name = input;
    }

    //Prints a message asking the player to enter the desired name
    void toStringEnterName() {
        language.languageOutput(0);
    }

    //Sets players account balance to the value of the input
    void setPAccount(int amount) {
        account.setMoney(amount);
    }

    //Returns current balance of the players account
    int getPBalance() {

        return account.getMoneySum();
    }

    // Checks the value of the input and decides which field the player has landed on
    // The corresponding amount is either added or withdrawn from the players account
    void fieldList(int sum) {
        switch (sum) {
            case 2: {
                System.out.println("<Tower>");
                language.languageOutput(1);
                System.out.println(account.addMoney(250));         //Tower  +250
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 3: {
                System.out.println("<Crater>");
                language.languageOutput(2);
                System.out.println(account.subtractMoney(100));     //Crater   -100
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 4: {
                System.out.println("<Palace gates>");
                language.languageOutput(3);
                System.out.println(account.addMoney(100));         //Palace gates  +100
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 5: {
                System.out.println("<Cold Desert>");
                language.languageOutput(4);
                System.out.println(account.subtractMoney(20));     //Cold Desert    -20
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 6: {
                System.out.println("<Walled city>");
                language.languageOutput(5);
                System.out.println(account.addMoney(180));         //Walled city    +180
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 7: {
                System.out.println("<Monastery>");
                language.languageOutput(6);
                System.out.println(account.addMoney(0));           //Monastery    0
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 8: {
                System.out.println("<Black cave>");
                language.languageOutput(7);
                System.out.println(account.subtractMoney(70));    //Black cave    -70
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 9: {
                System.out.println("<Huts in the mountain>");
                language.languageOutput(8);
                System.out.println(account.addMoney(60));         // Huts in the mountain   +60
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 10: {
                System.out.println("<The Werewall> (Werewallwolf-wall)");
                language.languageOutput(9);        //The Werewall (werewolf-wall  -80,
                System.out.println(account.subtractMoney(80));                  // men spilleren får en ekstra tur.
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 11: {
                System.out.println("<The pit>");
                language.languageOutput(10);
                System.out.println(account.subtractMoney(50));     //The pit    -50
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
            case 12: {
                System.out.println("<Goldmine>");
                language.languageOutput(11);
                System.out.println(account.addMoney(650));        //Goldmine     +650
                System.out.println(account.toStringBalance(account.getMoneySum()) + "\n");
                break;
            }
        }
    }
}




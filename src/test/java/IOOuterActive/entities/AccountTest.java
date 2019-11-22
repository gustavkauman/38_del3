package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    void t03_check_setMoney() {
        Account account = new Account();
        account.setMoney(1000);
        int expected = 1000;
        int actual = account.getMoney();
        Assert.assertEquals("Checks whether the setMoney method sets the amount given.", expected, actual);
    }

    @Test
    void t_04_check_addMoney() {
        Account account = new Account();
        account.setMoney(1000);
        account.addMoney(500);
        int expected = 1500;
        int actual = account.getMoney();
        Assert.assertEquals("Checks whether the addMoney method adds the amount given to the balance.", expected, actual);
    }

    @Test
    void t_05_check_subtract_money() {
        Account account = new Account();
        account.setMoney(3000);
        account.addMoney(-500);
        int expected = 2500;
        int actual = account.getMoney();
        Assert.assertEquals("Checks whether the addMoney method subtracts the amount given from the balance.", expected, actual);
    }
}
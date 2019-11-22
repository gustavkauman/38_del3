package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class CardTest {

    @Test
    void t_06_check_name() {
        Card card = new Card("testCard", "This is a sample text");
        String expected = "testCard";
        String actual = card.getName();
        Assert.assertEquals("Checks whether the getName method gets the given name", expected, actual);
    }

    @Test
    void t_07_check_text() {
        Card card = new Card("testCard", "This is a sample text");
        String expected = "This is a sample text";
        String actual = card.getText();
        Assert.assertEquals("Checks whether the getText method gets the given text", expected, actual);

    }

}
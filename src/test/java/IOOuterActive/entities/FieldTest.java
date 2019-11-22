package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void check_getName() {
        Field field = new Field("Test name", 8);
        String expected= "Test name";
        String actual = field.getName();
        Assert.assertEquals("Checks whether ",expected,actual);
    }

}
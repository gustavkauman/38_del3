package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrisonFieldTest {

    @Test
    void check_imprison(){
        PrisonField prisonField = new PrisonField("test field", 8);
        prisonField.imPrison();
        Assert.assertTrue(prisonField.convicted());
    }

    @Test
    void check_release_with_money() {
        PrisonField prisonField = new PrisonField("test field", 8);
        prisonField.release(5,"card");
        Assert.assertFalse(prisonField.convicted());
    }

    @Test
    void check_release_with_card() {
        PrisonField prisonField = new PrisonField("test field", 8);
        prisonField.release(0,"Release");
        Assert.assertFalse(prisonField.convicted());

    }

}
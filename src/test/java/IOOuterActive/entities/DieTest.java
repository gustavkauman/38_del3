package IOOuterActive.entities;


import org.junit.Assert;

class DieTest {
    public DieTest() {
    }

    @org.junit.jupiter.api.Test
    void t01_check_roll() {
        Die d = new Die(6);
        int roll = d.roll();
        Assert.assertTrue(roll >= 1 && roll <= 6);
    }

    @org.junit.jupiter.api.Test
    void t02_check_statistically_correct() {
        Die d = new Die(6);
        int[] fallouts = new int[6];

        for (int i = 0; i <= 6000000; ++i) {
            int roll = d.roll();
            int var10002;
            switch (roll) {
                case 1:
                    var10002 = fallouts[0]++;
                    break;
                case 2:
                    var10002 = fallouts[1]++;
                    break;
                case 3:
                    var10002 = fallouts[2]++;
                    break;
                case 4:
                    var10002 = fallouts[3]++;
                    break;
                case 5:
                    var10002 = fallouts[4]++;
                    break;
                case 6:
                    var10002 = fallouts[5]++;
            }
        }

        int delta = 5000;
        Assert.assertEquals(1000000.0F, (float) fallouts[0], (float) delta);
        Assert.assertEquals(1000000.0F, (float) fallouts[1], (float) delta);
        Assert.assertEquals(1000000.0F, (float) fallouts[2], (float) delta);
        Assert.assertEquals(1000000.0F, (float) fallouts[3], (float) delta);
        Assert.assertEquals(1000000.0F, (float) fallouts[4], (float) delta);
        Assert.assertEquals(1000000.0F, (float) fallouts[5], (float) delta);
    }
}

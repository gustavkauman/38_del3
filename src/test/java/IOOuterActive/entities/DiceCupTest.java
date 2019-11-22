package IOOuterActive.entities;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceCupTest {

    @Test
   public void dicecup_can_throw_two_normal_dice(){
        DiceCup cup = new DiceCup(2,6);
        cup.throwDice();
        cup.getSum();
        int outcome = cup.getSum();
        assertTrue(outcome >= 2 && outcome <= 12);
    }


    @Test
    public void dicecup_can_throw_max_number_of_dice(){
        int maxFaces = 120;
        int maxDice = 15;
        DiceCup cup = new DiceCup(maxDice,maxFaces);
        cup.throwDice();
        cup.getSum();
        int outcome = cup.getSum();
        int intervalmax = (maxFaces*maxDice)-maxDice-1;
        assertTrue(outcome>=maxDice && outcome<=intervalmax);
    }

    @Test
    public void dicecup_can_throw_minimal_number_of_dice(){
        int minFaces = 2;
        int minDice = 1;
        DiceCup cup = new DiceCup(minDice,minFaces);
        cup.throwDice();
        int outcome = cup.getSum();
        assertTrue(outcome>=1 && outcome<=2);
    }

}



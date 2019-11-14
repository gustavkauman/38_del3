package IOOuterActive;

class DiceCup {

    int numberOfDice;

    public DiceCup(int x) {
        numberOfDice =x;
    }

    // Attributes
    private final Die t1;
    private final Die t2;
    private int outcome1, outcome2;

    //Contructor. Creates two die objects
    DiceCup() {
        t1 = new Die();
        t2 = new Die();
    }

    //Rolls two die objects with the roll-method form the die-class
    void throwDice() {
        outcome1 = t1.roll();
        outcome2 = t2.roll();
    }

    //Adds the two facevalues and saves the sum
    int getSum() {
        return outcome1 + outcome2;
    }
}
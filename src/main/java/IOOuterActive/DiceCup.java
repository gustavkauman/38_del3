package IOOuterActive;

class DiceCup {

    private IOOuterActive.Die[] dices;

    DiceCup(int numberOfDice, int numberOfFaces) {

        this.dices = new IOOuterActive.Die[numberOfDice];

        for (int i = 0; i<dices.length; i++) {
            dices[i] = new IOOuterActive.Die(numberOfFaces);
        }
    }

    void throwDice() {
        for (IOOuterActive.Die die : dices) {
            die.roll();
        }
     }

    int getSum() {
        int sum = 0;
        for (IOOuterActive.Die die: dices) {
            sum += die.getFaceValue();
        }
        return sum;
    }
}
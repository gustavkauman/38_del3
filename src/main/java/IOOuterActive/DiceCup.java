package IOOuterActive;

class DiceCup {

    private Die[] dices;

    DiceCup(int numberOfDice, int numberOfFaces) {

        this.dices = new Die[numberOfDice];

        for (int i = 0; i<dices.length; i++) {
            dices[i] = new Die(numberOfFaces);
        }
    }

    void throwDice() {
        for (Die die : dices) {
            die.roll();
        }
     }

    int getSum() {
        int sum = 0;
        for (Die die: dices) {
            sum += die.getFaceValue();
        }
        return sum;
    }
}
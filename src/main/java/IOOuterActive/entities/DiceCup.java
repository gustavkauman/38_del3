package IOOuterActive.entities;

public class DiceCup {

    private Die[] dices;

    public DiceCup(int numberOfDice, int numberOfFaces) {

        this.dices = new Die[numberOfDice];

        for (int i = 0; i<dices.length; i++) {
            dices[i] = new Die(numberOfFaces);
        }
    }

    public void throwDice() {
        for (Die die : dices) {
            die.roll();
        }
     }

    public Die[] getDices() {
        return this.dices;
    }

    public int getSum() {
        int sum = 0;
        for (Die die: dices) {
            sum += die.getFaceValue();
        }
        return sum;
    }
}
package IOOuterActive.entities;

public class Die {
    private int faces;
    private int faceValue;

    Die(int x){
        this.faces = x;
    }

    /**
     * @return Returns random value between 1 and x, where x is amount of sides on the die.
     */
    int roll() {
        return this.faceValue = (int) (Math.random() * faces) + 1;
    }

    /**
     * @return Returns facevalue.
     */
    public int getFaceValue() {
        return faceValue;
    }
}

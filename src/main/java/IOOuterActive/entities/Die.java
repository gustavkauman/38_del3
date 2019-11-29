package IOOuterActive.entities;

public class Die {
    private int faces;
    private int faceValue;

    Die(int x){
        this.faces = x;
    }

    /**
     * Returns random value between 1 and x, where x is amount of sides on the die.
     * @return Returns random value.
     */
    int roll() {
        return this.faceValue = (int) (Math.random() * faces) + 1;
    }

    /**
     * Gets the facevalue
     * @return Returns facevalue.
     */
    public int getFaceValue() {
        return faceValue;
    }
}

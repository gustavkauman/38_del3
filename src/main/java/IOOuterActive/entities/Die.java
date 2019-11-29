package IOOuterActive.entities;

public class Die {
    private int faces;
    private int faceValue;

    Die(int x){
        this.faces = x;
    }

    int roll() {
        return this.faceValue = (int) (Math.random() * faces) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }
}

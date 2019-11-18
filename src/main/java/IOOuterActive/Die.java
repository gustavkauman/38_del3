package main.java.IOOuterActive;

class Die {
    private int faces;
    private int faceValue;

    Die(int x){
        this.faces = x;
    }

    void roll() {
        this.faceValue = (int) (Math.random() * faces) + 1;
    }

    public int getFaceValue() {
        return faceValue;
    }
}

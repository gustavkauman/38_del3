package IOOuterActive;

class Die {
    private int faces;

    Die(int x){
        faces = x;
    }
    int roll() {
        return (int) (Math.random() * faces) + 1;
    }
}

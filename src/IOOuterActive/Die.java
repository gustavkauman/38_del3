package IOOuterActive;

class Die {
    //generates random value between 1 and 6 and returns that value
    int roll() {
        //Attributer
        // max value
        int maxval = 6;
        //current value
        return (int) (Math.random() * maxval) + 1;
    }
}

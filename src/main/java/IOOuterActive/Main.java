package IOOuterActive;

public class Main {
    public static void main(String[] args) {
        DiceCup dg = new DiceCup(6,2);
        System.out.println(dg.getSum());
        dg.throwDice();
        System.out.println(dg.getSum());
    }
}

package main.java.field;

/**
 * The StartField class (Subclass)
 *
 * @author Moamal Abdul-Mahdi
 * @Version 1.0
 */

public class StartField extends Field {

    private String fieldText;
    private boolean passed;
    private boolean StandingOn;

    public StartField(String name, int id) {
        super(name, id);
    }

    public void startingGame(boolean standingOn) {
        this.StandingOn = standingOn;
    }

    public void startPassed(boolean passed) {
        this.passed = passed;
    }

    public String getStart(String fieldText) {
        return fieldText;
    }
}

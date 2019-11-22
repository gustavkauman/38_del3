package IOOuterActive.entities;

public class Card {
    private String name, text;

    public Card(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }
}

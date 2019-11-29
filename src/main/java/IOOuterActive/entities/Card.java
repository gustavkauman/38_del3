package IOOuterActive.entities;

public class Card {
    private String name, text;

    public Card(String name, String text) {
        this.name = name;
        this.text = text;
    }

    /**
     * @return Returns card name
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @return Returns card text
     */

    public String getText() {
        return text;
    }
}

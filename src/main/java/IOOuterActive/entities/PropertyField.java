package IOOuterActive.entities;

import java.awt.Color;

public class PropertyField extends Field {

    int price;
    Player owner = null;
    String color;

    public PropertyField(String name, int ID, String color, int price) {
        super(name, ID);
        this.color = color;
        this.price = price;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getPrice() {
        return price;
    }

    public Player getOwner() {
        return owner;
    }

    public String getColor() {
        return this.color;
    }

    public Color getBoardColor() {

        switch (this.color) {
            case "brown":
                return new Color(137,104,74);
            case "lightblue":
                return new Color(103,153,206);
            case "pink":
                return Color.PINK;
            case "yellow":
                return Color.YELLOW;
            case "red":
                return Color.RED;
            case "orange":
                return Color.ORANGE;
            case "green":
                return Color.GREEN;
            case "blue":
                return Color.BLUE;
            default:
                return Color.GRAY;
        }

    }
}
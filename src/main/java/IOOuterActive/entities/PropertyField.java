package IOOuterActive.entities;

public class PropertyField extends Field {

    int price;
    Player owner;
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
        return color;
    }
}
package IOOuterActive.entities;

public class PropertyField extends Field {

    int rent;
    Player owner;
    String color;

    public PropertyField(String name, int ID, String color) {
        super(name, ID);
        this.color = color;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public String getColor() {
        return color;
    }
}
package IOOuterActive.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameBoard {

    private Field[] fields;

    public GameBoard() {

        this.fields = new Field[]{
            new Field("Start", 0),
            new PropertyField("Burgerbaren", 1, "brown", 1),
            new PropertyField("Pizzariaet", 2, "brown", 1),
            new ChanceField("Chance", 3),
            new PropertyField("Slikbutikken", 4, "lightblue", 1),
            new PropertyField("Iskiosken", 5, "lightblue", 1),
            new PrisonField("Fængsel", 6),
            new PropertyField("Museet", 7, "pink", 2),
            new PropertyField("Biblioteket", 8, "pink", 2),
            new ChanceField("Chance", 9),
            new PropertyField("Skateparken", 10, "yellow", 2),
            new PropertyField("Swimmingpool", 11, "yellow", 2),
            new Field("Gratis parkering", 12),
            new PropertyField("Spillehallen", 13, "red", 3),
            new PropertyField("Biografen", 14, "red", 3),
            new ChanceField("Chance", 15),
            new PropertyField("Legetøjsbutikken", 16,"orange", 3),
            new PropertyField("Dyrehandlen", 17,"orange", 3),
            new Field("Gå i fængsel", 18),
            new PropertyField("Bowlinghallen", 19,"green",4),
            new PropertyField("Zoo", 20,"green",4),
            new ChanceField("Chance", 21),
            new PropertyField("Vandland", 22, "blue", 5),
            new PropertyField("Strandpromenaden", 23, "blue", 5)
        };

    }

    public Field[] getFields() {
        return fields;
    }

    public PropertyField[] getFieldsOwnedByPlayer(Player player) {

        List<PropertyField> list = new ArrayList<PropertyField>();

        for (Field field : this.fields) {

            if (field instanceof PropertyField && ((PropertyField) field).getOwner() == player) {
                list.add((PropertyField) field);
            }

        }

        PropertyField[] out = new PropertyField[list.size()];
        return list.toArray(out);

    }

    public Field[] getFieldsByColor (String color) {

        List<Field> list = new ArrayList<Field>();
        Field[] out = new Field[2];

        for (Field field: this.fields) {
            try {
                PropertyField propertyField = (PropertyField) field;
                if (propertyField.getColor().equals(color))
                    list.add(propertyField);
            } catch (ClassCastException e) {

            }
        }

        return list.toArray(out);
    }

    public boolean fieldsAreOwnedBySamePlayer (Field[] fields) {
        return ( ((PropertyField) fields[0]).getOwner() == ((PropertyField) fields[1]).getOwner());
    }

    public PropertyField getFieldByColor (int currentPlayerPos, String... inputColors) {

        List<String> colors = Arrays.asList(inputColors);

        while (true) {

            int fieldIndex = currentPlayerPos;

            try {
                PropertyField field = (PropertyField) this.fields[++fieldIndex];

                if (colors.contains(field.getColor())) {
                    return field;
                }

            } catch (ClassCastException e) {
                ++fieldIndex;
            }
        }
    }
}

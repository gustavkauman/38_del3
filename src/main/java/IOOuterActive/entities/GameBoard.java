package IOOuterActive.entities;

public class GameBoard {

    private Field[] fields;

    public GameBoard() {

        this.fields = new Field[]{
            new Field("Start", 0),
            new PropertyField("Burgerbaren", 1, "brown", 1),
            new PropertyField("Pizzariaet", 2, "brown", 1),
            new ChanceField("Chance", 3),
            new PropertyField("Slikbutikken", 4, "blue", 1),
            new PropertyField("Iskiosken", 5, "blue", 1),
            new PrisonField("Fængsel", 6),
            new PropertyField("Museet", 7, "orange", 2),
            new PropertyField("Biblioteket", 8, "orange", 2),
            new ChanceField("Chance", 9),
            new PropertyField("Skateparken", 10, "yellow", 2),
            new PropertyField("Swimmingpool", 11, "yellow", 2),
            new Field("Gratis parkering", 12),
            new PropertyField("Spillehallen", 13, "red", 3),
            new PropertyField("Biografen", 14, "red", 3),
            new ChanceField("Chance", 15),
            new PropertyField("Legetøjsbutikken", 16,"yellow", 3),
            new PropertyField("Dyrehandlen", 17,"yellow", 3),
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
}

package IOOuterActive.entities;

/**
 * The general template for each field (Superclass)
 *
 * @author Moamal Abdul-Mahdi, David Mikkelsen
 * @Version 1.0
 */
public class Field {

    private String name;
    private int id;

    public Field(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * @return Returns field name
     */
    public String getName() {
        return name;
    }

    /**
     * @return ID name
     */
    protected int getId() {
        return this.id;
    }
}

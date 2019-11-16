package field;

/**
 * The general template for each field (Superclass)
 *
 * @author Moamal Abdul-Mahdi, David Mikkelsen
 * @Version 1.0
 */
public class Field {

    private String name;
    private int id;

    public Field(String name, int id){

        this.name = name;
        this.id = id;

    }

    /**
     * @return
     * A string containing the name and id of the field
     */
    public String getField(){

        return name +"\t" + id;
    }




}

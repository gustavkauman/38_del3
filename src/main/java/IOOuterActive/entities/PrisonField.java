package IOOuterActive.entities;

/**
 *
 * @author David Mikkelsen
 * @version 1.0
 */
public class PrisonField extends Field {

    private boolean convicted;

    public PrisonField(String name, int id){
        super(name,id);
    }

    /**
     * Imprisons a player
     */
    public void imPrison(){

        convicted = true;
    }

    /**
     * Releases a player from prison
     */
    public void release(int bail, String card){

        if(bail > 0)
            convicted = false;
        else if(card.equals("Release"))
            convicted = false;
    }
}

package structures;

/**
 * Created by cadebryant on 7/31/16.
 */
public class NamedEntity {
    /**
     * Default parameterless constructor
     */
    public NamedEntity() {

    }

    /**
     * Constructor
     * @param nm
     */
    public NamedEntity(String nm) {
        name = nm;
    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String nm) {
        name = nm;
    }
}

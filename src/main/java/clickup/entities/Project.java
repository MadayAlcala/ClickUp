package clickup.entities;

/**
 * Project class.
 *
 * @author Maday Alcala
 * @version 1.0
 */
public class Project {
    private String name;

    /**
     * Getter method.
     *
     * @return a String containing name associated to this project.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter method.
     *
     * @param name a String containing the new value to assign as a new name for this project.
     */
    public void setName(final String name) {
        this.name = name;
    }
}

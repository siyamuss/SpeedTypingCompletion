/**
 * It's a simple class that holds the code and description of a genre
 */
package za.co.typespeed.completition.persistence;

public class Player {
    private final String name;
    private final String position;

    // A constructor that takes in a code and description and sets the code and description of the object to the code
    // and description passed in.
    public Player(String nameOfPlayer, String positionOfPlayer) {
        this.name = nameOfPlayer;
        this.position = positionOfPlayer;
    }

    /**
     * This function returns the code of the object.
     *
     * @return The code of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * > This function returns the description of the object
     *
     * @return The description of the item.
     */
    public String getPosition() {
        return position;
    }

    /**
     * If the object passed in is not null and is of the same class as the object calling the function, then return true if
     * the code of the object passed in is equal to the code of the object calling the function
     *
     * @param o The object to compare to.
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player genre = (Player) o;

        return name.equals(genre.name);
    }

    /**
     * > The hashCode() function returns the hash code value for this enum constant
     *
     * @return The hash code of the code variable.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * The toString() method returns a string representation of the object
     *
     * @return The code and description of the genre.
     */
    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

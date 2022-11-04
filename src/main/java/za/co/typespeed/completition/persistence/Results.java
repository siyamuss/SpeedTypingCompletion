/**
 * A book class represents a book, and it has (a title, a genre, and an id)
 */
package za.co.typespeed.completition.persistence;

import java.util.Objects;

public class Results {


    private final String SPEED_TIME;
    private final String LINE;
    private final String NAME;

    // A book constructor that takes (a title and a genre) and assigns them to the book.
    public Results(String name, String line, String speedTime) {
        this.NAME = name;
        this.LINE = line;
        this.SPEED_TIME = speedTime;
    }

    /**
     * The toString() method returns a string
     *
     * @return The id, title, and genre of the book.
     */
    @Override
    public String toString() {
        return "Result{" +
                "name=" + NAME +
                ", line='" + LINE + '\'' +
                ", speedTime=" + SPEED_TIME +
                '}';
    }
}

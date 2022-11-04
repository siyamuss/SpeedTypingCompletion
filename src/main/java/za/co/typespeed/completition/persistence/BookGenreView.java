/**
 * It's a simple data class that holds the book title and genre description
 */
package za.co.typespeed.completition.persistence;

public class BookGenreView {
    private final String bookTitle;
    private final String genreDescription;

    // A constructor that takes two parameters and assigns them to the class variables.
    public BookGenreView(String bookTitle, String genreDescription) {
        this.bookTitle = bookTitle;
        this.genreDescription = genreDescription;
    }

    /**
     * This function returns the title of the book
     *
     * @return The book title.
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     * This function returns the genreDescription of the movie
     *
     * @return The genreDescription is being returned.
     */
    public String getGenreDescription() {
        return genreDescription;
    }

    /**
     * If the object passed in is not null, and is of the same class as the object calling the function, then compare the
     * two objects' bookTitle and genreDescription fields. If they are equal, return true. Otherwise, return false
     *
     * @param o The object to be compared.
     * @return The hashcode of the object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookGenreView that = (BookGenreView) o;

        if (!bookTitle.equals(that.bookTitle)) return false;
        return genreDescription.equals(that.genreDescription);
    }

    /**
     * The hashCode() method returns a hash code value for the object
     *
     * @return The hashcode of the book title and genre description.
     */
    @Override
    public int hashCode() {
        int result = bookTitle.hashCode();
        result = 31 * result + genreDescription.hashCode();
        return result;
    }

    /**
     * The toString() function is a function that is automatically called when you try to print an object
     *
     * @return The book title and genre description.
     */
    @Override
    public String toString() {
        return "BookGenreView{" +
                "bookTitle='" + bookTitle + '\'' +
                ", genreDescription='" + genreDescription + '\'' +
                '}';
    }
}

package business.entity;

/**
 * The Statistic class is the representation of a statistic managed by the system. Implements the {@link Comparable} to
 * be able to sort the statistics from the most popular genre to the least.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-10
 */
public class Statistic implements Comparable<Statistic>{
    private int numSongs;
    private final String genre;

    /**
     * Default Statistic parametrized constructor.
     */
    public Statistic(String genre) {
        this.numSongs = 1;
        this.genre = genre;
    }

    /**
     * Returns the number of songs that shares this genre
     *
     * @return the number of songs
     */

    public int getNumSongs() { return numSongs; }

    /**
     * Updates the number of the statistic by adding 1
     */
    public void updateNumSongs() {
        this.numSongs += 1;
    }

    /**
     * Returns the genre this statistic belongs to.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Compares two statistics using the number of songs from those.
     *
     * @param statistic The statistic to be compared to this statistic
     * @return Zero if the number of songs is equal to this number of songs, a value less than zero if this number of
     * songs is less than that, or a value greater than zero if this number of songs is greater than that
     */
    @Override
    public int compareTo(Statistic statistic) {
        return this.numSongs - statistic.numSongs;
    }

}

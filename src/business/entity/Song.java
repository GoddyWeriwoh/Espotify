package business.entity;

/**
 * The Song class is the representation of a song managed by the system.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-11
 */
public class Song {
    private final int id;
    private final String title;
    private final String author;
    private final String album;
    private final String genre;
    private final String duration;
    private final String username;
    private final String path;

    /**
     * Default Song parametrized constructor.
     */
    public Song(int id, String title, String author, String genre, String album, String duration, String username, String path){
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.album = album;
        this.duration = duration;
        this.username = username;
        this.path = path;
    }

    /**
     * Returns the unique id of a song.
     *
     * @return the id of the song
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the title of a song.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the author of a song.
     *
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the album of a song.
     *
     * @return the album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Returns the genre of a song.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Returns the duration of a song.
     *
     * @return the duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Returns the owner of a song.
     *
     * @return the owner
     */
    public String getUserName() {
        return username;
    }

    /**
     * Returns the path of a song.
     *
     * @return the path
     */
    public String getPath() {
        return path;
    }
}

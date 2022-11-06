package business;

import business.entity.Song;
import business.entity.User;
import persistence.SQL.SQLSongDAO;
import persistence.SQL.SQLUserDAO;
import persistence.SongDAO;
import persistence.UserDAO;

import java.util.ArrayList;
import java.util.regex.Pattern;
import business.entity.Song;
/**
 * The SongManager class focuses on logic related to the playlists from the app.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-12
 */
public class SongManager {
    private final SongDAO songDAO;
    private User user;
    private business.entity.Song song;
    private ArrayList<Song> songs;
    /**
     * Default SongManager constructor, initializes the persistence layer.
     */
    public SongManager(){
        songDAO = new SQLSongDAO();
        songs = songDAO.getSongList();
    }

    /**
     * Registers a song in the system and persist the changes in the database.
     *
     * @param name The name of the song
     * @param author The author of the song
     * @param genre The genre of the song
     * @param album The album where the song was released
     * @param path the path of the system where the song archive (.mp3) is stored
     */
    public void registerSong(String name, String author, String genre, String album, String duration, String path, String username) {
        Song song = new Song(1, name,  author,  genre,  album,  duration,  username, path);
        songDAO.addSong(song, songs.size());
        songs.add(song);
    }

    /**
     * Returns the list of songs stored in the system.
     *
     * @return List of songs in the system
     */
    public ArrayList<Song> getSongList() {
        return songs;
    }

    /**
     * Verifies the path of the song.
     *
     * @param path The path of the song
     * @return true if the path is ok
     */
    public boolean checkPathOk(String path) {
        return path.endsWith(".mp3");
    }

    //S'HA DE ELIMINAR AQUESTA FUNCIO
    public boolean checkTimeOk(String duration) {
        Pattern p = Pattern.compile("\\d{2}:\\d{2}");
        return p.matcher(duration).matches();
    }

    /**
     * Verifies that the necessary inputs are filled.
     *
     * @param name The name of the song
     * @param path The path of the song in the system
     * @param genre The genre of the song
     * @return true if the inputs are ok
     */
    public boolean hasInputs(String name, String path, String genre) {
        return !name.equals("") && !path.equals("") && !genre.equals("");
    }

    /**
     * Returns the name of the song.
     *
     * @param title The name of the song
     * @return the name of the song
     */
    public Song getSong(String title) {
        for(Song song: songs){
            if (song.getTitle().equals(title)) return song;
        }
        return null;
    }

    /**
     * Deletes the song from the system.
     *
     * @param song The song to be deleted
     * @return true if the deletion was successful
     */
    public boolean deleteSong(Song song) {
        songs.remove(song);
        return songDAO.deleteSong(song);
    }

    /**
     * Sets the selected song as the current song to manage.
     *
     * @param songName The name of the song to be set as current
     */
    public void setCurrentSong(String songName) {
        this.song = getSong(songName);
    }

    /**
     * Gets the current song that is being managed.
     *
     * @return the current song to manage
     */
    public Song getCurrentSong() {
        return song;
    }

    /**
     * Checks if the current user logged is the owner of the song.
     *
     * @param user the current user logged in the system
     * @return true if is the owner of the song
     */
    public boolean isUser(User user) {
        return user.getUsername().equals(song.getUserName());
    }

    /**
     * Sets the current user logged in the system
     * @param user sets the current user logged in the system
     */
    public void setUser(User user){
        this.user = user;
    }

    /**
     * Return the current user logged in the system.
     *
     * @return the current user logged in the system
     */
    public User getUser(){
        return user;
    }
}

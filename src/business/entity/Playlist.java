package business.entity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * The Playlist class is the representation of a user managed by the system.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-25
 */
public class Playlist {
    private int id;
    private String title;
    private ArrayList<SongOrdered> songs;
    private User owner;
    private int currentSongPosition;
    private boolean playlistOverBool;


    /**
     * Default Playlist parametrized constructor.
     */
    public Playlist(int id, String title, User owner) {

        this.title = title;
        this.owner = owner;
        this.id = id;
        songs = new ArrayList<SongOrdered>();
        currentSongPosition = 0;
        playlistOverBool = false;
    }

    /**
     * Returns the title of a playlist.
     *
     * @return the title
     */
    public String getTitle(){
        return title;
    }

    /**
     * Returns the songs contained in a playlist.
     *
     * @return the songs
     */
    public ArrayList<SongOrdered> getSongs() {
        return songs;
    }

    /**
     * Adds a song to the playlist.
     *
     * @param songOrdered SongOrdered to be added.
     */
    public void addSong(SongOrdered songOrdered) {
        this.songs.add(songOrdered);
    }

    /**
     * Returns the owner of the playlist.
     *
     * @return the owner
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Returns the unique id of a playlist.
     *
     * @return the id
     */
    public int getId(){
        return id;
    }

    /**
     * Sers the songs in the playlist in an ordered way
     * @param songs ArrayList<SongOrdered>
     */
    public void setSongs(ArrayList<SongOrdered> songs){
        this.songs = songs;
    }

    /**
     * Removes the song and rearranges the order
     * @param posi int
     */
    public void removeSong(int posi) {
        songs.remove(posi);
            for (int i = posi; i < songs.size(); i++) {
                songs.get(i).decrementOrder();
            }
    }

    /**
     * Gets the title of a song in a demanded position
     * @param i int
     * @return String
     */
    public String getSongTitle(int i) {
        return getSongs().get(i).getSong().getTitle();
    }

    /**
     * Returns the array of playlist songs in an ordered way.
     * @return ArrayList<SongOrdered>
     */
    public ArrayList<SongOrdered> getSongsOrdered(){
        return songs;
    }

    /**
     * Decrements the order position of the chosen song.
     * @param chosenSong String
     */
    public void decrementCurrentSongOrder(String chosenSong) throws IndexOutOfBoundsException{
        boolean found = false;
        for (int i = 0; i < songs.size() && !found; i++) {
            if(songs.get(i).getSong().getTitle().equals(chosenSong)){
                songs.get(i).decrementOrder();
                songs.get(i-1).incrementOrder();
                Collections.swap(songs, i-1, i);
                found = true;
            }
        }
    }
    /**
     * increments the order position of the chosen song.
     * @param chosenSong String
     */
    public void incrementCurrentSongOrder(String chosenSong) throws IndexOutOfBoundsException{
        boolean found = false;
        for (int i = 0; i < songs.size() && !found; i++) {
            if(songs.get(i).getSong().getTitle().equals(chosenSong)){
                songs.get(i).incrementOrder();
                songs.get(i+1).decrementOrder();
                Collections.swap(songs, i+1, i);
                found = true;
            }
        }
    }

    public void setCurrentSongPosition(int i) {
        currentSongPosition = i;
    }

    public int getCurrentSongPosition() {
        return currentSongPosition;
    }

    public void incrementCurrentSongPosition() {
        currentSongPosition++;
        if(currentSongPosition == songs.size()){
            playlistOverBool = true;
        }
    }

    public boolean playlistOver() {
        return playlistOverBool;
    }
}

package business.entity;

import javax.swing.*;

/**
 * Class that helps us keep and manae song order in playlists.
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 *
 * @version 1.0
 * @since 2022-05-10
 */
public class SongOrdered {
    private int order;
    private Song song;

    /**
     * Constructor for songordered
     * @param order1 int
     * @param song1 Song
     */
    public SongOrdered(int order1, Song song1){
        order = order1;
        song = song1;
    }
    /**
     * Increments the song order, putting it one step behind
     */
    public void incrementOrder(){
        order++;
    }

    /**
     * Decrements the song order, putting it one step in front
     */
    public void decrementOrder(){
        order--;
    }

    /**
     * Retrurns the song
     * @return Song
     */
    public Song getSong(){
        return song;
    }

    /**
     * Returns the order of this song
     * @return order
     */
    public int getOrder(){
        return order;
    }
}

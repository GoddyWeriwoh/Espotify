package persistence;

import business.entity.Song;

import java.util.ArrayList;

/**
 * The SongDAO interface defines the design that the DAO must follow to manage the
 * persisted data from the Songs in the system.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-11
 */
public interface SongDAO {
    void addSong(Song song, int id);

    boolean deleteSong(Song song);

    ArrayList<Song> getSongList();
}

package persistence;

import business.entity.Playlist;
import business.entity.Song;
import business.entity.SongOrdered;
import business.entity.User;

import java.util.ArrayList;

/**
 * The PlaylistDAO interface defines the design that the DAO must follow to manage the
 * persisted data from the Playlists in the system.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-25
 */
public interface PlaylistDAO {
    void addPlaylist(Playlist playlist);

    boolean deletePlaylist(Playlist playlist);

    void addSongToPlaylist(Playlist playlist, SongOrdered song);

    boolean deleteSongFromPlaylist(Playlist playlist, Song song);

    boolean playlistNameExists(String name, String userName);

    ArrayList<Playlist> getUserPlaylists(User currentUser);

    ArrayList<Playlist> getOthersPlaylists(String userName);

    ArrayList<Song> getSongListFromPlaylist(String playlistName);

    void refreshPlaylist(Playlist currentPlaylist);
}

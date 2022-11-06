package persistence.SQL;

import business.entity.Playlist;
import business.entity.Song;
import business.entity.SongOrdered;
import business.entity.User;
import persistence.PlaylistDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * The SQLPlaylistDAO implements the {@link PlaylistDAO} interface to persist the data in
 * a database.
 *
 * @author Georgina Bellmunt, Andrea Labá, Gonzalo Garcia, Sergi Pelfort, Weriwoh Mbang Goddy
 * Clinton Junior and Maria Duch.
 * @version 1.0
 * @since   2022-05-25
 */

public class SQLPlaylistDAO implements PlaylistDAO {//MISSING IMPLEMENTS


    /**
     * Saves a playlist in the database.
     *
     * @param playlist The playlist to be persisted
     */
    @Override
    public void addPlaylist(Playlist playlist){
        String query = "INSERT INTO playlist_user(playlist,user) VALUES ('" +
                playlist.getTitle() + "', '" +
                playlist.getOwner().getUsername() + "');";

        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Deletes a playlist in the database.
     *
     * @param playlist The playlist to be deleted
     */
    @Override
    public boolean deletePlaylist(Playlist playlist) {
        String query = "DELETE FROM playlist_song WHERE id = '" + playlist.getId() +"';";
        SQLConnector.getInstance().deleteQuery(query);
        query = "DELETE FROM playlist_user WHERE id = '" + playlist.getId() +"';";
        return SQLConnector.getInstance().deleteQuery(query);
    }

    /**
     * Persists the relation between a song and a playlist in the database.
     *
     * @param playlist The playlist to be persisted
     * @param song The song to be persisted
     */
    @Override
    public void addSongToPlaylist(Playlist playlist, SongOrdered song) {
        String query = "INSERT INTO playlist_song(id,play_order,song) VALUES ('" + playlist.getId() + "','" + song.getOrder() + "','" + song.getSong().getId() + "');";
        SQLConnector.getInstance().insertQuery(query);
    }

    /**
     * Deletes the relation between a song and a playlist in the database.
     *
     * @param playlist The playlist to be deleted in the relation
     * @param song The song to be deleted in the relation
     */
    @Override
    public boolean deleteSongFromPlaylist(Playlist playlist, Song song) {
        String query = "DELETE FROM playlist_song WHERE id = '" + playlist.getId() + "'AND song ='" + song.getId() +"';";
        return SQLConnector.getInstance().deleteQuery(query);
    }

    /**
     * Checks if playlist name exists
     * @param name String
     * @param userName String
     * @return boolean
     */
    @Override
    public boolean playlistNameExists(String name, String userName) {
        String query = "SELECT playlist FROM playlist_user WHERE playlist = '" + name + "' AND user = '" + userName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try {
            return result.next(); // checks if there is any row
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Gets the demanded user's playlist
     * @param currentUser User
     * @return ArrayList<Playlist>
     */
    @Override
    public ArrayList<Playlist> getUserPlaylists(User currentUser) {

        String query = "SELECT * FROM playlist_user  WHERE user = '" + currentUser.getUsername() + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        ArrayList<Playlist> playlistList = new ArrayList<>();
        try {
            while(result.next()){
                int id = result.getInt("id");
                ArrayList<SongOrdered> songsOrdered = new ArrayList<>();

                String query1 = "SELECT * FROM playlist_song  WHERE id = '" + id + "';";
                ResultSet result1 = SQLConnector.getInstance().selectQuery(query1);
                try{
                    while(result1.next()){
                        int order = result1.getInt("play_order");
                        String query2 = "SELECT * FROM song  WHERE id = '" + result1.getInt("song") + "';";
                        ResultSet result2 = SQLConnector.getInstance().selectQuery(query2);
                        while(result2.next()){
                            Song song = new Song(result2.getInt("id"), result2.getString("name"), result2.getString("author"), result2.getString("genre"),result2.getString("album"),result2.getString("duration"),result2.getString("path"),result2.getString("username"));
                            SongOrdered songOrdered = new SongOrdered(order, song);
                            songsOrdered.add(songOrdered);
                        }
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }

                String playlistName = result.getString("playlist");
                Playlist p = new Playlist(id, playlistName, currentUser);
                p.setSongs(songsOrdered);
                playlistList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlistList;
    }

    @Override
    public ArrayList<Playlist> getOthersPlaylists(String userName) {
        String query = "SELECT * FROM playlist_user  WHERE user != '" + userName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        ArrayList<Playlist> playlistList = new ArrayList<>();
        try {
            while(result.next()){
                int id = result.getInt("id");

                ArrayList<SongOrdered> songsOrdered = new ArrayList<>();

                String query1 = "SELECT * FROM playlist_song  WHERE id = '" + id + "';";
                ResultSet result1 = SQLConnector.getInstance().selectQuery(query1);

                try{
                    while(result1.next()){
                        int order = result1.getInt("play_order");
                        String query2 = "SELECT * FROM song  WHERE id = '" + result1.getInt("song") + "';";
                        ResultSet result2 = SQLConnector.getInstance().selectQuery(query2);
                        while(result2.next()){
                            Song song = new Song(result2.getInt("id"), result2.getString("name"), result2.getString("author"), result2.getString("genre"),result2.getString("album"),result2.getString("duration"),result2.getString("path"),result2.getString("username"));
                            SongOrdered songOrdered = new SongOrdered(order, song);
                            songsOrdered.add(songOrdered);
                        }
                    }
                }catch (SQLException e1){
                    e1.printStackTrace();
                }

                String playlistName = result.getString("playlist");
                String currentUser = result.getString("user");
                Playlist p = new Playlist(id, playlistName, new User("", currentUser, ""));
                p.setSongs(songsOrdered);
                playlistList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playlistList;
    }

    @Override
    public ArrayList<Song> getSongListFromPlaylist(String playlistName) {
        ArrayList<Song> songs = new ArrayList<>();
        int playlist_id = 0;
        String query = "SELECT id FROM playlist_user  WHERE playlist = '" + playlistName + "';";
        ResultSet result = SQLConnector.getInstance().selectQuery(query);
        try{
            while(result.next()){
                playlist_id = result.getInt("id");
            }
            String query1 = "SELECT song FROM playlist_song  WHERE id = '" + playlist_id + "';";
            ResultSet result1 = SQLConnector.getInstance().selectQuery(query1);

            while(result1.next()){
                String query2 = "SELECT * FROM song  WHERE id = '" + result1.getInt("song") + "';";
                ResultSet result2 = SQLConnector.getInstance().selectQuery(query2);
                while(result2.next()){
                    songs.add(new Song(result2.getInt("id"), result2.getString("name"), result2.getString("author"), result2.getString("genre"),result2.getString("album"),result2.getString("duration"),result2.getString("path"),result2.getString("username")));
                }

            }


        }catch (SQLException e1){
            e1.printStackTrace();
        }

        return songs;
    }

    /**
     * Refreshes the playlist in database with new orders for songs
     * @param currentPlaylist Playlist
     */
    @Override
    public void refreshPlaylist(Playlist currentPlaylist) {
        String query = "DELETE FROM playlist_song;";
        SQLConnector.getInstance().deleteQuery(query);
        for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
            addSongToPlaylist(currentPlaylist, currentPlaylist.getSongs().get(i));
        }
    }

}
